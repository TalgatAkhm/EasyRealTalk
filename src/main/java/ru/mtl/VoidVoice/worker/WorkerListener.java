package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.*;
import ru.mtl.VoidVoice.comparator.Compare;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.tree.GestureTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class WorkerListener extends Listener implements Presenter {
    private static final double SUCCESS_COMPARE_MIN_VALUE = 0.4;
    private int treeLayer = 0;
    private MotionVector resultMotionVector = null;
    private int timePassed = 0;
    private float currentTimeStamp = -1;

    @Override
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    @Override
    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        if (currentTimeStamp == -1) {
            currentTimeStamp = frame.timestamp();
        }
        timePassed = (int) ((frame.timestamp() - currentTimeStamp) / Math.pow(10, 6));
        if (timePassed == 2) {
            //TODO:: make normal timer
            System.out.println("2 seconds passed");
            if (resultMotionVector != null) {
                //print result
            }
            treeLayer = 0;
            timePassed = 0;
        }
    }

    @Override
    public void motionVectorHandler(MotionVector motionVector) {
        List<MotionVector> keyPoints = GestureTree.getKeyPointsByIndex(treeLayer);
        if (keyPoints == null) { //So the last layer passed or no data in database
            if (resultMotionVector != null) { //We got our result
                // echo result
                System.out.println("Result found");
            }
            return;
        }
        List<Double> comparisons = new ArrayList<>();
        for (MotionVector keyPoint : keyPoints) {
            comparisons.add(Compare.compareMotionVectors(motionVector, keyPoint));
        }
        double max = Collections.max(comparisons);
        if (max >= SUCCESS_COMPARE_MIN_VALUE) {
            treeLayer++;
            for (int i = 0; i < comparisons.size(); i++) {
                if (comparisons.get(i) == max) {
                    resultMotionVector = keyPoints.get(i);
                    return;
                }
            }
        }
    }
}