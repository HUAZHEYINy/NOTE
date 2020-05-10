package Trees.Traversal;

import Trees.utilities.TreeNode;
import Trees.utilities.TreeUtilities;

public class FindMaxDepth {
    public static void main(String[] args) {
        final TreeNode root = TreeUtilities.generateBinaryTree();
        System.out.println(findMaxDepth(root));
    }

    // 递归: 1. 定义什么时候停止/结束 2. 定义什么时候进行下一步 3. 定义初始状态
    // 遇到递归问题画出初始几个轮次的递归图很有帮助.
    private static int findMaxDepth(final TreeNode node) {
        if (node == null) return 0;

        int left = findMaxDepth(node.left) + 1;
        int right = findMaxDepth(node.right) + 1;

        return Math.max(left, right);
    }
}
