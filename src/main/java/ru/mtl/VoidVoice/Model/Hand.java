package ru.mtl.VoidVoice.Model;

import com.sun.javafx.geom.Vec3d;

public class Hand {
    private Vec3d palmNormalVector;
    private Vec3d palmDirectionVector;

    public Hand(){

    }

    public Vec3d getPalmNormalVector() {
        return palmNormalVector;
    }

    public void setPalmNormalVector(Vec3d palmNormalVector) {
        this.palmNormalVector = palmNormalVector;
    }

    public Vec3d getPalmDirectionVector() {
        return palmDirectionVector;
    }

    public void setPalmDirectionVector(Vec3d palmDirectionVector) {
        this.palmDirectionVector = palmDirectionVector;
    }
}
