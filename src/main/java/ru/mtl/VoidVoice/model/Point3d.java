package ru.mtl.VoidVoice.model;

import com.leapmotion.leap.Vector;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
public class Point3d implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float x;
    private float y;
    private float z;

    public Point3d(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d(Vector v){
        x = v.getX();
        y = v.getY();
        z = v.getZ();
    }


    public Long getPrimaryKey(){
        return this.id;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
