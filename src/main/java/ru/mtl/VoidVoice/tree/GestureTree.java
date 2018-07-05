package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.Model.MotionVector;

import java.util.ArrayList;
import java.util.List;

final public class GestureTree {
    private static List<TreeNode> tree;

    public static void generate() {

    }

    public static List<MotionVector> getKeyPointsByIndex(int index) {
        List<MotionVector> keyPointList = null;
        if (index < tree.size()) {
            keyPointList = new ArrayList<>();

            for(int i = 0; i < tree.size(); i++) {
                if (i == index) {
                    TreeNode parentTreeNode = tree.get(i).getParent();
                    for (TreeNode childTreeNodes: parentTreeNode.getTreeNodeList()) {
                        keyPointList.add(childTreeNodes.getKeyPoint());
                    }
                    break;
                }
            }
        }
        return keyPointList;
    }
}
