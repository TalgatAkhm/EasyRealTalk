package ru.mtl.VoidVoice.Model;

import com.sun.javafx.geom.Vec3d;

public class Finger {
    private Vec3d fingerDirectionVector;
    private FingerType fingerType;
    private double fingerCurvature;

    public Finger(){

    }

    public Vec3d getFingerDirectionVector() {
        return fingerDirectionVector;
    }

    public void setFingerDirectionVector(Vec3d fingerDirectionVector) {
        this.fingerDirectionVector = fingerDirectionVector;
    }

    public FingerType getFingerType() {
        return fingerType;
    }

    public void setFingerType(FingerType fingerType) {
        this.fingerType = fingerType;
    }

    public double getFingerCurvature() {
        return fingerCurvature;
    }

    public void setFingerCurvature(double fingerCurvature) {
        this.fingerCurvature = fingerCurvature;
    }
}
