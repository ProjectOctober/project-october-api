all: scrooge-3.0.1 gen_scala gen-rb
	@echo 'Success!'

gen_scala: october.thrift
	java -jar ./scrooge-3.0.1/scrooge-3.0.1.jar -d gen_scala ./october.thrift

gen-rb: october.thrift
	thrift --gen rb ./october.thrift 

setup: scrooge-3.0.1
	@echo 'Downloading and unzipping Scrooge...'

scrooge-3.0.1:
	wget http://maven.twttr.com/com/twitter/scrooge/3.0.1/scrooge-3.0.1.zip
	unzip ./scrooge-3.0.1.zip
	rm ./scrooge-3.0.1.zip

clean:
	rm -rf ./gen-rb ./gen-scala
