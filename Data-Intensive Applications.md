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
![5-1 Leader Based Replication](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-1%20Leader-based%20%20Replication.png)  
1. One of the replicas is designated the leader(AKA master or primary). When clients want to write to the database, they must send their requests to the leader, which firstr wirtes the new data to its local storage.  
2. The other replicas are knows as followers(AKA read replicas, slavs, secondaries, or host standbys). Whenever the leader writes new data to its local storage, it also sends the data change to all of its followers as part of a replication log or change stream. Each follower takes the log from the leader and updates its local copy of the database accordingly, by applying all writes in the same order as they were processed on the leader. 
3. When a client wants to read from the database, it can query either the leader or any of the followers. However, writes are only accepted on the leader(the followers are read-only from client's point of view.)  
  
##### Synchronous Vs Asyncronous Replication    
![5-2 Leader Based async and sync](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-2%20Leader-based%20replication%20with%20one%20sync%20and%20one%20async.png)  

An important detail of a replicated system is whether the replication happens synthronously or asynchronously.  

`
The replication to follower 1 is synchronous: the leader waits until the follower has confirmed that it received the write before reporting success to the user, and before making the write visible to other clients. The replication to follower 2 is asynchronous: the leader sends the message, but does not wait for a reponse from the follower.  
`  
NOTE: Normally, the replication is quite fase but there is no guarantee of how long it might take. There are circumstances when followers might fall bebind the leader by serveral minutes or more. E.g If a follower is recovering from a failure, if the system is operating near maximum capacity, or if there are netowrk problems between the nodes.  
  
Advantage of Sync: The follower is guaranteed to have an up-to-date copy of the data that is consistent with the leader.  
Disadvantage of Sync: 
#### Multi-Leader  
#### Leaderless
