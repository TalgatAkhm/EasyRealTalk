package ru.mtl.VoidVoice.Model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.*;
import java.util.List;
@Entity
public class Gesture implements PersistentEntity<Long> {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<MotionVector> keyPointList;

    @Column
    private String meaning;

    public Gesture(){

    }

    public Long getPrimaryKey(){
        return this.id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MotionVector> getKeyPointList() {
        return keyPointList;
    }

    public void setKeyPointList(List<MotionVector> keyPointList) {
        this.keyPointList = keyPointList;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
