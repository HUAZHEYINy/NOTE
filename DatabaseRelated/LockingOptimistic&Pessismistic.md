# Optimistic Locking and Pessismistic Locking
### Optimistic Locking  
We always allow the process to read the data but when saving the data we will check whether the version will match or not.  
E.g Process1 -> read data(write down the data version 1) -> modify the data -> save data (save succeed when the version = 1; or failure).  
  
### Pessismistic Locking  
We will not allow the user to read the data if another user is working on the data.  - ## Exclusive Locking   
  
### Which one to choose.  
Optimistic Locking wins most of the time.   
But if the cost of merging two data or data processing is high then go with pessismistic locking.