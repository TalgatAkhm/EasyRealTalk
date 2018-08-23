package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.tree.AverageMotionVectorGenerator;
import ru.mtl.VoidVoice.tree.GestureDefiner;

public class WorkerListener extends Listener implements GestureDefiner.GestureDetectedListener {
    private static final Logger LOG = LoggerFactory.getLogger(GestureDefiner.class);

    // Time for the one gesture
    private final static long ONE_GESTURE_TIME = 2000;
    // Time between the frames, which sends to AverageMotionVectorGenerator
    private final static long FRAME_DELAY = 100;

    @Autowired
    private AverageMotionVectorGenerator averageMotionVectorGenerator;

    @Autowired
    private GestureDefiner gestureDefiner;

    private long currentMillis = -1;

    private long currentMillisFrame = System.currentTimeMillis();

    private long currentGestureTime = 0;

    @Override
    public void onInit(Controller controller) { LOG.debug("Initialized");
    }

    @Override
    public void onConnect(Controller controller) {
        LOG.debug("Connected");
    }

    @Override
    public void onDisconnect(Controller controller) {
        LOG.debug("Disconnected");
    }

    @Override
    public void onExit(Controller controller) {
        LOG.debug("Exited");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        if (System.currentTimeMillis() - currentMillisFrame >= FRAME_DELAY) {
            averageMotionVectorGenerator.addFrame(frame);
            currentMillisFrame = System.currentTimeMillis();
        }

        if (currentMillis == -1) {
            currentMillis = System.currentTimeMillis();
            currentGestureTime++;
//            LOG.debug("New try to detect a gesture: "+currentGestureTime);
        }

        if (System.currentTimeMillis() - currentMillis >= ONE_GESTURE_TIME) {
            LOG.debug("New try to detect a gesture: "+ ++currentGestureTime);
            currentMillis = System.currentTimeMillis();
            LOG.debug("Drop current gesture definer");
            gestureDefiner.dropCurrentIterator();
        }
    }

    @Override
    public void onGestureDetected(Gesture gesture) {
        LOG.debug("Gesture is "+gesture.getMeaning());
        currentMillis = System.currentTimeMillis();
        gestureDefiner.dropCurrentIterator();
    }
}