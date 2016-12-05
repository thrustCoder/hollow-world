package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rpsin on 11/27/2016.
 */
public class TrieNode <T> {
    private T value;
    private Map<T, TrieNode> children = new HashMap<>();

    public TrieNode() {}

    public TrieNode(T v) {
        value = v;
    }
    public TrieNode(T v, Map<T, TrieNode> c) {
        value = v;
        children = c;
    }

    public T getValue() {
        return value;
    }

    public Map<T, TrieNode> getChildren() {
        return children;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setChildren(Map<T, TrieNode> children) {
        this.children = children;
    }
}
