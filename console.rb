#!/usr/bin/env ruby

$:.push('gen-rb')

require 'irb'

require 'thrift'
require 'recommender'

module IRB # :nodoc:
  def self.start_session(binding)
    unless @__initialized
      args = ARGV
      ARGV.replace(ARGV.dup)
      IRB.setup(nil)
      ARGV.replace(args)
      @__initialized = true
    end

    workspace = WorkSpace.new(binding)

    irb = Irb.new(workspace)

    @CONF[:IRB_RC].call(irb.context) if @CONF[:IRB_RC]
    @CONF[:MAIN_CONTEXT] = irb.context

    catch(:IRB_EXIT) do
      irb.eval_input
    end
  end
end

begin
  transport = Thrift::FramedTransport.new(Thrift::Socket.new('127.0.0.1', 9090))
  protocol = Thrift::BinaryProtocol.new(transport)
  client = Recommender::Client.new(protocol)
  transport.open()

  IRB.start_session(binding)

rescue Thrift::TransportException => e
  Rails.logger.error "Cannot Connect to Thrift Backend"
end
