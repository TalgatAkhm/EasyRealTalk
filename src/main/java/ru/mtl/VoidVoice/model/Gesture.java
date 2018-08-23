package ru.mtl.VoidVoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gesture implements PersistentEntity<Long> {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn
    private List<KeyPoint> keyPointList;

    private String meaning;

    public Gesture() {

    }

    @JsonIgnore
    public Long getPrimaryKey(){
        return this.id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KeyPoint> getKeyPointList() {
        return keyPointList;
    }

    public void setKeyPointList(List<KeyPoint> keyPointList) {
        this.keyPointList = keyPointList;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
