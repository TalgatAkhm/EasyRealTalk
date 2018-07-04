package ru.mtl.VoidVoice.Model;

import java.util.List;

public class Gesture {
    private List<KeyPoint> keyPointList;
    private long id;

    public List<KeyPoint> getKeyPointList() {
        return keyPointList;
    }

    public void setKeyPointList(List<KeyPoint> keyPointList) {
        this.keyPointList = keyPointList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
