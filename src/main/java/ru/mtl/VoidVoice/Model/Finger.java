package ru.mtl.VoidVoice.Model;

import com.sun.javafx.geom.Vec3d;
import javax.persistence.*;


@Entity
public class Finger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private FingerType fingerType;
    @Column
    private Vector3d fingerDirectionVector;
    @Column
    private double fingerCurvature;

    public Finger(FingerType fingerType, Vector3d fingerDirectionVector, double fingerCurvature){
        this.fingerType = fingerType;
        this.fingerDirectionVector = fingerDirectionVector;
        this.fingerCurvature = fingerCurvature;
    }

    public Finger(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
