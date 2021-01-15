package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rpsin on 11/27/2016.
 * Updated by Devarshi on 01/14/2021
 *
 * Ref. https://www.baeldung.com/trie-java
 *
 * The word "I" would look like:
 * Trie {
 *     children = {
 *          { 'I' -> Trie {
 *              children = null,
 *              endOfWord = true
 *              }
 *          }
 *     },
 *     endOfWord = null
 * }
 *
 * The words "I" and "In" would look like:
 * Trie {
 *     children = {
 *          { 'I' -> Trie {
 *              children = {
 *                 { 'n' -> Trie {
 *                      children = null,
 *                      endOfWord = true
 *                      }
 *                 }
 *              },
 *              endOfWord = true
 *              }
 *          }
 *     },
 *     endOfWord = null
 * }
 */
public class TrieNode <T> {
    // value shouldn't be needed IMO
    private T value;

    private Map<T, TrieNode> children = new HashMap<>();
    private Boolean endOfWord;

    public TrieNode() {}

    public TrieNode(T v) {
        value = v;
    }

    public TrieNode(T v, Map<T, TrieNode> c) {
        value = v;
        children = c;
    }

    public TrieNode(Map<T, TrieNode> c, Boolean endOfWord) {
        this.children = c;
        this.endOfWord = endOfWord;
    }

    public T getValue() {
        return value;
    }

    public Boolean getEndOfWord() {
        return endOfWord;
    }

    public Map<T, TrieNode> getChildren() {
        return children;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setEndOfWord(Boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public void setChildren(Map<T, TrieNode> children) {
        this.children = children;
    }
}
