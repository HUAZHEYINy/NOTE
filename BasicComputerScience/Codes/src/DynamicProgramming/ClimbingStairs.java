package DynamicProgramming;

/**
 * 问题描述:
 *   一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法.
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairsRecursion(45));
        System.out.println(climbStairsDP(45));
    }

    /**
     * 使用递归算法求解 - 运行时自上而下的求解.
     * 建议画出递归树。
     *  1. 定义什么时候终止
     *  2. 定义什么时候转换到下一步
     *  3. 定义初始状态
     * @param n - N级台阶.
     * @return 有多少种跳法.
     */
    private static int climbStairsRecursion(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        return climbStairsRecursion(n-1) + climbStairsRecursion(n-2);
    }

    /**
     * 使用动态规划求解 - 运行时自下而上的求解.
     *  1. 定义dp数组的含义
     *  2. 定义状态转移方程
     *  3. 找出初始值
     * @param n - N级台阶.
     * @return 有多少种跳法.
     */
    private static int climbStairsDP(int n) {
        int[] dp = new int[n+1];    // 定义dp[i]为高度为i级台阶的总跳法数.
        dp[0] = 1;                  // 找出初始值，因为动态规划的思想是自下而上的.
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];  //定义状态转移方程 dp[i] = dp[i-1] + dp[i-2]. 因为一次只能跳1级或者2级,
                                        // 到i级的总数等于i-1级和i-2级的总数。
        }

        return dp[n];
    }
}
