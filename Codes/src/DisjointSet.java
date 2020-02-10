import java.util.*;

/**
 * Disjoint set
 */
public class DisjointSet {
    public static void main(String[] args) {
        HashMapImple();
    }

    public static void HashMapImple() {
        List<List<String>> edges = new ArrayList<>();
        String[][] edgeArrs = {{"a", "c"}, {"a", "b"}, {"c", "d"}, {"b", "d"}, {"e", "f"}, {"e", "g"}, {"h", "i"}, {"j", "j"}};
        for (String[] edgeArr : edgeArrs) {
            edges.add(Arrays.asList(edgeArr));
        }

        Map<String, List<String>> sets = makeSet();

        for (List<String> edge : edges) {
            if (findSet(edge.get(0), sets).equals(findSet(edge.get(1), sets))) {
                continue;
            } else {
                union(findSet(edge.get(0), sets), findSet(edge.get(1), sets), sets);
            }
        }

        System.out.println(sets);
    }

    /**
     * Make set so that each key is the representative ele; the value is the set elements.
     */
    public static Map<String, List<String>> makeSet() {
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

        Map<String, List<String>> sets = new HashMap<>();
        for (String str : arr) {
            sets.computeIfAbsent(str, key -> new ArrayList<>()).add(str);
        }

        return sets;
    }

    /**
     * Return the representative element of the set of the input string
     */
    public static String findSet(String input, Map<String, List<String>> sets) {
        if (sets.containsKey(input)) {
            return input;
        }

        for (String key : sets.keySet()) {
            if (sets.get(key).contains(input)) {
                return key;
            }
        }

        return input;
    }

    /**
     * Unite the set that contains vertex1 and the set that contains vertex2.
     */
    public static void union(String vertex1, String vertex2, Map<String, List<String>> sets) {
        sets.get(vertex2).addAll(sets.get(vertex1));
        sets.remove(vertex1);
    }
}
