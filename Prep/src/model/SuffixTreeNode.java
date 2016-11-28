package model;

import util.Printer;

import java.util.Map;

/**
 * Created by rpsin on 11/27/2016.
 */
public class SuffixTreeNode extends TrieNode<Character> {
    public SuffixTreeNode() {}

    public SuffixTreeNode(Character c) {
        setValue(c);
    }

    public void insert(String s) {
        if (s != null && s.length() > 0) {
            setValue(s.charAt(0));

            SuffixTreeNode child;
            Map<Character, TrieNode> children = getChildren();
            Character value = getValue();

            if (children.containsKey(value)) {
                child = (SuffixTreeNode) children.get(value);
            } else {
                child = new SuffixTreeNode(value);
                children.put(value, child);
            }

            String remainder = s.substring(1);
            child.insert(remainder);
        }
    };

    // found(s) = match(s[0]) + found(s.substring(1))
    public boolean search(String s) {
        if (s == null) {
            return false;
        } else if (s.length() == 0) {
            return true;
        }

        Character c = s.charAt(0);
        Map<Character, TrieNode> children = getChildren();

        if (children.containsKey(c)) {
            SuffixTreeNode child = (SuffixTreeNode) children.get(c);
            return child.search(s.substring(1));
        }

        return false;
    }

    public void print(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        Character c = s.charAt(0);
        Map<Character, TrieNode> children = getChildren();

        if (children.containsKey(c)) {
            Printer.print(" " + c.toString());
            SuffixTreeNode child = (SuffixTreeNode) children.get(c);
            child.print(s.substring(1));
        }
    }
}
