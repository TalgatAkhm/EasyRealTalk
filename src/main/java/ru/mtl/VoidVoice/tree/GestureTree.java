package ru.mtl.VoidVoice.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;

final public class GestureTree {
    private static final Logger LOG = LoggerFactory.getLogger(GestureTree.class);
    private static final Marker EXTEND_INFO_MARKER = MarkerFactory.getMarker("EXTEND_INFO_MARKER");

    private static List<List<KeyPoint>> tree;

    private GestureDao gestureDao;

    public GestureTree() {
        gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
        generate();
        LOG.debug(EXTEND_INFO_MARKER, drawTree());
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

    public List<List<KeyPoint>> getTree() {
        return tree;
    }

    public List<KeyPoint> getKeyPointsAtLayer(int layerNumber) {
        return tree.get(layerNumber);
    }

    public String drawTree() {
        StringBuilder builder = new StringBuilder("Gestures: ");
        List<Gesture> gestures = gestureDao.getAll();

        for (Gesture gesture : gestures) {
            builder.append(gesture.getMeaning()).append(" ");
        }

        builder.append("\n");

        int layer = 1;
        for (List<KeyPoint> layerList : tree) {
            builder.append("Layer ").append(layer).append(": ");
            for (KeyPoint keyPoint : layerList) {
                builder.append(keyPoint.getPrimaryKey()).append(" ");
            }
            builder.append("\n");
            layer++;
        }

        return builder.toString();
    }

    public int size() {
        return tree.size();
    }
}
