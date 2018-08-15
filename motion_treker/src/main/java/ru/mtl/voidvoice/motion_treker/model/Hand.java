package ru.mtl.voidvoice.motion_treker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Hand {
    @JsonIgnore
    private long id;

    private Vector3d palmNormalVector;

    private Vector3d palmDirectionVector;

    private Vector3d palmVelocity;

    private float confidence;


    public Hand() {

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
