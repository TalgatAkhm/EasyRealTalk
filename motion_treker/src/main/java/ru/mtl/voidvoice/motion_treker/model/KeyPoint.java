package ru.mtl.voidvoice.motion_treker.model;

public class KeyPoint {
    private long id;

    private MotionVector baseVector;

    private Motion baseMotion;

    public KeyPoint() {

    }

    public MotionVector getBaseVector() {
        return baseVector;
    }

    public void setBaseVector(MotionVector baseVector) {
        this.baseVector = baseVector;
    }

    public Motion getBaseMotion() {
        return baseMotion;
    }

    public void setBaseMotion(Motion baseMotion) {
        this.baseMotion = baseMotion;
    }

}
