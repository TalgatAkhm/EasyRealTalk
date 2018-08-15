package ru.mtl.voidvoice.motion_treker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class MotionVector {
    @JsonIgnore
    private long id;

    private Hand rightHand;

    private Hand leftHand;

    private List<List<Integer>> touchList;

    private List<Finger> leftFingersList;

    private List<Finger> rightFingersList;

    private Motion leftHandMotion;

    private Motion rightHandMotion;

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

}
