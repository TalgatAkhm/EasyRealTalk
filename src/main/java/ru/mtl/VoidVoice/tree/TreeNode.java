package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.Model.MotionVector;

import java.util.List;

public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> treeNodeList;
    private MotionVector keyPoint;

    public TreeNode(TreeNode parent, List<TreeNode> treeNodeList, MotionVector keyPoint) {
        this.parent = parent;
        this.treeNodeList = treeNodeList;
        this.keyPoint = keyPoint;
    }

    TreeNode getParent() {
        return parent;
    }

    MotionVector getKeyPoint() {
        return keyPoint;
    }

    List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }
}
