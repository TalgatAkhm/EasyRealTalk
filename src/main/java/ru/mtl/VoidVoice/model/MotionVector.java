package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;

import java.util.List;
import javax.persistence.*;

@Entity
public class MotionVector implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Hand rightHand;

    @OneToOne
    private Hand leftHand;

    //TODO:
    @Transient
    private List<List<Integer>> touchList;

    @OneToMany
    private List<Finger> leftFingersList;

    @OneToMany
    private List<Finger> rightFingerList;

    @OneToOne
    private Motion leftHandMotion;

    @OneToOne
    private Motion rightHandMotion;

    public Long getPrimaryKey(){
        return this.id;
    }

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

    public List<Finger> getRightFingerList() {
        return rightFingerList;
    }

    public void setRightFingerList(List<Finger> rightFingerList) {
        this.rightFingerList = rightFingerList;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
