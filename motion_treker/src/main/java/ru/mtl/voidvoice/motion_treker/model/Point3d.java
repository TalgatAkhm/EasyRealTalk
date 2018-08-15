package ru.mtl.voidvoice.motion_treker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Point3d {
    private final static double TOLERANCE_DISTANCE = 10.0;

    @JsonIgnore
    private long id;

    private float x;
    private float y;
    private float z;

    public Point3d(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d(Vector3d v){
        x = v.getX();
        y = v.getY();
        z = v.getZ();
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

    public static double distance(Point3d first, Point3d second) {
        return first.distance(second);
    }

    public double distance(Point3d point3d) {
        return Math.sqrt((x - point3d.x) * (x - point3d.x) + (y - point3d.y) * (y - point3d.y) + (z - point3d.z) * (z - point3d.z));
    }
}
