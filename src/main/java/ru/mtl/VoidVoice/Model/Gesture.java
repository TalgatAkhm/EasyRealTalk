package ru.mtl.VoidVoice.Model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Gesture {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    private List<MotionVector> keyPointList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MotionVector> getKeyPointList() {
        return keyPointList;
    }

    public void setKeyPointList(List<MotionVector> keyPointList) {
        this.keyPointList = keyPointList;
    }
}
