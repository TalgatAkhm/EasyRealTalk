package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.*;
import ru.mtl.VoidVoice.comparator.Comparable;

@Entity
public class KeyPoint implements PersistentEntity<Long>, Comparable<KeyPoint> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private MotionVector baseVector;
    @OneToOne
    private Motion baseMotion;

    public KeyPoint() {

    }

    public MotionVector getBaseVector() {
        return baseVector;
    }

    public void setBaseVector(MotionVector baseVector) {
        this.baseVector = baseVector;
    }

    public Motion getBaseMotion() {
        return baseMotion;
    }

    public void setBaseMotion(Motion baseMotion) {
        this.baseMotion = baseMotion;
    }

    @Override
    public Long getPrimaryKey() {
        return this.id;
    }

    @Override
    public double compareTo(KeyPoint object) {
        return 0;
    }
}
