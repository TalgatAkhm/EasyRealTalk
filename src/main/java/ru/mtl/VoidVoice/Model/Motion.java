package ru.mtl.VoidVoice.Model;

public class Motion {
    private MotionType motionType;

    public Motion() {
    }

    public void setMotionType(MotionType extMotionType) {
        motionType = extMotionType;
    }

    public MotionType getMotionType() {
        return motionType;
    }
}
