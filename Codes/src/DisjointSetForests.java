import java.util.ArrayList;
import java.util.List;

public class DisjointSetForests {
    public static void main(String[] args) {
        List<Node> nodes = makeSet();
    }

    /**
     * Unite two trees such that the tree with lower rank points to the tree with higher rank.
     */
    public static void union(Node x, Node y) {
        link(findSet(x), findSet(y));
    }

    public static void link(Node x, Node y) {
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;
            y.rank += 1;
        }
    }

    /**
     * Find the root of tree which x resides in;
     * Also do path compression such that all child node points to the root of the tree.
     */
    public static Node findSet(Node x) {
        if (x.parent != x) {
            x.parent = findSet(x.parent);
        }

        return x.parent;
    }

    /**
     * Make x number of distinct node which each has rank 0; parent points to itself.
     */
    public static List<Node> makeSet() {
        List<Node> result = new ArrayList<>();
        String[] arr = {"a", "b", "c", "d", "e", "f"};
        for (String str : arr) {
            result.add(new Node(str, 0));
        }

        return result;
    }

    public static class Node {
        String val;
        int rank;
        Node parent;

        public Node(String val, int rank) {
            this.val = val;
            this.rank = rank;
            this.parent = this;
        }
    }
}
