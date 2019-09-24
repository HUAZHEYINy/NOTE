# What to consider to build a service.
*** 

### Pre-Implementation. 
***Starting Points***
* Understand the funcitonal user requirements - from basic to details.
* Estimate the users requirements - understand the priority. 
* Understand the non-functional user requirments - e.g. SLA, latency, TPS, scale, authentication, TPS, more read less write, more write less reader, both massive read and write(Caching), cost.  
* High-Level architecture design - Skeleton design.

***Some knowledge***  
* Use Case Diagram
* Architecture Diagram 
* Sequence Diagram
* Decision of implementation techniques - Languages/Frameworks, Authentication Mechanisms, Scalibility Improvement.
***  
### Implementation  
***Starting Points***
* Implementation of skeleton design - focus more on extensibility.   
* Error Handling - Retryable/NonRetryable Exceptions( Error/Faults/Failures Cases), Failure Recovery Mechanism, Failure Notification Mechanism. 
* Throttling - Protective Mechanism.
* Backup Mechanisms - One authoritative Storage.
* Monitoring and Logging - Data Visualization(KPI),  Logging Storage and Analyzing Mechanism.
  
***Some Knowledge***  
* Clean Architecture - SRP(Single Responsibility Principle etc.)  
* AWS Services - Cloud Watch, Logsdash.
* ElasticSearch/Kibana Combo. 
***

### Post-Implementation  
***Starting Points***  
* Load Testing - Tune Service and Get the TPS(More Understand about the service).
* Maintenance - Refactor Code and Resolve Technical Debt, Build Emergency Responsive Mechanism(On-call.), Improve Service(Dynamic Configuration.)
  
***Some Knowledge***
* Flare Graph
