package ru.mtl.VoidVoice.Model;

//import com.sun.javafx.geom.Vec3d;
@Entity
@Table(name="HIBERNATE_HAND")
public class Hand {
    @Id
    @GeneratedValue (stategy=GenerationType.AUTO)
    @Column(name="HAND_ID")
    private int id;

    @Column(name = "PALM_NORMAL_VECTOR")
    private Vector3d palmNormalVector;
    @Column(name = "PALM_DIRECTION_VECTOR")
    private Vector3d palmDirectionVector;

    public Hand(){

    }

    public Hand(Vector3d palmNormalVector, Vector3d palmDirectionVector){
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
}
