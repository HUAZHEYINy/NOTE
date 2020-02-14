### Disjoint-set  并查集    
##### What  
* Group N distinct elements into a collection of disjoint set.  
* Perform: find the unique set that contains the given element; Unite two sets for given element A & B.  
* Each set has a representative element.
##### Methods  
MakeSet -> Create a new set whose only member is x.  
Union(x, y) -> Unites the sets that contain x and y, Sx U Sy.  
FindSet(x) -> Return the representative element of the set which contains x.  
  
##### Pseudo Code  
```  
for each vertex v  
  MakeSet(v)  
  
for each edg(u, v) 
  if (FindSet(u) != FindSet(v))  
    Union(u, v)  
``` 
##### Code  
[HashMapImplementation](https://github.com/HUAZHEYINy/NOTE/blob/master/Codes/src/DisjointSet.java)  
  
##### Img  
1/ ![Disjoint Set Steps](https://media.geeksforgeeks.org/wp-content/uploads/Linked_List_representation_of_Disjoint_Set_Data_Structures_2.jpg)  
  
2/ ![Disjoin Set - LinkedList](https://media.geeksforgeeks.org/wp-content/uploads/Linked_List_representation_of_Disjoint_Set_Data_Structures_3.jpg)  
  
### Disjoin-set Forests  
##### What  
Instead of using an element as a representative element. It represents sets by rooted trees with each node contains one memeber and each tree represents one set.  
##### Methods  
MakeSet -> Create a tree with just one node.
FindSet(X) -> Retrieve its parent node till find the root of the tree.
Union(x, y) -> Point the root of the tree X to the root of the tree Y. 
##### Heuristics 
UnionByRank -> (Rank -> the height of the tree) Point the tree with smaller rank to the one with higher rank.
PathCompression -> For each node of a tree, instead of pointing to its parent, It points to the root of the tree.  
##### Pseudo Code  
```  
MakeSet(x)  
  x.parent = x;
  x.rank = 0  
  
Union(x, y)  
  Link(FindSet(x). FindSet(y))  
  
Link(x, y)  
  if (x.rank > y.rank)  
    y.parent = x  
  else   
    x.parent = y  
      if (x.rank == y.rank)  
        y.rank += 1  
   
FindSet(x)  
  if x != x.parent  
    x.parent = FindSet(x.parent)  
  return x.parent
```    
##### Img 
1/  
![Disjoint Set Forests](https://helloacm.com/wp-images/acm/2012/data-structure/disjoint3.jpg)
