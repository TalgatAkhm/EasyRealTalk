package ru.mtl.VoidVoice.model;

import com.leapmotion.leap.Vector;
import net.sf.autodao.PersistentEntity;
import org.jetbrains.annotations.NotNull;
import ru.mtl.VoidVoice.comparator.Comparable;

import javax.persistence.*;

@Entity
public class Vector3d implements PersistentEntity<Long>, Comparable<Vector3d> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float x;
    private float y;
    private float z;

    public Vector3d() {}

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

    public static double module(Vector3d v){
        return Math.sqrt(scalarProduct(v, v));
    }

    public float module(){
        return (float) module(this);
    }

    @Override
    public double compareTo(@NotNull Vector3d object) {
        double angleXSimilarity = 1 - Math.abs(getAngleOX(this) - getAngleOX(object)) / Math.PI;
        double angleYSimilarity = 1 - Math.abs(getAngleOY(this) - getAngleOY(object)) / Math.PI;
        double angleZSimilarity = 1 - Math.abs(getAngleOZ(this) - getAngleOZ(object)) / Math.PI;

        return angleXSimilarity * angleYSimilarity * angleZSimilarity;
    }

    private double getAngleOX(Vector3d vector3d) {
        Vector3d normalX = new Vector3d(1, 0, 0);
        double cos = scalarProduct(vector3d, normalX) / (module(vector3d) * module(normalX));
        return Math.acos(cos);
    }

    private double getAngleOY(Vector3d vector3d) {
        Vector3d normalY = new Vector3d(0, 1, 0);
        double cos = scalarProduct(vector3d, normalY) / (module(vector3d) * module(normalY));
        return Math.acos(cos);
    }

    private double getAngleOZ(Vector3d vector3d) {
        Vector3d normalZ = new Vector3d(0, 0, 1);
        double cos = scalarProduct(vector3d, normalZ) / (module(vector3d) * module(normalZ));
        return Math.acos(cos);
    }
}
