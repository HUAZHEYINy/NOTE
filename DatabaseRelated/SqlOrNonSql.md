### SQL or NoSql  

#### Sql Database
* Prefine Schema
* Transactional Property 
    * Atomicity: Transaction complete or not.
    * Consistency: The entire database is in one steady state after each transaction.
    * Isolation: Transaction is executed serially(not overlapping trans).
    * Durability: The data is there once the transaction completed.
* Scalability
    - Master-slave replication: write/read to/from Master; read from slave; Master replicate to slave.
    - Master-master replication: write/read to/from master; Master sync each other.
    - Federation: autonomy; split database by x . e.g Geography; DataType Customer DB; Order DB.
    - Sharding: horizontal partitioning - each database can only store part of the data.
    - Denormalization
    - SQL tuning
  
#### NoSql Database
* Schemaless - only key is needed.
* 

#### Reference  
1. https://github.com/donnemartin/system-design-primer#database
