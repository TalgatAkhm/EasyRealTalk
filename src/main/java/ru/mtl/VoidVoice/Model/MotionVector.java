package ru.mtl.VoidVoice.Model;

import java.util.List;
import javax.persistence.*;

@Entity
public class MotionVector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Hand rightHand;
    @Column
    private Hand leftHand;

    //TODO:
    @Transient
    private List<List<FingerType>> touchList;
    @OneToMany
    private List<Finger> leftFingersList;
    @OneToMany
    private List<Finger> rightFingerList;
    @Column
    private Motion leftHandMotion;
    @Column
    private Motion rightHandMotion;

    public MotionVector(){

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

    public List<List<FingerType>> getTouchList() {
        return touchList;
    }

    public void setTouchList(List<List<FingerType>> touchList) {
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

    public List<Finger> getRightFingerList() {
        return rightFingerList;
    }

    public void setRightFingerList(List<Finger> rightFingerList) {
        this.rightFingerList = rightFingerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
