package ru.mtl.voidvoice.motion_treker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Motion {
    @JsonIgnore
    private long id;

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
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
