package ru.mtl.voidvoice.motion_treker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Finger {

    @JsonIgnore
    private long id;

    private FingerType fingerType;

    private Vector3d fingerDirectionVector;

    private float fingerCurvature;

    private Point3d fingerTipPosition;

    public Finger(){

    }

    public Finger(FingerType fingerType, Vector3d fingerDirectionVector, float fingerCurvature){
        this.fingerType = fingerType;
        this.fingerDirectionVector = fingerDirectionVector;
        this.fingerCurvature = fingerCurvature;
    }

    public Finger(FingerType fingertype, Vector3d fingerDirectionVector, Vector3d palmNormal){
        this.fingerType = fingertype;
        this.fingerDirectionVector = fingerDirectionVector;
    }


    public Vector3d getFingerDirectionVector() {
        return fingerDirectionVector;
    }

    public void setFingerDirectionVector(Vector3d fingerDirectionVector) {
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

    public void setFingerCurvature(float fingerCurvature) {
        this.fingerCurvature = fingerCurvature;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point3d getFingerTipPosition() {
        return fingerTipPosition;
    }

    public void setFingerTipPosition(Point3d fingerTipPosition) {
        this.fingerTipPosition = fingerTipPosition;
    }
}
