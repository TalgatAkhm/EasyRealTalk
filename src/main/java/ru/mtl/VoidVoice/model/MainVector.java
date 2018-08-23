package ru.mtl.VoidVoice.model;

import java.util.ArrayList;
import java.util.List;


public class MainVector {
    private List<Double> vectorNd;

    private MainVectorType mainVectorType;

    private List<Finger> leftFingerList;

    private List<Finger> rightFingerList;

    private Hand leftHand;

    private Hand rightHand;

    private List<Double> leftHandVecor;

    private List<Double> rightHandVecor;

    private List<Double> leftFingersListVector;

    private List<Double> rightFingersListVector;

    private List<Double> touchListVector;

    private List<List<Double>> touchList;

    public MainVector(MotionVector motionVector) {

        if (motionVector.getLeftFingersList().isEmpty() && motionVector.getRightFingersList().isEmpty()) {
            throw new IllegalArgumentException("Both finger lists are empty");
        } else if (motionVector.getLeftFingersList().isEmpty()) {
            mainVectorType = MainVectorType.OnlyRightHand;

            rightHandVecor = new ArrayList<>();
            rightHandVecor.addAll(motionVector.getRightHand().getPalmNormalVector().getCoordinatesList());
            rightHandVecor.addAll(motionVector.getRightHand().getPalmDirectionVector().getCoordinatesList());
            rightHandVecor.addAll(motionVector.getRightHand().getPalmVelocity().getCoordinatesList());

            rightFingersListVector = new ArrayList<>();
            for (Finger finger :
                    motionVector.getRightFingersList()) {
                rightFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
            }

            // получится вектор длины 10.
            touchListVector = new ArrayList<>();
            for (int i = 0; motionVector.getTouchList() != null && i < motionVector.getTouchList().size(); i++) {
                for (int j = 0; j < motionVector.getTouchList().get(i).size() - 1; j++) {
                    touchListVector.add(motionVector.getTouchList().get(i).get(j));
                }
            }

            leftHandVecor = null;
            leftFingersListVector = null;

            vectorNd = new ArrayList<>();
            vectorNd.addAll(rightHandVecor);
            vectorNd.addAll(rightFingersListVector);
            vectorNd.addAll(touchListVector);

            this.rightHand = motionVector.getRightHand();
            this.rightFingerList = motionVector.getRightFingersList();
            this.touchList = motionVector.getTouchList();
        } else if (motionVector.getRightFingersList().isEmpty()) {
            mainVectorType = MainVectorType.OnlyLeftHand;

            leftHandVecor = new ArrayList<>();
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmNormalVector().getCoordinatesList());
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmDirectionVector().getCoordinatesList());
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmVelocity().getCoordinatesList());

            leftFingersListVector = new ArrayList<>();
            for (Finger finger :
                    motionVector.getLeftFingersList()) {
                leftFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
            }

            // получится вектор длины 10.
            touchListVector = new ArrayList<>();
            for (int i = 0; motionVector.getTouchList() != null && i < motionVector.getTouchList().size(); i++) {
                for (int j = 0; j < motionVector.getTouchList().get(i).size() - 1; j++) {
                    touchListVector.add(motionVector.getTouchList().get(i).get(j));
                }
            }

            rightHandVecor = null;
            rightFingersListVector = null;

            vectorNd = new ArrayList<>();
            vectorNd.addAll(leftHandVecor);
            vectorNd.addAll(leftFingersListVector);
            vectorNd.addAll(touchListVector);

            this.leftHand = motionVector.getLeftHand();
            this.leftFingerList = motionVector.getLeftFingersList();
            this.touchList = motionVector.getTouchList();
        } else {
            mainVectorType = MainVectorType.BothHands;

            leftHandVecor = new ArrayList<>();
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmNormalVector().getCoordinatesList());
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmDirectionVector().getCoordinatesList());
            leftHandVecor.addAll(motionVector.getLeftHand().getPalmVelocity().getCoordinatesList());

            rightHandVecor = new ArrayList<>();
            rightHandVecor.addAll(motionVector.getRightHand().getPalmNormalVector().getCoordinatesList());
            rightHandVecor.addAll(motionVector.getRightHand().getPalmDirectionVector().getCoordinatesList());
            rightHandVecor.addAll(motionVector.getRightHand().getPalmVelocity().getCoordinatesList());

            leftFingersListVector = new ArrayList<>();
            for (Finger finger :
                    motionVector.getLeftFingersList()) {
                leftFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
            }

            rightFingersListVector = new ArrayList<>();
            for (Finger finger :
                    motionVector.getRightFingersList()) {
                rightFingersListVector.addAll(finger.getFingerDirectionVector().getCoordinatesList());
            }

            //получится вектор длины 50.
            touchListVector = new ArrayList<>();
            for (List<Double> layer :
                    motionVector.getTouchList()) {
                touchListVector.addAll(layer);
            }

            vectorNd = new ArrayList<>();
            vectorNd.addAll(leftHandVecor);
            vectorNd.addAll(rightHandVecor);
            vectorNd.addAll(leftFingersListVector);
            vectorNd.addAll(rightFingersListVector);
            vectorNd.addAll(touchListVector);

            this.rightHand = motionVector.getRightHand();
            this.rightFingerList = motionVector.getRightFingersList();
            this.leftHand = motionVector.getLeftHand();
            this.leftFingerList = motionVector.getLeftFingersList();
            this.touchList = motionVector.getTouchList();

        }

        for (int i = 0; i < touchListVector.size(); i++) {
            touchListVector.set(i, 0d);
        }
    }

    //    если нужно получить значение соприкосновения любых двух пальцев
    //     нумерация: левый мезинец 0; левый большой 4; правый мезинец 5; правый большой 9.
    Double getTouchOfIandJfingers(int i, int j) {
        if (this.mainVectorType.equals(MainVectorType.OnlyLeftHand) || this.mainVectorType.equals(MainVectorType.OnlyRightHand)) {
            if (i == j) {
                return -1.d;
            } else if (i > j) {
                return this.touchList.get(i).get(j);
            } else {
                return this.touchList.get(j).get(i);
            }
        } else {
            if (i == j) {
                return -1.d;
            } else if (i > j) {
                if (j <= 4) {
                    return this.touchList.get(j).get(i);
                } else {
                    return this.touchList.get(9 - j).get(i - j - 1);

                }
            } else {
                if (i <= 4) {
                    return this.touchList.get(i).get(j);
                } else {
                    return this.touchList.get(9 - i).get(j - i - 1);
                }
            }
        }
    }


    public List<Double> getVectorNd() {
        return vectorNd;
    }

    public MainVectorType getType() {
        return mainVectorType;
    }

    public List<Double> getTouchListVector() {
        return touchListVector;
    }

    public MainVectorType getMainVectorType() {
        return mainVectorType;
    }

    public List<Double> getLeftHandVecor() {
        return leftHandVecor;
    }

    public List<Double> getRightHandVecor() {
        return rightHandVecor;
    }

    public List<Double> getLeftFingersListVector() {
        return leftFingersListVector;
    }

    public List<Double> getRightFingersListVector() {
        return rightFingersListVector;
    }

    public List<Finger> getLeftFingerList() {
        return leftFingerList;
    }

    public List<Finger> getRightFingerList() {
        return rightFingerList;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public Hand getRightHand() {
        return rightHand;
    }
}
