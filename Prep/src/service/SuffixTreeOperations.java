package service;

import util.Printer;

/**
 * Created by rpsin on 11/27/2016.
 */
public class SuffixTreeOperations {
    public static void prepSuffixTreeSearch() {
        SuffixTree sTree = new SuffixTree("rajarshi");
        sTree.insert("rashi");

        sTree.printTree();
        Printer.println("Search result: " + sTree.search("rash"));
    }
}
