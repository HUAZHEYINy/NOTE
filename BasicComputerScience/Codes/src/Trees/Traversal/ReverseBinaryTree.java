package Trees.Traversal;

import Trees.utilities.TreeNode;
import Trees.utilities.TreeUtilities;

import java.util.LinkedList;
import java.util.List;

public class ReverseBinaryTree {
    public static void main(String[] args) {
        final TreeNode root = TreeUtilities.generateBinaryTree();

        System.out.println("Before reverse: ");
        BFS.BFS(new LinkedList<>(List.of(root)));

        System.out.println("\nAfter reverse: ");
        reverseBinaryTree(root);
        BFS.BFS(new LinkedList<TreeNode>(List.of(root)));
    }

    // 大名鼎鼎的反转二叉树
    private static void reverseBinaryTree(final TreeNode root) {
        if (root == null) return;
        final TreeNode left = root.left;
        final TreeNode right = root.right;
        root.left = right;
        root.right = left;

        reverseBinaryTree(left);
        reverseBinaryTree(right);
    }
}
