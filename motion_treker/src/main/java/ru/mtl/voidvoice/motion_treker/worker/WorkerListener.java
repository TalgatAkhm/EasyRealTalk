package ru.mtl.voidvoice.motion_treker.worker;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

import static com.leapmotion.leap.Finger.Type.TYPE_THUMB;

class WorkerListener extends Listener {
    private final static long DELAY_MILLIS = 500;

    private int treeLayer = 0;
    private float timePassed = 0;
    private long currentTimeStamp = -1;

    private Frame frame;

    WorkerListener() {
        currentTimeStamp = System.currentTimeMillis();
    }

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
        long timeNow = System.currentTimeMillis();

        if (timeNow - currentTimeStamp >= DELAY_MILLIS) {
            frame = controller.frame();
            currentTimeStamp = timeNow;
        }
    }

    void showFrameData() {
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

        stringBuilder.append("\n///////////////////////////////////////////////////////////////");
        System.out.println(stringBuilder.toString());
    }

    private String getStringFingerType(Finger.Type type) {
        switch (type) {
            case TYPE_THUMB: return "Thumb";
            case TYPE_INDEX: return "Index";
            case TYPE_MIDDLE: return "Middle";
            case TYPE_RING: return "Ring";
            case TYPE_PINKY: return "Pinky";
        }

        return "";
    }
}
