package Trees.Others;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;

    public TrieNode() {
        this.children = new HashMap<>();
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}
