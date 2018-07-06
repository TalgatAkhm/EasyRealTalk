package ru.mtl.VoidVoice.Model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

@Entity
public class Vector3d implements PersistentEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double x;
    @Column
    private double y;
    @Column
    private double z;

    public Vector3d() {

    }


    public Long getPrimaryKey(){
        return this.id;
    }

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setCoordinate(double x, double y, double z) {
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



}
