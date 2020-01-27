# What to consider to build a service.
*** 

### Pre-Design 
***Starting Points***
* Understand the functional user requirements.  
  *  Prioritize the requirements.
  *  Estimate the efforts and cost.
* Understand the non-functional user requirments.
  * e.g SLA, latency, TPS, scale, authentication mechanism, more read less write, more write less reader, both massive read and write(Caching), cost.  
* Extended requirements - Requirements other than P0.  

***Some knowledge***  
* Use Case Diagram
* Architecture Diagram 
* Sequence Diagram
* Decision of implementation techniques - Languages/Frameworks, Authentication Mechanisms, Scalibility Improvement.
***  
### In-Design  
***Starting Points***
* Detailed the skeleton design. (API Definition; Data Design; System Design and Algorithm)
  * First, Satisfy P0 requirements.
  * Second, Consider extensibility/scalability. e.g scale to support more use cases; scale to support more users; select proper componenets
* Error Handling.  
  * Retryable/NonRetryable Exceptions( Error/Faults/Failures Cases), Failure Recovery Mechanism, Failure Notification Mechanism. 
* Throttling.  
  * Protective Mechanism.
* Backup Mechanisms - One authoritative Storage.
* Monitoring and Logging.  
  * Data Visualization(KPI),  Logging Storage and Analyzing Mechanism.
  
***Some Knowledge***  
* Clean Architecture - SRP(Single Responsibility Principle etc.)  
* AWS Services - Cloud Watch, Logsdash.
* ElasticSearch/Kibana Combo.   
***

### Post-Design  
***Starting Points***  
* Load Testing - Tune Service and Get the TPS(More Understand about the service).
* Maintenance - Refactor Code and Resolve Technical Debt, Build Emergency Responsive Mechanism(On-call.), Improve Service(Dynamic Configuration.)
  
***Some Knowledge***
* Flare Graph
