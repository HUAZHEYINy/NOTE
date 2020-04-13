# Chapter 6 - Partition  
## What is Partition?  
It's a division of a logical database; Each partition is a small database of its own;   
## Why Partition?  
* Scability - For very large dataset, one node can't store it all Or very high query throughput, we need to query in parallel etc. (The goal with partitioning is to spread the data and the query load evenly across nodes).
  
## Partitioning and Replicaiton  
![6-1 Combining replication and partitioning](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/6-1%20Combining%20Replication%20and%20Partitioning.png)    
Partitioning is usually combined with replication so that copies of each partition are stored on multiple nodes for fault tolerance.
  
## Partitioning of Key-Value Data  
* Hot Spot: A partition with disproportionately high load.  

### Partition by Key Range  
Use the range of primary key to partition,   

Disadvantage: e.g Timestamp. The pontential problem is that for a particualr date, we could have hot key issue.  
Advatage: The range is sorted so can be easily identified.  

### Partition by Hash of Key  
Use Hash function to calculate the hash for each key and use the hash of key to partition. The hash function will generate a hash that follows uniformaly distribution.     
* Consistent Hashing: It's a way of evenly distributing load across an internet-weide system of achces.  
  
Advantage: avoid the hot key by evenly distributing the key to partition.  
Disadvatage: The data are not scattered (not sorted)  

![6-3 Partition by hash of key](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/6-3%20Partitoning%20by%20hash%20of%20key.png)  

## Paritioning and Secondary Indexes  
  
### Partitioning Secondary Indexes by Document  
![6-4 Partitioning secondary indexes by document](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/6-4%20Partitioning%20secondary%20indexes%20by%20document.png)  
Local Index: The index is stored in the same partition of it's primary document. In other words, we only need to dela with the partition that contains the document ID that we are writing.  
  
Problem? The approach of querying reuqires searching on all partitions and that is quite expensive. We call this approach scatter/gather. 

### Partitioning Secondary Indexes by Term  
 ![6-5 Partitioning secondary indexes by term](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/6-5%20Partitioning%20secondary%20indexes%20by%20term.png)  
 
Gobal Index: index and the primary document are totally separate.  
  
Problem? The write of global index may be expensive as it has to find the location of index.
  

  
## Other Reference  
1. [Dynamodb Partitioning](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/HowItWorks.Partitions.html)
