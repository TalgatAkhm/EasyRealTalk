package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.model.ValuableObject;

import java.util.List;

public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> treeNodeList;
    private ValuableObject keyPoint;

    public TreeNode(TreeNode parent, List<TreeNode> treeNodeList, ValuableObject keyPoint) {
        this.parent = parent;
        this.treeNodeList = treeNodeList;
        this.keyPoint = keyPoint;
    }

    TreeNode getParent() {
        return parent;
    }

    ValuableObject getKeyPoint() {
        return keyPoint;
    }

    List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }
}
