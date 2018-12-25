# What to consider to build a service.
*** 

### Pre-Implementation. 
***Starting Points***
* Understand the funcitonal user requirements - from basic to details.
* Estimate the users requirements - understand the priority. 
* Understand the non-functional user requirments - e.g. SLA, latency, TPS, scale, authentication, more read less write, more write less reader, both massive read and write, cost.  
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
* Error Handling and Monitoring - Retryable/NonRetryable Exceptions( Error/Faults/Failures Cases), Failure Recovery Mechanism, Failure Notificatio Mechanism. 
* Backup Mechanisms - One authoritative Storage.
* Data Visualization - Data Analysis(KPI).
  
***Some Knowledge***  
* Clean Architecture - SRP(Single Responsibility Principle etc.)  
* AWS Services.  
* ElasticSearch/Kibana Combo. 
### Post-Implementation  
