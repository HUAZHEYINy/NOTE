package Trees.Traversal;

import Trees.utilities.TreeNode;
import Trees.utilities.TreeUtilities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BFS {
    public static void main(String[] args) {
        final TreeNode root = TreeUtilities.generateBinaryTree();

        System.out.println("\nBFS");
        BFS(new LinkedList<TreeNode>(List.of(root)));
    }

    public static void BFS(final LinkedList<TreeNode> nodeList) {
        while(!nodeList.isEmpty()) {
            final TreeNode temp = nodeList.pop();
            System.out.print(String.format("%d ", temp.val));

            if (temp.left != null) nodeList.add(temp.left);
            if (temp.right != null) nodeList.add(temp.right);
        }
    }
}
