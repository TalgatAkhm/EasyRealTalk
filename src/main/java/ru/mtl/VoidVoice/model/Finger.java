package ru.mtl.VoidVoice.model;

import com.leapmotion.leap.Vector;
import net.sf.autodao.PersistentEntity;
import ru.mtl.VoidVoice.comparator.Comparable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Finger implements PersistentEntity<Long>, Comparable<Finger> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private FingerType fingerType;

    @OneToOne(cascade = CascadeType.ALL)
    private Vector3d fingerDirectionVector;

    private float fingerCurvature;

    @OneToOne(cascade = CascadeType.ALL)
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
        float prodRes = Vector3d.scalarProduct(palmNormal, fingerDirection);
        fingerCurvature = prodRes / palmNormal.module() / fingerDirection.module();
    }

    public Point3d getFingerTipPosition() {
        return fingerTipPosition;
    }

    public void setFingerTipPosition(Point3d fingerTipPosition) {
        this.fingerTipPosition = fingerTipPosition;
    }

    @Override
    public double compareTo(Finger object) {
        double fingerDirectionSimilarity = fingerDirectionVector.compareTo(object.fingerDirectionVector);
        double fingerTipSimilarity = fingerTipPosition.compareTo(object.fingerTipPosition);

        return isSimilar(fingerDirectionSimilarity, fingerTipSimilarity);
    }

    private double isSimilar(double fingerDirectionSimilarity, double fingerTipSimilarity) {
        return fingerDirectionSimilarity * fingerTipSimilarity;
    }
}
