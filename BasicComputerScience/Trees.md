- [Tree](#tree)
- [树](#-)
- [专业术语](#----)
  * [树的常见类型](#------)
    + [普通树](#---)
    + [二叉树](#---)
      - [完全二叉树](#-----)
        * [满二叉树](#----)
      - [二叉搜索树](#-----)
        * [AVL 树](#avl--)
        * [红黑树](#---)
  * [树的遍历](#----)
    + [深度优先遍历](#------)
    + [广度优先遍历](#------)
  * [关于树的一些算法题](#---------)
  * [索引](#--)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
 
   
## Tree   
  
## 树
树型结构是一种抽象类型，用来模拟具有树状结属性的非线性的数据结构.   

## 专业术语
父节点: 父节点是相对的概念, 它被它的子节点称为父节点；他被它的父节点称为子节点.  

子节点: 和父节点类似.  

叶子节点: 无子节点的节点.      

线性数据结构: 数据点被连续的联系在一起比如array;queue;linkedlist etc. 相反的Tree被称为非线性，因为他是层次型结构.       

简单路径: 一个没有重复节点的道路.  

高度/深度: 对一个树来说高度和深度是一样的；对一个节点来说深度是　从该节点到根节点的边的数量(自下而上 - 根的深度为０)，高度是　从该节点到它最深子节点边的数量（自上而下）.    

节点数和深度的关系(二叉树):  
 * N个节点的树最大高度为N-1; 最小高度为Log2(n) 
 * 高度为H, H层最多节点为2^h; 最多节点数为2^(h+1) - 1 -　数学归纳法可简单证明
    
### 树的常见类型  
#### 普通树  
每个节点能有任意数量的子节点. 它是所有其他树的超集。  

#### 二叉树  
每个节点最多有两个子节点。  
  
##### 完全二叉树    
* 除了最深一层外，其他层都有最大数量的子节点  
* 最深一层的节点必须从最左边连续开始.
  
###### 满二叉树  
* 所有*叶子节点*都有同样的深度.
* 任何*非叶子节*点都有２个子节点.  

##### 二叉搜索树  
特殊版本的二叉树, 增加了属性: 对任意节点，它的左孩子一定小于等于它，它的右孩子一定大于等于它 2.   
  
###### AVL 树    
特殊版本的二叉树搜索树, 增加了属性：对任意节点的两个子树，它们的深度差最多为１，任何打破这一原则的更改都会触发它自平衡的功能,来使它恢复到平衡的状态.  
*另外它的节点的数据结构包含了深度.*

###### 红黑树  
特殊版本的二叉树搜索树, 增加了属性：  
1. 节点是红色或者黑色   
2. 根是黑色   
3. 所有叶子借点都是黑色   
4. 任何红色节点必须有两个黑色子节点   
5. 从任意节点到其每个叶子的所有简单路径都包含相同数量的黑色节点  
*另外它的节点的数据结构包含了该节点的颜色*
  
  
#### 字典树 Trie 
又称前缀树, 能被用来快速搜索字符串 比如查英文字典时，我们是按照单词各个字母的顺序挨个查找 - words 我们会按照顺序 w -> o -> r -> d -> s.   
在计算机中我们可以使用树型结构来对单词进行存储以便于在查找的时候方便。  
 
![](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Imgs/TrieDiagram.svg)    
  
```  
上图是在一个Trie中插入单词 {"APP", "APPLE", "B", "BI", "CA"} 得出的结构. 从上图可以看出，我们有以下所有prefix的组合 {"A", "B", "C", "AP", "APP", "APPL", "APPLE", "BI", "CA"}. 但是在字典里面APP 和 APPLE 是两个不一样的单词，但是他们都存在这个prefix tree中，这时可以使用 特殊节点来作为一个单词的结束节点 - 如上图当我们结束插入 APP 的时候我们给 最后一个P的插入一个特殊的孩子节点.
```

总结一些性质:  
1. 根节点为空  
2. 每个节点仅存储它的孩子 
3. 从根到节点A,所有字符组成的字符串称为A节点的内容 


一些资料
https://www.interviewcake.com/concept/java/trie  
https://www.baeldung.com/trie-java

#### 其他树
* *B-Tree* 
* etc.  

### 树的遍历    

#### 深度优先遍历    

![](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Imgs/tree-traversal.jpg)   

* 前序遍历 -  先根遍历  
```  
　１．拜访根根　　
　２．遍历左子树　
　３．遍历右子树　　
```

* 中序遍历 - 中根遍历  
```  
　１．遍历左子树　　
　２．拜访根根
　３．遍历右子树　　
```
* 后序遍历 - 后跟遍历  
```  
　１．遍历左子树　　
　２．遍历右子树
　３．拜访根根　　
```
#### 广度优先遍历     
```  
从根节点往下依次横向拜访节点.
```  
*使用深度优先遍历的Tree图做例子*  
```  
25, 15, 50, 10, 22, 35, 70, 4, 12, 18, 24, 31, 44, 66, 90
```
  
### 关于树的一些算法题 　　
(以下问题都默认使用二叉树)    
```  
不要被各种有关树的算法吓跑，掰开揉碎了之后发现套路很简单。使用递归进行DFS或者使用Queue进行BFS。  
递归的话按照递归的套路  
1. 定义什么情况下该停止/返回  
2. 定义什么情况下移动到一下层递归  
3. 定义递归的初始状态.
```
  
* [深度优先遍历的代码实现](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/DFS.java)  
* [广度优先遍历的代码实现](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/BFS.java)
* [给一个根节点，返回树的深度](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/FindMaxDepth.java)
* [给一个根节点和一个值ｘ,　判定ｘ是否存在树中](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/FindXFromTree.java)　
* [给一个根节点和一个值ｘ，判定ｘ的深度](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/FindXFromTree.java)
* [给一个根节点和一个值ｘ,　返回ｘ的父节点](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/FindXFromTree.java)  
* [给一个根节点,　翻转二叉树](https://github.com/HUAZHEYINy/NOTE/blob/master/BasicComputerScience/Codes/src/Trees/Traversal/ReverseBinaryTree.java)

### 索引　　
* https://www.cs.cmu.edu/~tcortina/15-121sp10/Unit06A.pdf  
* https://en.wikipedia.org/wiki/AVL_tree    
* https://en.wikipedia.org/wiki/Red%E2%80%93black_tree  
* https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/trees.html
