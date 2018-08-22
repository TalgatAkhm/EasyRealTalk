package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.dao.MotionDao;
import ru.mtl.VoidVoice.dao.MotionVectorDao;
import ru.mtl.VoidVoice.model.*;
import ru.mtl.VoidVoice.tree.GestureTree;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;
import ru.mtl.VoidVoice.utils.DatabaseHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Worker {
    private final static Logger LOG = LoggerFactory.getLogger(Worker.class);

    private MotionDao motionDao;
    private MotionVectorDao motionVectorDao;

    private DatabaseHelper databaseHelper;

    private Frame frame;

    public void run() {
        //TODO::Creating motion vector tree
        // Create a sample listener and controller
        WorkerListener listener = ApplicationContextHolder.getApplicationContext().getBean(WorkerListener.class);
        Controller controller = new Controller();

//        databaseHelper = ApplicationContextHolder.getApplicationContext().getBean(DatabaseHelper.class);
//        databaseHelper.insertHardcodeData("Привет", "current.json");
//        GestureTree gestureTree = ApplicationContextHolder.getApplicationContext().getBean(GestureTree.class);
//        System.out.println(gestureTree.drawTree());
        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
//
//        GestureDao gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
//        List<Gesture> list = gestureDao.getAll();


    }

    public void run2() {
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        MotionVectorDao motionVectorDao = context.getBean(MotionVectorDao.class);
        List<MotionVector> list = motionVectorDao.getAll();

        System.out.println(list.get(0).getRightFingersList().size());
    }

/*    public void run3() {
        WorkerListener listener = ApplicationContextHolder.getApplicationContext().getBean(WorkerListener.class);
        Controller controller = new Controller();
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder stringBuilder = new StringBuilder("///////////////////////////////////////////////////////////////\n");

        for (Hand hand : frame.hands()) {
            if (hand.isRight()) {
                stringBuilder.append("Right hand:\n");
            } else {
                stringBuilder.append("Left hand:\n");
            }

            Vector palmNormalVector = hand.palmNormal();
            Vector palmDirectionVector = hand.direction();
            Vector palmVelocity = hand.palmVelocity();
            float confidence = hand.confidence();

            stringBuilder.append("\tpalmNormalVector: ").append(palmNormalVector).append("\n");
            stringBuilder.append("\tpalmDirectionVector: ").append(palmDirectionVector).append("\n");
            stringBuilder.append("\tpalmVelocity: ").append(palmVelocity).append("\n");
            stringBuilder.append("\tconfidence: ").append(confidence).append("\n");

            stringBuilder.append("Fingers:\n");

            for (Finger finger : hand.fingers()) {
                String fingerType = getStringFingerType(finger.type());
                Vector fingerDirectionVector = finger.direction();
//                fingerCurvature = finger.
                Vector fingerTipPosition = finger.tipPosition();
                float distanceToCenter = fingerTipPosition.distanceTo(new Vector(0f, 0f, 0f));

                stringBuilder.append("\tfingerType: ").append(fingerType).append("\n");
                stringBuilder.append("\tfingerDirectionVector: ").append(fingerDirectionVector).append("\n");
//                stringBuilder.append("\tfingerCurvature: ").append("???").append("\n");
                stringBuilder.append("\tfingerTipPosition: ").append(fingerTipPosition).append("\n");
                stringBuilder.append("\tdistanceToLeapMotion: ").append(distanceToCenter).append("\n\n");
            }
        }


        // Remove the sample listener when done
        controller.removeListener(listener);
    }


        private String getStringFingerType(Finger.Type type){
            switch (type) {
                case TYPE_THUMB:
                    return FingerType.Thumb.name();
                case TYPE_INDEX:
                    return FingerType.Index.name();
                case TYPE_MIDDLE:
                    return FingerType.Middle.name();
                case TYPE_RING:
                    return FingerType.Ring.name();
                case TYPE_PINKY:
                    return FingerType.Pinky.name();
            }

            return FingerType.Index.name();
        }*/
}

