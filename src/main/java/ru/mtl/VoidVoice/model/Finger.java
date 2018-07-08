package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Finger implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private FingerType fingerType;

    @OneToOne
    private Vector3d fingerDirectionVector;

    private double fingerCurvature;

    public Finger(FingerType fingerType, Vector3d fingerDirectionVector, double fingerCurvature){
        this.fingerType = fingerType;
        this.fingerDirectionVector = fingerDirectionVector;
        this.fingerCurvature = fingerCurvature;
    }

    public Finger(){

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

    public void setFingerCurvature(double fingerCurvature) {
        this.fingerCurvature = fingerCurvature;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
