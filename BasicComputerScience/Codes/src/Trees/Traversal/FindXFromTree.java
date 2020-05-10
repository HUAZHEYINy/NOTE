package Trees.Traversal;

import Trees.utilities.TreeNode;
import Trees.utilities.TreeUtilities;

import java.util.LinkedList;

public class FindXFromTree {
    public static void main(String[] args) {
        final TreeNode root = TreeUtilities.generateBinaryTree();
        int x = 44;
        System.out.println(String.format("\n Is x num %d in the Tree? (BFS solution)  %s", x, isXInTheTreeBFS(root, x)));

        System.out.println(String.format("\n Is x num %d in the Tree? (DFS solution)  %s", x, isXInTheTreeDFS(root, x)));

        System.out.println(String.format("\n x num %d has height %d in the Tree? ", x, xHasHeight(root, x, 0)));

        System.out.println(String.format("\n x num %d has parent %d in the Tree? ", x, findXParent(root, x)));
    }

    // BFS - find x in the tree or not.
    private static boolean isXInTheTreeBFS(final TreeNode node, final int x) {
        final LinkedList<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(node);

        while(!nodeList.isEmpty()) {
            final TreeNode temp = nodeList.pop();

            if (temp.val == x) return true;

            if (temp.left != null) nodeList.add(temp.left);
            if (temp.right != null) nodeList.add(temp.right);
        }

        return false;
    }

    // DFS - find x in the tree
    // 递归: 1. 定义什么时候停止 2. 定义什么时候进行下一步 3. 定义初始状态.
    private static boolean isXInTheTreeDFS(final TreeNode node, final int x) {
        if (node == null) return false;
        if (node.val == x) return true;

        boolean left = isXInTheTreeDFS(node.left, x);
        boolean right = isXInTheTreeDFS(node.right, x);

        return left || right;
    }

    // Find the height of x from the tree - given x is in the tree for sure.
    private static int xHasHeight(final TreeNode node, final int x, final int height) {
        if (node == null) return -1;
        if (node.val == x) return height;

        int leftHeight = xHasHeight(node.left, x, height + 1);
        if (leftHeight != -1) return leftHeight;

        int rightHeight = xHasHeight(node.right, x, height + 1);
        if (rightHeight != -1) return rightHeight;

        return -1;
    }

    // Find the parent of x from the tree.
    private static int findXParent(final TreeNode node, final int x) {
        if (node == null) return -1;
        if (node.left != null && node.left.val == x) return node.val;
        if (node.right != null && node.right.val == x) return node.val;

        int left = findXParent(node.left, x);
        if (left != -1) return left;

        int right = findXParent(node.right, x);
        if (right != -1) return right;

        return -1;
    }
}
