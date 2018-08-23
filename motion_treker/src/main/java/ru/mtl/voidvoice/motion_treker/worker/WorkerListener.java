package ru.mtl.voidvoice.motion_treker.worker;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import ru.mtl.voidvoice.motion_treker.model.FingerType;
import ru.mtl.voidvoice.motion_treker.model.MotionVector;
import ru.mtl.voidvoice.motion_treker.model.Point3d;
import ru.mtl.voidvoice.motion_treker.model.Vector3d;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

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

    String showFrameData() {
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
        return stringBuilder.toString();
    }

    MotionVector getMotionVector() {
        MotionVector result = new MotionVector();

        result.setLeftHand(new ru.mtl.voidvoice.motion_treker.model.Hand());
        result.setRightHand(new ru.mtl.voidvoice.motion_treker.model.Hand());
        result.setLeftHandMotion(null);
        result.setRightHandMotion(null);

        for (Hand hand : frame.hands()) {
            ru.mtl.voidvoice.motion_treker.model.Hand currentHand;
            if (hand.isRight()) {
                currentHand = result.getRightHand();
            } else {
                currentHand = result.getLeftHand();
            }

            currentHand.setPalmDirectionVector(getVector3d(hand.palmNormal()));
            currentHand.setPalmNormalVector(getVector3d(hand.palmNormal()));
            currentHand.setPalmVelocity(getVector3d(hand.palmVelocity()));
            currentHand.setConfidence(hand.confidence());

            List<ru.mtl.voidvoice.motion_treker.model.Finger> fingers = new ArrayList<ru.mtl.voidvoice.motion_treker.model.Finger>();

            for (Finger finger : hand.fingers()) {
                ru.mtl.voidvoice.motion_treker.model.Finger mFinger = new ru.mtl.voidvoice.motion_treker.model.Finger();
                mFinger.setFingerType(getFingerType(finger.type()));
                mFinger.setFingerDirectionVector(getVector3d(finger.direction()));
                mFinger.setFingerTipPosition(getPoint3d(finger.tipPosition()));
                fingers.add(mFinger);
            }

            if (hand.isRight()) {
                result.setRightFingersList(fingers);
            } else {
                result.setLeftFingersList(fingers);
            }
        }
//        result.setTouchList(createTouchList(result));
        return result;
    }

    private static final int MAX_AFFORTABLE_DISTANCE = 1;

    private List<List<Double>> createTouchList(MotionVector motionVector) {
        List<List<Double>> layerList = new ArrayList<List<Double>>();
        //в случае одной руки layerList содержит 5 элементов(touchList). Первый layer содержит один элемент(соприкосновение 1го и 1го пальцев(Pinky)),
        // второй - два эл-та соприкосновения 2го пальца с 1ым и 2ым, и так еще три слоя.
        if (motionVector.getLeftFingersList().isEmpty() || motionVector.getRightFingersList().isEmpty()) {
            throw new IllegalArgumentException("somehow MotionLeap has sent frame without hands");
        } else if (motionVector.getLeftFingersList().isEmpty()) {
            for (int i = 0; i < motionVector.getRightFingersList().size(); i++) {
                List touchList = new ArrayList<Double>();
                for (int j = 0; j < i + 1; j++) {
                    touchList.add(checkTouch(motionVector.getRightFingersList().get(i).getFingerTipPosition(), motionVector.getRightFingersList().get(j).getFingerTipPosition()));
                }
                layerList.add(touchList);
            }
        } else if (motionVector.getRightFingersList().isEmpty()) {
            for (int i = 0; i < motionVector.getRightFingersList().size(); i++) {
                List touchList = new ArrayList<Double>();
                for (int j = 0; j < i + 1; j++) {
                    touchList.add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getLeftFingersList().get(j).getFingerTipPosition()));
                }
                layerList.add(touchList);
            }
        } else {
            for (int i = 0; i < motionVector.getLeftFingersList().size(); i++) {
                List touchList = new ArrayList<Double>();
                for (int j = 0; j < motionVector.getLeftFingersList().size(); j++) {
                    touchList.add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getLeftFingersList().get(j).getFingerTipPosition()));
                    layerList.add(touchList);
                }
            }
            layerList.get(1).set(0, checkTouch(motionVector.getRightFingersList().get(4).getFingerTipPosition(), motionVector.getRightFingersList().get(5).getFingerTipPosition()));
            layerList.get(2).set(0, checkTouch(motionVector.getRightFingersList().get(3).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(2).set(1, checkTouch(motionVector.getRightFingersList().get(3).getFingerTipPosition(), motionVector.getRightFingersList().get(5).getFingerTipPosition()));
            layerList.get(3).set(0, checkTouch(motionVector.getRightFingersList().get(2).getFingerTipPosition(), motionVector.getRightFingersList().get(3).getFingerTipPosition()));
            layerList.get(3).set(1, checkTouch(motionVector.getRightFingersList().get(2).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(3).set(2, checkTouch(motionVector.getRightFingersList().get(2).getFingerTipPosition(), motionVector.getRightFingersList().get(5).getFingerTipPosition()));
            layerList.get(4).set(0, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(2).getFingerTipPosition()));
            layerList.get(4).set(1, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(3).getFingerTipPosition()));
            layerList.get(4).set(2, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(4).set(3, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(5).getFingerTipPosition()));

            for (int i = 0; i < motionVector.getLeftFingersList().size(); i++) {
                for (int j = 0; j < motionVector.getRightFingersList().size(); j++) {
                    layerList.get(i).add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getRightFingersList().get(i).getFingerTipPosition()));
                }
            }
        }
        return layerList;
    }

    private double checkTouch(Point3d point1, Point3d point2) {
        // если точки совпадают, возвращаем -1, если между ними расстояние меньше MAX_AFFORTABLE_DISTANCE,
        // то возвращаем 1(касание), если больше, то 0.
        if (point1.equals(point2)) {
            return -1.d;
        } else if (point1.distance(point2) < MAX_AFFORTABLE_DISTANCE) {
            return 1.d;
        } else {
            return 0.d;
        }
    }

    private Vector3d getVector3d(Vector vector) {
        return new Vector3d(vector.getX(), vector.getY(), vector.getZ());
    }

    private Point3d getPoint3d(Vector vector) {
        return new Point3d(vector.getX(), vector.getY(), vector.getZ());
    }

    private FingerType getFingerType(Finger.Type type) {
        switch (type) {
            case TYPE_THUMB:
                return FingerType.Thumb;
            case TYPE_INDEX:
                return FingerType.Index;
            case TYPE_MIDDLE:
                return FingerType.Middle;
            case TYPE_RING:
                return FingerType.Ring;
            case TYPE_PINKY:
                return FingerType.Pinky;
        }

        return FingerType.Index;
    }

    private String getStringFingerType(Finger.Type type) {
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
    }
}
