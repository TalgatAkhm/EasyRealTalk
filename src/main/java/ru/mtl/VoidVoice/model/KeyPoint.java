package ru.mtl.VoidVoice.model;

import ru.mtl.VoidVoice.comparator.Comparable;

public class KeyPoint implements Comparable<KeyPoint> {

    private MotionVector baseVector;

    public KeyPoint(){

    }

    public MotionVector getBaseVector() {
        return baseVector;
    }

    public void setBaseVector(MotionVector baseVector) {
        this.baseVector = baseVector;
    }

    @Override
    public double compareTo(KeyPoint object) {
        return 0;
    }
}
