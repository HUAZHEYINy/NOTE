- [Charter 5 - Replicaiton](#charter-5---replicaiton)
  * [What is Replication?](#what-is-replication-)
  * [Why Replication?](#why-replication-)
  * [What Challenge from Replication?](#what-challenge-from-replication-)
  * [How the Solve the Challenge?](#how-the-solve-the-challenge-)
    + [Single-Leader](#single-leader)
      - [Synchronous Vs Asyncronous Replication](#synchronous-vs-asyncronous-replication)
      - [Setup New Followers](#setup-new-followers)
      - [Handle Followers Failure](#handle-followers-failure)
      - [Handle Leader Failure](#handle-leader-failure)
      - [Implementation of Replication Logs](#implementation-of-replication-logs)
      - [Problems with Replication Lag](#problems-with-replication-lag)
        * [Read your own writes(Read-after-write)](#read-your-own-writes-read-after-write-)
        * [Monotonic Reads](#monotonic-reads)
        * [Consistent Prefix Reads](#consistent-prefix-reads)
    + [Multi-Leader](#multi-leader)
      - [Use Cases](#use-cases)
        * [Multi-datacenter operation](#multi-datacenter-operation)
        * [Client with offline operation](#client-with-offline-operation)
        * [Collaborative editing](#collaborative-editing)
      - [Handling Write Conflicts](#handling-write-conflicts)
        * [Synchronous Conflict Detection](#synchronous-conflict-detection)
        * [Conflict Avoidance](#conflict-avoidance)
        * [Converging Toward a Consistent State](#converging-toward-a-consistent-state)
        * [Custom Conflict Resolution Logic](#custom-conflict-resolution-logic)
      - [Multi-Leader Replication Topologies](#multi-leader-replication-topologies)
    + [Leaderless](#leaderless)
      - [Writeing to the Database When a Node Is Down](#writeing-to-the-database-when-a-node-is-down)
        * [Quorums for reading and writing](#quorums-for-reading-and-writing)
        * [Sloppy Quorums and Hinted Handoff](#sloppy-quorums-and-hinted-handoff)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>



# Charter 5 - Replicaiton  
  
## What is Replication?   

Replication means keeping a copy of the data on multiple machines that are connected over network. 

## Why Replication?  
* Keep the data geographically close to the user - reduce the latency.
* Allow the system to continue working even if some of its parts have failed - increase the avaibility.  
* Scale out the number of machines that can serve more reads - increase the throughput.  
    
## What Challenge from Replication?  
If the data that you're replicating does not change over time then we just need to copy the data to every node once;   
But in reality, the data keeps changing so we have to handle the changes while replicating.   
    
## How the Solve the Challenge?  
Three most popular algorithms for replicating changes between nodes: single-leader; multi-leader; leaderless.   

There are also many trade-offs to consider with replication: for example,   
* Use Aynchronous or Synchronous replication.  
* How to handle the failed replicas.
  
### Single-Leader     
![5-1 Leader Based Replication](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-1%20Leader-based%20%20Replication.png)  
1. One of the replicas is designated the leader(AKA master or primary). When clients want to write to the database, they must send their requests to the leader, which firstr wirtes the new data to its local storage.  
2. The other replicas are knows as followers(AKA read replicas, slavs, secondaries, or host standbys). Whenever the leader writes new data to its local storage, it also sends the data change to all of its followers as part of a replication log or change stream. Each follower takes the log from the leader and updates its local copy of the database accordingly, by applying all writes in the same order as they were processed on the leader. 
3. When a client wants to read from the database, it can query either the leader or any of the followers. However, writes are only accepted on the leader(the followers are read-only from client's point of view.)  
  
#### Synchronous Vs Asyncronous Replication    
![5-2 Leader Based async and sync](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-2%20Leader-based%20replication%20with%20one%20sync%20and%20one%20async.png)  

An important detail of a replicated system is whether the replication happens synthronously or asynchronously.  

`
The replication to follower 1 is synchronous: the leader waits until the follower has confirmed that it received the write before reporting success to the user, and before making the write visible to other clients. The replication to follower 2 is asynchronous: the leader sends the message, but does not wait for a reponse from the follower.  
`  
NOTE: Normally, the replication is quite fase but there is no guarantee of how long it might take. There are circumstances when followers might fall bebind the leader by serveral minutes or more. E.g If a follower is recovering from a failure, if the system is operating near maximum capacity, or if there are netowrk problems between the nodes.  
  
Advantage of Sync: The follower is guaranteed to have an up-to-date copy of the data that is consistent with the leader.  
Disadvantage of Sync: The leader needs to wait for the followers to complete and when the followers do not respond then the write can not succeed and it will block all writes.  
  
#### Setup New Followers
Challenge? Copy the snapshot of one node to the new node is not sufficient as the data is always in flux.   
Solve with following Steps:  
1. Leader consitently takes point-in-time snapshot of the database.  
2. Copy the snapshot to the new node.  
3. Associate the new node to the leader and request all the data changes that have happened since the snapshot was taken - Needs replication log.  
4. Once the new node processed the backlog of data changes since the snapshot - then it caught up and continue to process data latest write.  
  
#### Handle Followers Failure  
Similar to Setup New Followers, on its local disk it keeps copy of replication log.   

#### Handle Leader Failure  
High Level Steps:  
1. Determining that the leader has failed.  
2. Choosing a new leader.  
3. Reconfiguring the system to use the new leader.    

Problems?
1. When using asynchronous replication, the old leader may have unreplicated writes. Common solution is to discard the unreplicated data.
2. Split brain - multiple leaders.  
3. How to define the death of the leader? Not good if too long or too short. 
#### Implementation of Replication Logs 
* Statement-based replication - Leader sends the actual statement to its followers.  
* Write-ahead Log(WAL) shipping - Leader sends the logs (actual data change) to its followers.  
* Logical (row-based) log replication - Similar to WAL but the log its in different format which provides more flexibility.  
* Trigger-based replication - e.g Dynamodb Streaming.  
Resources: https://www.databasejournal.com/features/mysql/article.php/3922266/Comparing-MySQL-Statement-Based-and-Row-Based-Replication.htm
    
#### Problems with Replication Lag    
* Eventual Consistency: Read does not guarantee to return the up-to-date data after writes (tolerate some delays).  
* Read-after-write: Read guarantee to return the up-to-date data after writes.  

##### Read your own writes(Read-after-write)    
Problem: When the user writes the data then it needs to see the result immediately. e.g Update twitter profile. 

![read-after-write problem](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-3%20Raed-after-write%20consistency.png)  
Solution:  
* Read from leader: when reading something that the user modified then from leader otherwise from followers.  
* Read from leader if the read SLA is less than x seconds. e.g One minute after the user modified the profile, read from followers. Less than 1 minute then from leader.  
* Read based on timestamp: e.g Record the timestamp of last write, when read if the machine data has older timestamp than the write one then read from leader or other followers or wait.

##### Monotonic Reads  
Problem: The same user makes a series of reads but they will see time of the data go backward. e.g User refreshed the web page multiple times but the reads requests are severed from different hosts.
  
![Monotic reads problem](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-4%20Monotonic%20Reads.png)  

Solution:  
* Each users always reads from the same replica. 
  
*Additions*  
* Strong Consistency: All clients will receive the same result at any point in time; Sacrifice the latency.  
* Eventual Consistency: Not guarantee all clients will receive the same result but will be the same result after some time.  
* Read-after-write: One of forms of Strong consistency.  
* *Monotonic Reads*: Between strong consistency and eventual consistency. 
  
##### Consistent Prefix Reads   
Problem: When the writes are in certain order but when the reads happen the order can not be guaranteed. This is a prticular problem in partitioned databases if there is no global ordering of writes.
  
![Consistent prefix reads](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-5%20Consisten%20prefix%20reads.png)  

Solution:  
*  The solution is to make sure that any writes that are related to the each others are written to the same partition.

### Multi-Leader    
Why Multi-Leader?  
Compare to Single leader, it can accept more writes as well as increase the avalibility.   
#### Use Cases  
##### Multi-datacenter operation  
![Multi-datacenter replications](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-6%20multi-leader%20replication%20dc.png)  

* Performance  
Multi-leaders allow us to place the host close to the end users geographically. The leader replication in different dc can be done asynchronously.  
* Tolerance of dc outage.  
* Tolerance of network problems.  
##### Client with offline operation    
E.g Calendar app need to sync between phone/mac etc. Each device can act as one dc. (CouchDB)  
##### Collaborative editing  
Similar to the offline operation. 
  
#### Handling Write Conflicts  
![Handle Write Conflict](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-7%20write%20conflict.png)  
##### Synchronous Conflict Detection  
Make the write request synchronous and wait it to be replicated across all leaders.  
##### Conflict Avoidance  
Lock the write and only allow one user to write in one place. Achiving this by ensure all requests come to one host.  
##### Converging Toward a Consistent State  
Basically, converge the final result.   
* Last write wins.  
* Give each replica a unique Id and use the result that came from higher unique id one.  
* Take all value and concatenate them.  
* Take all value and ask the user to resolve it.  
##### Custom Conflict Resolution Logic 
* On write: allow the user to specify the way they want to resolve the conflict.  
* On read: provide versioning for the data that has conflict.
  
#### Multi-Leader Replication Topologies  
A *replication topology* describes the communication paths along which writes are propagated from one node to another.  
  
![Example Topologies](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-8%20Topologies.png)  
* Circular Topology  
Each node receives writres from one node and forwards those writes plus any writes of its own to one other node.  
* Star Topology  
One desinated root node forwards writes to all of the other nodes.  
* All-to-all Topology  
All pass to all.  
  
Issue may have:   
![Multi-leader replication issue](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-9%20Multi-leader%20issue.png)  
*Detecting Concurrent Writes*
  
### Leaderless
The clients directly send its writes to several replicas.  
#### Writing to the Database When a Node Is Down  
![A quorum write, read and read repair after a node outage](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-10%20A%20quorum%20write%2C%20quorum%20read%2C%20and%20read%20repair%20after%20a%20node%20outage.png)  
  
##### Read repair and anti-entropy    
* Read repair - When a client makes a read from several nodes in parallel, it can detect any stale response and fix the stale data by writing the new data to the stale node.  
* anti-entropy process - Have a background process that constantly looks for differences in the data between replicas and copies any missing data from one replica to another.  
  
##### Quorums for reading and writing  
Define  
* total nodes : n
* every writes must be confirmed by the number of nodes : w  
* every read must be confirmed by the number of nodes : r    
When w + r > n, then we can guarantee that at least one of the r nodes must be up to date.  

![quorum write/read](https://github.com/HUAZHEYINy/NOTE/blob/master/images/Data-intensive-App/5-11%20if%20w%2Br%2C%20at%20least%20one%20of%20the%20r%20replicas%20you%20read%20from%20must%20have%20seen%20the%20most%20recent%20successful%20write.png)  
##### Sloppy Quorums and Hinted Handoff  
* Sloppy quorum - writes and reads still require w and r successful respnses, but those may include nodes that are not among the designated n.  
* Hinted handoff - Any writes that one node temporarily accepted on behalf of another node are sent to the appropriate "home" node.
