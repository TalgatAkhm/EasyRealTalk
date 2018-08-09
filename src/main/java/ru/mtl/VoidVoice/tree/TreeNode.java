package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.model.KeyPoint;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private List<TreeNode> treeNodeList;
    private KeyPoint keyPoint;

    public TreeNode(KeyPoint keyPoint) {
        this.keyPoint = keyPoint;
        treeNodeList = new ArrayList<>();
    }

    KeyPoint getKeyPoint() {
        return keyPoint;
    }

    List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }
}
