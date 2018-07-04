package ru.mtl.VoidVoice.Model;


@Entity
@Table(name="HIBERNATE_MOTION")
public class Motion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOTION_ID")
    private int id;

    @Column(name="MOTION_TYPE")
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
}
