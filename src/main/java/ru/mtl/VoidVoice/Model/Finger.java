package ru.mtl.VoidVoice.Model;

import com.sun.javafx.geom.Vec3d;



@Entity
@Table(name="HIBERNATE_FINGER")
public class Finger {
    @Id
    @GeneratedValue (stategy = GenerationType.AUTO)
    @Column (name="FINGER_ID")
    private int id;

    @Clounm (name = "FINGER_TYPE")
    private FingerType fingerType;
    @Column (name = "FINGER_DIRECTION_VECTOR")
    private Vec3d fingerDirectionVector;
    @Column (name = "FINGER_CURVATURE")
    private double fingerCurvature;

    public Finger(FingerType fingerType, Vec3d fingerDirectionVector, double fingerCurvature){
        this.fingerType = fingerType;
        this.fingerDirectionVector = fingerDirectionVector;
        this.fingerCurvature = fingerCurvature;
    }

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
