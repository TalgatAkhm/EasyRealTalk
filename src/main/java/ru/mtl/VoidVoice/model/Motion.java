package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;
import ru.mtl.VoidVoice.comparator.Comparable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Motion implements PersistentEntity<Long>, Comparable<Motion> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Enumerated(EnumType.STRING)
    private MotionType motionType;

    public Motion() {
    }

    public Long getPrimaryKey(){
        return this.id;
    }

    public Motion(MotionType motionType){
        this.motionType = motionType;
    }

    public void setMotionType(MotionType extMotionType) {
        motionType = extMotionType;
    }

    public MotionType getMotionType() {
        return motionType;
    }
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double compareTo(Motion motion) {
        return this.motionType == motion.getMotionType() ? 1.0 : 0.0;
    }
}
