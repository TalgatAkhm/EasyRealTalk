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
    private Vector palmNormalVector;
    @Column
    private Vector palmDirectionVector;
    @Column
    private Vector palmVelocity;

    @Column
    private float confidence;


    public Hand() {

    }


    public Long getPrimaryKey(){
        return this.id;
    }

    public Hand(Vector palmNormalVector, Vector palmDirectionVector) {
        this.palmNormalVector = palmNormalVector;
        this.palmDirectionVector = palmDirectionVector;
    }

    public Vector getPalmNormalVector() {
        return palmNormalVector;
    }

    public void setPalmNormalVector(Vector palmNormalVector) {
        this.palmNormalVector = palmNormalVector;
    }

    public Vector getPalmDirectionVector() {
        return palmDirectionVector;
    }

    public void setPalmDirectionVector(Vector palmDirectionVector) {
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

    public Vector getPalmVelocity() {
        return palmVelocity;
    }

    public void setPalmVelocity(Vector palmVelocity) {
        this.palmVelocity = palmVelocity;
    }
}
