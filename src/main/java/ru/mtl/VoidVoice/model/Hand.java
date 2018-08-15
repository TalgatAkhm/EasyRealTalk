package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;
import ru.mtl.VoidVoice.comparator.Comparable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Hand implements PersistentEntity<Long>, Comparable<Hand> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Vector3d palmNormalVector;

    @OneToOne(cascade = CascadeType.ALL)
    private Vector3d palmDirectionVector;

    @OneToOne(cascade = CascadeType.ALL)
    private Vector3d palmVelocity;

    private float confidence;//


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

    @Override
    public double compareTo(Hand object) {

        double palmNormalSimilarity = palmNormalVector.compareTo(object.palmNormalVector);
        double palmDirectionSimilarity = palmDirectionVector.compareTo(object.palmDirectionVector);
        double palmVelocitySimilarity = palmVelocity.compareTo(object.palmVelocity);

        return isSimilar(palmNormalSimilarity, palmDirectionSimilarity, palmVelocitySimilarity);
    }

    // This method defines how similar two hand and returns the measure of the similarity
    private double isSimilar(double palmNormalSimilarity, double palmDirectionSimilarity, double palmVelocitySimilarity) {
        return palmNormalSimilarity * palmDirectionSimilarity * palmVelocitySimilarity;
    }
}
