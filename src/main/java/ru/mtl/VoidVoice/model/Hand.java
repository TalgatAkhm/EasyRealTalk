package ru.mtl.VoidVoice.model;

import com.leapmotion.leap.Vector;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

//import com.sun.javafx.geom.Vec3d;
@Entity
public class Hand implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Vector3d palmNormalVector;
    @Column
    private Vector3d palmDirectionVector;
    @Column
    private Vector3d palmVelocity;

    @Column
    private float confidence;


    public Hand() {

    }


    public Long getPrimaryKey(){
        return this.id;
    }

    public Hand(Vector3d palmNormalVector, Vector3d palmDirectionVector) {
        this.palmNormalVector = palmNormalVector;
        this.palmDirectionVector = palmDirectionVector;
    }

    public Vector3d getPalmNormalVector() {
        return palmNormalVector;
    }

    public void setPalmNormalVector(Vector3d palmNormalVector) {
        this.palmNormalVector = palmNormalVector;
    }

    public Vector3d getPalmDirectionVector() {
        return palmDirectionVector;
    }

    public void setPalmDirectionVector(Vector3d palmDirectionVector) {
        this.palmDirectionVector = palmDirectionVector;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public Vector3d getPalmVelocity() {
        return palmVelocity;
    }

    public void setPalmVelocity(Vector3d palmVelocity) {
        this.palmVelocity = palmVelocity;
    }
}
