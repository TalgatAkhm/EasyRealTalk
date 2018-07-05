package ru.mtl.VoidVoice.model;

import javax.persistence.*;

//import com.sun.javafx.geom.Vec3d;
@Entity
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Vector3d palmNormalVector;
    @Column
    private Vector3d palmDirectionVector;

    public Hand() {

    }

    public Hand(Vector3d palmNormalVector, Vector3d palmDirectionVector) {
        this.palmNormalVector = palmNormalVector;
        this.palmDirectionVector = palmDirectionVector;
    }

    public Vector3d getPalmNormalVector() {
        return palmNormalVector;
    }

    public void setPalmNormalVector(Vector3d palmNormalVector) {
        this.palmNormalVector = palmNormalVector;
    }

    public Vector3d getPalmDirectionVector() {
        return palmDirectionVector;
    }

    public void setPalmDirectionVector(Vector3d palmDirectionVector) {
        this.palmDirectionVector = palmDirectionVector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
