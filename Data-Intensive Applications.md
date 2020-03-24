## Charter 5 - Replicaiton  
  
### What is Replication?   

Replication means keeping a copy of the data on multiple machines that are connected over network. 

### Why Replication?  
* Keep the data geographically close to the user - reduce the latency.
* Allow the system to continue working even if some of its parts have failed - increase the avaibility.  
* Scale out the number of machines that can serve more reads - increase the throughput.  
    
### What Challenge from Replication?  
If the data that you're replicating does not change over time then we just need to copy the data to every node once;   
But in reality, the data keeps changing so we have to handle the changes while replicating.   
    
### How the Solve the Challenge?  
Three most popular algorithms for replicating changes between nodes: single-leader; multi-leader; leaderless. 
There are also many trade-offs to consider with replication: for example,   
* Use Aynchronous or Synchronous replication.  
* How to handle the failed replicas.
  
#### Single-Leader   
  
  
#### Multi-Leader  
#### Leaderless
