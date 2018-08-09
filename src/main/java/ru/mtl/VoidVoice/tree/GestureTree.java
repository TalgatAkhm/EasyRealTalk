package ru.mtl.VoidVoice.tree;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.dao.*;
import ru.mtl.VoidVoice.model.*;

import java.util.ArrayList;
import java.util.List;

final public class GestureTree {
    private static List<List<TreeNode>> tree;

    @Autowired
    private GestureDao gestureDao;

    public void generate() {
        tree = new ArrayList<>();
        tree.add(new ArrayList<>());
        
        List<Gesture> gestures = gestureDao.getAll();
        // Go through all gestures in DB
        for (Gesture gesture : gestures) {
            List<KeyPoint> keyPoints = gesture.getKeyPointList();
            int treeLayer = 0;
            while (true) {
                List<KeyPoint> treeKeyPoints = getKeyPointsByIndex(treeLayer);
                boolean continued = false;
                // Go through tree layer and check if there is the same keypoint
                for (KeyPoint treeKeyPoint : treeKeyPoints) {
                    if (treeKeyPoint.getPrimaryKey().equals(keyPoints.get(treeLayer).getPrimaryKey())) {
                        continued = true;
                    }
                }
                // Then, if found, go further
                if (continued) {
                    treeLayer++;
                    // Else break and insert remaining branch
                } else {
                    break;
                }
            }
            // If no matches
            if (treeLayer == 0) {
                tree.get(0).add(new TreeNode(keyPoints.get(0)));
                treeLayer++;
            }
            // Because at this treeLayer there is no matches
            treeLayer--;
            while (treeLayer < keyPoints.size()) {
                for (TreeNode treeNode : tree.get(treeLayer)) {
                    // If we found the same keypoints, then continue this branch
                    if (treeNode.getKeyPoint().getPrimaryKey().equals(keyPoints.get(treeLayer).getPrimaryKey())) {
                        treeNode.getTreeNodeList().add(new TreeNode(keyPoints.get(treeLayer + 1)));
                        treeLayer++;
                        break;
                    }
                }
            }
        }
    }

    public List<KeyPoint> getKeyPointsByIndex(int index) {
        List<KeyPoint> keyPointList = null;
        if (index < tree.size()) {
            keyPointList = new ArrayList<>();

            for (TreeNode child : tree.get(index)) {
                keyPointList.add(child.getKeyPoint());
            }
        }
        return keyPointList;
    }
}
