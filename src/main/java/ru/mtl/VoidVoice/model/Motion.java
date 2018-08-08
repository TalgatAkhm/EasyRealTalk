package ru.mtl.VoidVoice.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

@Entity
public class Motion implements PersistentEntity<Long>, ValuableObject {
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
    public boolean isMotion() {
        return true;
    }

    @Override
    public double compareWith(ValuableObject valuableObject) {
        // If two motions have the same motion types, then return 1, else return 1
        return this.motionType == ((Motion) valuableObject).getMotionType() ? 1.0 : 0.0;
    }
}
