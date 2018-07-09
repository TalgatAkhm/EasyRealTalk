package ru.mtl.VoidVoice.model;

import com.leapmotion.leap.Vector;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

@Entity
public class Vector3d implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float x;

    private float y;

    private float z;

    public Vector3d() {

    }

    public Long getPrimaryKey(){
        return this.id;
    }

    public Vector3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d(Vector ext){
        x = ext.getX();
        y = ext.getY();
        z = ext.getZ();
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

    public void setCoordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float scalarProduct(Vector3d second){
        return scalarProduct(this, second);
    }

    public static float scalarProduct(Vector3d first, Vector3d second){
        return (first.x * second.x + first.y * second.y + first.z * second.z);
    }

    public static float module(Vector3d v){
        return scalarProduct(v, v);
    }

    public float module(){
        return module(this);
    }

}
