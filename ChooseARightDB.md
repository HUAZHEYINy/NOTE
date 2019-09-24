# How to choose a right DataBase

***  
  
### What questions to ask when choosing DataBase.  
* Is this a read-heavy, write-heavy, or balanced workload? How many reads and
writes per second are you going to need? How will those values change if the
number of users increases?  

* How much data will you need to store and for how long? How quickly will this
grow? Is there an upper limit in the near future? What is the size of each
object (average, min, max)? How will these objects be accessed?    

* What are the requirements in terms of durability of data? Is this data store
going to be your “source of truth?”  
  
* What are your latency requirements? How many concurrent users do you
need to support?    

* What is your data model and how are you going to query the data? Are your
queries relational in nature (e.g., JOINs between multiple tables)? Could you
denormalize your schema to create flatter data structures that are easier to
scale?    

* What kind of functionality do you require? Do you need strong integrity
controls, or are you looking for more flexibility (e.g., schema-less data stores)?
Do you require sophisticated reporting or search capabilities? Are your
developers more familiar with relational databases than NoSQL?      

* What are the associated database technology license costs? Do these costs
consider application development investment, storage, and usage costs over
time? Does the licensing model support projected growth? Could you use
cloud-native database engines such as Amazon Aurora to get the simplicity
and cost-effectiveness of open-source databases?  
  
<To be continued...>