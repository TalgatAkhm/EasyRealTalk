package ru.mtl.VoidVoice.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.worker.Presenter;

/**
 * This class receives the {@link ru.mtl.VoidVoice.model.KeyPoint} from Leap Motion
 * in {@link GestureDefiner#motionVectorHandler(MotionVector)} or
 * {@link GestureDefiner#motionVectorHandler(Motion)}
 * and iterates over the {@link GestureDefiner#gestureTree} in order to compare
 * every KeyPoint.
 */
public class GestureDefiner implements Presenter {
    private static final Logger LOG = LoggerFactory.getLogger(GestureDefiner.class);

    @Autowired
    private GestureTree gestureTree;

    @Override
    public void motionVectorHandler(MotionVector motionVector) {

    }

    @Override
    public void motionVectorHandler(Motion valuableObject) {

    }
}
