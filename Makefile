all: api gen-cassie
	@echo 'Success!'

gen-cassie: cassie.thrift
	scala -cp lib/scopt_2.9.2-2.1.0.jar:lib/util_2.9.2-6.1.0.jar:lib/util-core-5.0.3.jar lib/scrooge-generator-3.0.5-SNAPSHOT.jar -d gen-cassie --finagle $<
	@echo 'Cassandra Generated'

api: gen-scala gen-rb
	@echo 'API Generated'

gen-scala: october.thrift
	scala -cp lib/scopt_2.9.2-2.1.0.jar:lib/util_2.9.2-6.1.0.jar:lib/util-core-5.0.3.jar lib/scrooge-generator-3.0.5-SNAPSHOT.jar -d gen-scala --finagle $<

gen-rb: october.thrift
	thrift --gen rb ./october.thrift 

clean:
	rm -rf ./gen-rb ./gen-scala ./gen-cassie
