package service;

import model.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class Tree {
    private List<TreeNode> nodes;

    public Tree(List<TreeNode> n) {
        nodes = n;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public TreeNode getRoot() {
        for (TreeNode node: nodes) {
            if (node.isRoot()) {
                return node;
            }
        }

        nodes.get(0).setRoot(true);
        return nodes.get(0);
    }
}
