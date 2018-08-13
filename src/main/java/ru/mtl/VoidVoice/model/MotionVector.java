package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.mtl.VoidVoice.comparator.Comparable;
import ru.mtl.VoidVoice.utils.MotionVectorTouchesConverter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class MotionVector implements PersistentEntity<Long>, Comparable<MotionVector> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Hand rightHand;

    @OneToOne(cascade = CascadeType.ALL)
    private Hand leftHand;

    @Convert(converter = MotionVectorTouchesConverter.class)
    private List<List<Integer>> touchList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "integer")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Finger> leftFingersList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "integer")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Finger> rightFingersList;

    @OneToOne(cascade = CascadeType.ALL)
    private Motion leftHandMotion;

    @OneToOne(cascade = CascadeType.ALL)
    private Motion rightHandMotion;

    public Long getPrimaryKey(){
        return this.id;
    }

    public MotionVector() {

    }

    public Hand getRightHand() {
        return rightHand;
    }

    public void setRightHand(Hand rightHand) {
        this.rightHand = rightHand;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Hand leftHand) {
        this.leftHand = leftHand;
    }

    public List<List<Integer>> getTouchList() {
        return touchList;
    }

    public void setTouchList(List<List<Integer>> touchList) {
        this.touchList = touchList;
    }

    public Motion getLeftHandMotion() {
        return leftHandMotion;
    }

    public void setLeftHandMotion(Motion leftHandMotion) {
        this.leftHandMotion = leftHandMotion;
    }

    public Motion getRightHandMotion() {
        return rightHandMotion;
    }

    public void setRightHandMotion(Motion rightHandMotion) {
        this.rightHandMotion = rightHandMotion;
    }

    public List<Finger> getLeftFingersList() {
        return leftFingersList;
    }

    public void setLeftFingersList(List<Finger> leftFingersList) {
        this.leftFingersList = leftFingersList;
    }

    public List<Finger> getRightFingersList() {
        return rightFingersList;
    }

    public void setRightFingersList(List<Finger> rightFingersList) {
        this.rightFingersList = rightFingersList;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double compareTo(MotionVector motionVector) {
        double rightHandSimilarity = rightHand.compareTo(motionVector.rightHand);
        double leftHandSimilarity = leftHand.compareTo(motionVector.leftHand);
        List<Double> rightFingersSimilarity = getFingersSimilarity(rightFingersList, motionVector.rightFingersList);
        List<Double> leftFingersSimilarity = getFingersSimilarity(leftFingersList, motionVector.leftFingersList);
        double rightMotionSimilarity = rightHandMotion.compareTo(rightHandMotion);
        double leftMotionSimilarity = leftHandMotion.compareTo(leftHandMotion);

        return isSimilar(rightHandSimilarity, leftHandSimilarity, rightFingersSimilarity, leftFingersSimilarity,
                rightMotionSimilarity, leftMotionSimilarity, touchList);
    }

    // method returns the end double value from 0. to 1. the measure of similarity
    // of the two MotionVectors
    private double isSimilar(double rightHandSimilarity, double leftHandSimilarity, List<Double> rightFingersSimilarity, List<Double> leftFingersSimilarity, double rightMotionSimilarity, double leftMotionSimilarity, List<List<Integer>> touchList) {
        return rightHandSimilarity * leftHandSimilarity;
    }

    private List<Double> getFingersSimilarity(List<Finger> fingers1, List<Finger> fingers2) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < fingers1.size(); i++) {
            result.add(fingers1.get(i).compareTo(fingers2.get(i)));
        }

        return result;
    }
}
