package Trees.utilities;

import java.util.LinkedList;

/**
 * Class that has all helper methods for tree.
 */
public class TreeUtilities {
    // Generate a perfect binary Tree with BFS
    // 25, 15, 50, 10, 22, 35, 70, 4, 12, 18, 24, 31, 44, 66, 90
    public static TreeNode generateBinaryTree() {
        final TreeNode[] nodes = new TreeNode[15];
        final int[] nodeValues = {25, 15, 50, 10, 22, 35, 70, 4, 12, 18, 24, 31, 44, 66, 90};

        // Instantiate the tree node.
        for (int i = 0; i < nodeValues.length; i++) {
            nodes[i] = new TreeNode(nodeValues[i]);
        }

        // Build the parent-child relationship.
        // Max num of parent nodes are num of nodes / 2.
        for (int i = 0; i < nodes.length / 2; i++) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            nodes[i].left = nodes[leftIndex];
            nodes[i].right = nodes[rightIndex];
        }

        return nodes[0];
    }
}
