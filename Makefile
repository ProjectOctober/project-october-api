all: gen_scala gen-rb
	@echo 'Success!'

gen_scala: october.thrift
	thrift --gen java ./october.thrift 
	mv ./gen-java ./gen_scala

gen-rb: october.thrift
	thrift --gen rb ./october.thrift 

clean:
	rm -rf ./gen-rb ./gen_scala
