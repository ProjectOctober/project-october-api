#!/usr/bin/env ruby
# Throw things at october-backend. Watch it scream.
$:.push('gen-rb')

require "benchmark"
require 'thrift'
require 'recommender'
require 'october_types'

begin
  transport = Thrift::FramedTransport.new(Thrift::Socket.new('127.0.0.1', 9091))
  protocol = Thrift::BinaryProtocol.new(transport)
  client = Backend::Recommender::Client.new(protocol)
  transport.open()

rescue Thrift::TransportException => e
  p "can't connect!" + e.messageo
  p "remember to start october in test environment!"
end

if system 'mongo october-test --eval "db.dropDatabase()"'
  puts "Database Dropped! Beginning test"
else
  puts "Could not drop database 'october-test'. Exiting..."
  exit
end

# TODO: This is making assumptions that the problem is number of tokens.
# Make a 3 dimensional examination later.

TOKENS = 16000
TOKENS_PER_POST = 100
MAX_COUNT_PER_TOKEN = 50
TOKEN_LENGTH = 8
USERS = 10
POSTS_PER_USER = 50
POSTS_PER_REQUEST = 20

$tokens = []

def random_tokens
  return $tokens.sample(rand(TOKENS_PER_POST)).map{ |t| Backend::Token.new(:t => t, :f => rand(MAX_COUNT_PER_TOKEN)) }
end

TOKENS.times do
  $tokens << (0...TOKEN_LENGTH).map{(65+rand(26)).chr}.join
end

puts "#TYPE\tUSERS\tPOSTS\tTOKENS\tTIME"
total_tokens = 0
USERS.times do |u|
  client.addUser(u+1)
  POSTS_PER_USER.times do |p|
    toks = random_tokens()
    add_time = Benchmark.realtime do
      client.addPost(u+1, u*POSTS_PER_USER+p+1, toks)
    end
    puts "add\t#{u+1}\t#{u*POSTS_PER_USER+p+1}\t#{total_tokens}\t#{add_time}"
    total_tokens += toks.length
    query_time = Benchmark.realtime do
      client.recPosts(1, POSTS_PER_REQUEST)
    end
    puts "recc\t#{u+1}\t#{u*POSTS_PER_USER+p+1}\t#{total_tokens}\t#{query_time}"
  end
end
