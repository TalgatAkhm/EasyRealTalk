package ru.mtl.VoidVoice.Model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

@Entity
public class Motion implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
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

}
