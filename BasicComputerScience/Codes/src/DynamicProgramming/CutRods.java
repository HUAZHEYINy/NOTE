package DynamicProgramming;

/**
 * Dynamic Programming.
 *
 *  Problem we are trying to solve is: Find the optimal way to cut the rod with length of n.
 *  The optimal solution is defined such that the revenue of selling each sub-robs after cut is the most.
 *
 *  Sub-problem: The optimal solution must have all sub solution in optimal.
 */
public class CutRods {
    public static void main(String[] args) {
        int[] priceTable = {0, 1, 5,8, 9, 10, 17, 17,20 ,24, 30};

        System.out.println(cutRod(priceTable, 4));
        System.out.println(memorizedCutRod(priceTable, 4));
    }

    /**
     * Naive Solution - Recursion.
     *
     *  In order to get the optimal revenue, we have to ensure all sub length can generate the best revenue as well.
     *
     * @param arr - Price table with index as length and value as price.
     * @param n - Total length of the rod.
     * @return final revenue.
     */
    public static int cutRod(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;
        for (int i =  1; i <= n; i++) {
            q = Math.max(q, arr[i] + cutRod(arr, n - i));
        }

        return q;
    }

    /**
     * Improved Version - Memorization.
     *
     * The Naive solution will solve the problem with same input several times. So that we can reduce the efforts by memorizing the result computed previously.
     *
     * @param arr - Price table with index as length and value as price.
     * @param n - Total length of the rod.
     */
    public static int memorizedCutRod(int[] arr, int n) {
        int[] revenue = new int[n+1];
        for (int i = 0; i <= n; i++) {
            revenue[i] = Integer.MIN_VALUE;
        }

        return memorizedCutRod(arr, n ,revenue);
    }

    // Save the computed result as future reference.
    public static int memorizedCutRod(int[] arr, int n, int[] revenue) {
        if (revenue[n] >= 0) {
            return revenue[n];
        }

        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, arr[i] + memorizedCutRod(arr, n - i, revenue));
        }

        revenue[n] = q;
        return q;
    }
}
