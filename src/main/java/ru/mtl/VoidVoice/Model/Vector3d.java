package ru.mtl.VoidVoice.Model;


@Entity
@Table (name="HIBERNATE_VECTOR3D")
public class Vector3d {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "VECTOR_ID")
    private int id;

    @Column(name="X")
    private double x;
    @Column(name="Y")
    private double y;
    @Column(name="Z")
    private double z;

    public Vector3d(){

    }
    public Vector3d(double x, double y, double z){
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

    public void setCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d getVecotr3d(){
        return this;
    }
}
