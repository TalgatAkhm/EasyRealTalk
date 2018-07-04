package ru.mtl.VoidVoice.Model;

import javax.persistence.*;

@Entity
public class Motion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private MotionType motionType;

    public Motion() {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
