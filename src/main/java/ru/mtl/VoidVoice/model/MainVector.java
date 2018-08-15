package ru.mtl.VoidVoice.model;

import java.util.ArrayList;
import java.util.List;

public class MainVector {
    private ArrayList<Double> vectorNd;

    private List<Double> leftHandVecor;

    private List<Double> rightHandVecor;

    private List<Double> leftFingersListVector;

    private List<Double> rightFingersListVector;

    public MainVector(MotionVector motionVector) {

        leftHandVecor = new ArrayList<>();
        leftHandVecor.addAll(motionVector.getLeftHand().getPalmNormalVector().getCoordinatesList());
        leftHandVecor.addAll(motionVector.getLeftHand().getPalmDirectionVector().getCoordinatesList());
        leftHandVecor.addAll(motionVector.getLeftHand().getPalmVelocity().getCoordinatesList());

        rightHandVecor = new ArrayList<>();
        rightHandVecor.addAll(motionVector.getRightHand().getPalmNormalVector().getCoordinatesList());
        rightHandVecor.addAll(motionVector.getRightHand().getPalmDirectionVector().getCoordinatesList());
        rightHandVecor.addAll(motionVector.getRightHand().getPalmVelocity().getCoordinatesList());

        leftFingersListVector = new ArrayList<>();
        for (Finger finger:
             motionVector.getLeftFingersList()) {
            leftFingersListVector.add(finger.getFingerNum());
            leftFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
        }

        rightFingersListVector = new ArrayList<>();
        for (Finger finger:
                motionVector.getRightFingersList()) {
            rightFingersListVector.add(finger.getFingerNum());
            rightFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
        }

        vectorNd = new ArrayList<>();
        vectorNd.addAll(leftHandVecor);
        vectorNd.addAll(rightHandVecor);
        vectorNd.addAll(leftFingersListVector);
        vectorNd.addAll(rightFingersListVector);
    }

    public ArrayList<Double> getVectorNd() {
        return vectorNd;
    }

}
