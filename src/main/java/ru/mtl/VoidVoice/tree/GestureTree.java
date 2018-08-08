package ru.mtl.VoidVoice.tree;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.dao.FingerDao;
import ru.mtl.VoidVoice.model.ValuableObject;

import java.util.ArrayList;
import java.util.List;

final public class GestureTree {
    private static List<TreeNode> tree;

    @Autowired
    private FingerDao fingerDao;

    public static void generate() {
    }

    public static List<ValuableObject> getKeyPointsByIndex(int index) {
        List<ValuableObject> keyPointList = null;
        if (index < tree.size()) {
            keyPointList = new ArrayList<>();

            for (int i = 0; i < tree.size(); i++) {
                if (i == index) {
                    TreeNode parentTreeNode = tree.get(i).getParent();
                    for (TreeNode childTreeNodes : parentTreeNode.getTreeNodeList()) {
                        keyPointList.add(childTreeNodes.getKeyPoint());
                    }
                    break;
                }
            }
        }
        return keyPointList;
    }
}
