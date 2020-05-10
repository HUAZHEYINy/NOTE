package Trees.Traversal;

import Trees.utilities.TreeNode;
import Trees.utilities.TreeUtilities;

public class DFS {
    public static void main(String[] args) {
        final TreeNode root = TreeUtilities.generateBinaryTree();

        System.out.println("\nPreOrder: ");
        preOrderTraversal(root);

        System.out.println("\nInOrder: ");
        inOrderTraversal(root);

        System.out.println("\nPostOrder: ");
        postOrderTraversal(root);
    }

    // 前序遍历 - 先根遍历
    // 使用递归, 递归三要素
    // 1. 定义什么时候结束　2. 定义什么时候进行下一步 3. 定义起始状态
    private static void preOrderTraversal(final TreeNode node) {
        if (node == null) return;
        System.out.print(String.format("%d  ", node.val));

        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // 中序遍历 - 中根遍历
    private static void inOrderTraversal(final TreeNode node) {
        if (node == null) return;
        inOrderTraversal(node.left);

        System.out.print(String.format("%d  ", node.val));

        inOrderTraversal(node.right);
    }

    // 后序遍历 - 后跟遍历
    private static void postOrderTraversal(final TreeNode node) {
        if (node == null) return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);

        System.out.print(String.format("%d  ", node.val));
    }
}
