package Trees.Others;

import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    public void insert(final String word) {
        Map<Character, TrieNode> children = root.getChildren();
        for (char ele : word.toCharArray()) {
            if (children.containsKey(ele)) {
                // the element exists in the children already.
                TrieNode next = children.get(ele);
                children = next.getChildren();
            } else {
                // the element is not one of the children, add it.
                TrieNode next = new TrieNode();
                children.put(ele, next);
                children = next.getChildren();
            }
        }

        // Special node that used to mark the end of the word.
        // e.g APPLE and APP share the same prefix APP and we should
        // have a way to distinguish them.
        children.put('.', new TrieNode());
    }

    /**
     * Method that search the input word - exact match.
     * @param word
     * @return true when we found a string exactly matches the input word.
     */
    public boolean search(final String word) {
        Map<Character, TrieNode> children = root.getChildren();
        for (char ele : word.toCharArray()) {
            if (children.containsKey(ele)) {
                // Found it and then continue to search.
                children = children.get(ele).getChildren();
            } else {
                return false;
            }
        }

        // Ensure the content in the Trie is a String not a prefix.
        if (children.containsKey('.')) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Search prefix - partial match.
     * @param prefix
     * @return true when found prefix in the trie.
     */
    public boolean startsWith(final String prefix) {
        Map<Character, TrieNode> children = root.getChildren();
        for (char ele : prefix.toCharArray()) {
            if (children.containsKey(ele)) {
                // Found it and then continue to search.
                children = children.get(ele).getChildren();
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        final Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true

    }
}
