## 动态规划  - Dynamic programming  
  
### 性质  
* 最优子结构(Optimal Substructure): an optimal solution to a problem contains optimal solutions to subproblems. e.g If z = LCS(x,y) then any prefix of Z is an LCS of a prefix of x and a prefix of y.
* 重叠子问题 (Overlapping Subproblems): A recursive solution contains a "small" number of distinct subproblems repreated many times.  
  
  自下而上 **Bottom-Up**  
  
* 长度为Ｎ的集合，集合的子集的个数为 2^N. [Proof](https://math.stackexchange.com/questions/546414/what-is-the-proof-that-the-total-number-of-subsets-of-a-set-is-2n).   
* [MIT-动态规划公开课](http://open.163.com/newview/movie/free?pid=M6UTT5U0I&mid=M6V2U1HL4)