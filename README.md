## Service for hospital users.

Service provides next abilities:
* Doctor registration


There is eureka client implementation. 

There is kafka implementation. Apache Kafka makes the data highly fault-tolerant. It protects against server failure by distributing storage of data streams in a fault-tolerant cluster.

There is kafka producer implementation. When doctor tries to register, we need to create bank account for him using [finance-microservice(consumer)](https://github.com/kermakovich/hospital-finance).  

