package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;

final public class GestureTree {
    private static List<List<KeyPoint>> tree;

    private GestureDao gestureDao;

    public GestureTree() {
        gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
        generate();
    }

    private void generate() {
        tree = new ArrayList<>();
        int maxGestureSize = 0;
        List<Gesture> gestures = gestureDao.getAll();

        for (Gesture gesture : gestures) {
            if (gesture.getKeyPointList().size() > maxGestureSize) {
                maxGestureSize = gesture.getKeyPointList().size();
            }
        }
        // We expand tree to max size and then add nulls if tree size < gesture size
        for (int i = 0; i < maxGestureSize; i++) {
            tree.add(new ArrayList<>());
        }
        for (Gesture gesture : gestures) {
            for (int layer = 0; layer < maxGestureSize; layer++) {
                if (gesture.getKeyPointList().size() <= layer) {
                    tree.get(layer).add(null);
                } else {
                    tree.get(layer).add(gesture.getKeyPointList().get(layer));
                }
            }
        }
    }

    public static List<List<KeyPoint>> getTree() {
        return tree;
    }
}
