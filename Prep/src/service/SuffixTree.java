package service;

import model.SuffixTreeNode;
import util.Printer;

/**
 * Created by rpsin on 11/27/2016.
 */
public class SuffixTree {
    private String baseString;

    SuffixTreeNode root = new SuffixTreeNode();

    public SuffixTree(String s) {
        baseString = s;
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            root.insert(suffix);
        }
    }

    public void insert(String s) {
        root.insert(s);
    }

    public boolean search(String s) {
        return root.search(s);
    }

    public void printTree () {
        Printer.println("-- Suffix tree one path --");
        root.print(baseString);
    }
}
