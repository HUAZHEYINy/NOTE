- [Data Structure](#data-structure)
  * [Disjoint-set  并查集](#disjoint-set-----)
      - [What](#what)
      - [Methods](#methods)
      - [Pseudo Code](#pseudo-code)
      - [Code](#code)
      - [Img](#img)
  * [Disjoin-set Forests](#disjoin-set-forests)
      - [What](#what-1)
      - [Methods](#methods-1)
      - [Heuristics](#heuristics)
      - [Pseudo Code](#pseudo-code-1)
      - [Img](#img-1)
- [Search](#search)
  * [Binary Search](#binary-search)
      - [What](#what-2)
      - [Pseudo Code](#pseudo-code-2)
- [Sort](#sort)
  * [Seleciton Sort](#seleciton-sort)
      - [What](#what-3)
      - [Pseudo Code](#pseudo-code-3)
- [Other](#other)
  * [Dynamic Programming ???](#dynamic-programming----)
      - [What](#what-4)
      - [Example](#example)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>


  
## Data Structure
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
[HashMapImplementation](https://github.com/HUAZHEYINy/NOTE/tree/master/BasicComputerScience/Codes/src/DisjoinSet)  
  
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
     
## Search  
### Binary Search
##### What  
Simple search algorithm that is used find the target from a *sorted array*.     
Time Complexity: O(Log(n))
##### Pseudo Code  
```  
BinarySearch(array, target) 
  left = 0
  right = array.len  
  
  while(left <= right)  
    mid = (left + right) / 2
    if (array[mid] == target) return mid
    if (array[mid] > target) right = mid - 1
    else left = mid + 1
    
  return -1 
```     
[Java Implementation](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Search/BinarySearch/BinarySearch.java)  
  
## Sort  
### Seleciton Sort  
##### What  
Simple in-place sort algorithm which takes O(n^2) time.
##### Pseudo Code    
For each ele, start from the left most, replace it with the smallest ele for the elements on its right unles it's smallest already.  

```  
SelectionSort(array)  
  for i = 0 to array.len    
    minIndex = i
    for j = i + 1 to array.len  
      if array[j] < array[minIndex]
        minIndex = j
        
    if minIndx != i
        # Swap the array[i] with array[minIndx]  
        temp = array[minIndx]
        array[minIndx] = array[i]  
        array[i] = temp
```
https://www.tutorialspoint.com/data_structures_algorithms/selection_sort_algorithm.htm 

### Topological Sort　拓扑排序  
#### 什么是拓扑排序
有向图的拓扑排序是其顶点的线性排序, 对于从顶点u到ｖ的每个有向边uv,ｕ都排在ｖ之前.　　 
**注意**  
1. 当且仅当图中没有环的时候(有[向无环图](https://zh.wikipedia.org/wiki/%E6%9C%89%E5%90%91%E6%97%A0%E7%8E%AF%E5%9B%BE))，才能进行拓扑排序。  
2. 任何有向无环图至少有一个拓扑排序.  
#### 算法  
##### 卡恩算法  　
把入度为0的节点放到L中，然后从图中移除所有和这些点相连的边,再寻找图中入度为０的点加入L,移除和这些点相连的边. 直到找不到入度为０的点.  
当运行完成之后如果原图中还存在边的话那就说明原图中有环, 即无拓扑排序.    

时间复杂度 Big-O = O(V+E) 即　顶点数和边数的总和.
空间复杂度 Big-O = O(V) 即　顶点总数.
```Java  
L ← Empty list that will contain the sorted elements
S ← Set of all nodes with no incoming edge

while S is not empty do
    remove a node n from S
    add n to tail of L
    for each node m with an edge e from n to m do
        remove edge e from the graph
        if m has no other incoming edges then
            insert m into S

if graph has edges then
    return error   (graph has at least one cycle)
else 
    return L   (a topologically sorted order)
```  
##### 深度优先遍历  

时间复杂度 Big-O = O(V+E) 即　顶点数和边数的总和.
空间复杂度 Big-O = O(V) 即　顶点总数.

```Java  
L ← Empty list that will contain the sorted nodes
while exists nodes without a permanent mark do
    select an unmarked node n
    visit(n)

function visit(node n)
    if n has a permanent mark then
        return
    if n has a temporary mark then
        stop   (not a DAG)

    mark n with a temporary mark

    for each node m with an edge from n to m do
        visit(m)

    remove temporary mark from n
    mark n with a permanent mark
    add n to head of L
```
  
## Other
### Dynamic Programming
##### What  
* Method used to solve probelm by combining the solutions to subproblems.
* Applicable when the sub-problems can be nested recursively inside larger problems. Means the larger problem can be decomposed to sub-problems.    
* Use additional memory to save computation time. - Time-Memory Trade-Off.  
  
##### Key Concepts  
*All recursion problems can be solved by DP* - compare to recursion(solve all sub-problems which includes the sub-problems have been solved previously), DP will store the resulve of sub-problem and re-use it.   
    
##### Key Steps  
###### Recursion  
1. Find *Exit Condition* - know when to return.  
2. Find what needed to be pass to next iteration.
##### Example  
* https://web.stanford.edu/class/archive/cs/cs161/cs161.1168/lecture12.pdf   
* [Code](https://github.com/HUAZHEYINy/NOTE/tree/master/BasicComputerScience/Codes/src/DynamicProgramming)  
* [https://www.cnblogs.com/wkfvawl/p/9372372.html](https://www.cnblogs.com/wkfvawl/p/9372372.html)
