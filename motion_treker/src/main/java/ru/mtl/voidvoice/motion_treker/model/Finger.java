package ru.mtl.voidvoice.motion_treker.model;

import com.leapmotion.leap.Vector;
import ru.mtl.voidvoice.motion_treker.model.FingerType;
import ru.mtl.voidvoice.motion_treker.model.Point3d;
import ru.mtl.voidvoice.motion_treker.model.Vector3d;

public class Finger {
    private long id;

    private FingerType fingerType;

    private Vector3d fingerDirectionVector;

    private double fingerCurvature;//

    private Point3d fingerTipPosition;//

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
        setCurvature(palmNormal, fingerDirectionVector);
    }


    public Long getPrimaryKey(){
        return this.id;
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

    public void setCurvature(Vector palmNormal, Vector fingerDirection){
        setCurvature(new Vector3d(palmNormal), new Vector3d(fingerDirection));
    }

    public void setCurvature(Vector3d palmNormal, Vector3d fingerDirection){
        double prodRes = Vector3d.scalarProduct(palmNormal, fingerDirection);
        fingerCurvature = prodRes / palmNormal.module() / fingerDirection.module();
    }

    public Point3d getFingerTipPosition() {
        return fingerTipPosition;
    }

    public void setFingerTipPosition(Point3d fingerTipPosition) {
        this.fingerTipPosition = fingerTipPosition;
    }

    private double isSimilar(double fingerDirectionSimilarity, double fingerTipSimilarity) {
        return fingerDirectionSimilarity * fingerTipSimilarity;
    }

    public Double getFingerNum() {
        if (this.fingerType.equals(FingerType.Pinky)) {
            return 1.d;
        } else if (this.fingerType.equals(FingerType.Ring)) {
            return 2.d;
        } else if (this.fingerType.equals(FingerType.Middle)){
            return 3.d;
        } else if (this.fingerType.equals(FingerType.Index)){
            return 4.d;
        } else if(this.fingerType.equals(FingerType.Thumb)){
            return 5.d;
        } else return 0.d;
    }
}
