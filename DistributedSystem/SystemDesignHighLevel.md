# What to consider to build a service.

### Preparation  
```  
During this phase, we will not actually design the system from technical standpoint but rather prepare for the technical design. 
``` 
#### Noteworthy Points #### 
   
* Understand the functional user requirements.      
	* Including unstanding the user - Who/How/Why will use the system.
	* Prioritize the requirements.  
* Understand the non-functional user requirments.     
	* SLA, Latency, TPS(Traffic Amount), Scale, Authentication Mechanism, More read less write, More write less reader, both massive read and write(Caching), Cost.  
* Extended requirements - Requirements other than P0.  

**Some knowledge**  
1. Use Case Diagram
2. Architecture Diagram 
3. Sequence Diagram
4. Decision of implementation techniques - Languages/Frameworks, Authentication Mechanisms, Scalibility Improvement.
  
### System Design   
```  
We start designing the system!  
The principles are 1. make sure it solves the problems 2. from top to down 3. make decision with a reason 4. OE is part of the design.
```
#### Steps ####  
	  
1. Outline a high level design.  
	* sketch the main components and connections. e.g Need a DB for storing data; Message Queue System for Async Processing.   

2. Detail the high level design.   
	* Make Technical Decisions    
		* [Database Schema](https://github.com/HUAZHEYINy/NOTE/blob/master/DatabaseRelated/ChooseARightDB.md)
		* Database Type - NoSql/Sql    
		* etc...  
	* API Definitions  
	  
3. Scale the Design.  
	* Consider the extensibility - Scale the design to support additional requirements; Scalability - Scale the design to support more load.  
	    
4. Operation Excellence.  
	* Error Handling   
		* Failure Recovery Mechanism  
		* Failure Notificaiton Mechanism  
	* Throttling  
		* Protective Mechanism  
	* Backup Mechanism - One Authoritative Storage  
	* Monitoring and Logging.  
		* Failure Metrics/Alarms  
		* KPI Metrics/Dashboard
		* Logging  - Storage & Analyzing Tool  

### Post Design      
```  
After implementing the design, We need to ensure our system is stable with current load as well as prepare for the scale.   
```

#### Noteworthy Points ####      

* Loading Testing  
	* Understand the Service Bottleneck.    
	* Baseline the Scalling Plan.
* Other  
	* Improve Service Static Config (Dynamic Configuration.)
  
***Some Knowledge***  
  
  * Flare Graph

