package ru.mtl.VoidVoice.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.MainVector;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;
import ru.mtl.VoidVoice.worker.Presenter;
import ru.mtl.VoidVoice.worker.WorkerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class receives the {@link ru.mtl.VoidVoice.model.KeyPoint} from Leap Motion
 * in {@link GestureDefiner#motionVectorHandler(MotionVector)} or
 * {@link GestureDefiner#motionVectorHandler(Motion)}
 * and iterates over the {@link GestureDefiner#gestureTree} in order to compare
 * every KeyPoint.
 */
public class GestureDefiner implements Presenter {
    private static final Logger LOG = LoggerFactory.getLogger(GestureDefiner.class);
    private static final Marker ITERATOR_MARKER = MarkerFactory.getMarker("ITERATOR_MARKER");

    @Autowired
    private GestureTree gestureTree;

    @Autowired
    private MainVectorTree mainVectorTree;

    private GestureDao gestureDao;

    private int currentLayer = 0;

    private BlockingQueue<MotionVector> queue;

    private GestureDetectedListener gestureDetectedListener;

    public interface GestureDetectedListener {
        void onGestureDetected(Gesture gesture);
    }

    @Autowired
    public GestureDefiner(GestureDetectedListener gestureDetectedListener) {
        this.gestureDetectedListener = gestureDetectedListener;
        gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
        queue = new ArrayBlockingQueue<>(10);
        TreeIterator treeIterator = new TreeIterator();
        treeIterator.setRunning(true);
        treeIterator.start();
    }

    @Override
    public void motionVectorHandler(MotionVector motionVector) {
        queue.add(motionVector);
    }

    @Override
    public void motionVectorHandler(Motion valuableObject) {

    }

    public void dropCurrentIterator() {
        currentLayer = 0;
        queue.clear();
    }

    class TreeIterator extends Thread {

        private boolean isRunning = false;

        @Override
        public void run() {
            int result;
            List<Integer> candidatesIndexes = null;
            while (isRunning) {
                MotionVector motionVector = null;
                try {
                    motionVector = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (motionVector != null && currentLayer == 0) {
                    LOG.debug(ITERATOR_MARKER, "Current layer: "+currentLayer);
                    try {
                        candidatesIndexes = mainVectorTree.getNearestIndexesInLayer(new MainVector(motionVector), currentLayer);
                        LOG.debug(ITERATOR_MARKER, "Nearest KeyPoint's indexes: " + candidatesIndexes);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                } else if (candidatesIndexes != null && candidatesIndexes.size() > 1 && gestureTree.size() - 1 > currentLayer) {
                    List<MotionVector> childMotionVectorList = new ArrayList<>();
                    for (Integer index : candidatesIndexes) {
                        childMotionVectorList.add(gestureTree.getKeyPointsAtLayer(currentLayer).get(index).getBaseVector());
                    }
                    try {
                        candidatesIndexes = mainVectorTree.getNearestIndexesInList(new MainVector(motionVector), childMotionVectorList);
                        LOG.debug(ITERATOR_MARKER, "Nearest KeyPoint's indexes: " + candidatesIndexes);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                } else if (candidatesIndexes != null && !candidatesIndexes.isEmpty()) {
                    int index = 0;

                    KeyPoint lastKeyPoint = gestureTree.getTree().get(currentLayer).get(candidatesIndexes.get(index));
                    LOG.debug("Find the leaf");
                    Gesture gesture = gestureDao.getByKeyPoint(lastKeyPoint);
                    gestureDetectedListener.onGestureDetected(gesture);
                }

                currentLayer++;
            }
        }

        private Long getKeyPointId(int result) {
            KeyPoint keyPoint = gestureTree.getTree().get(currentLayer).get(result);
            return keyPoint != null ? keyPoint.getPrimaryKey() : null;
        }

        void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }
    }
}
