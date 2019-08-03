package Logn;

import Logn.Keyer.Press;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public class RotateMesh implements Runnable {

    private MeshView mesh;
    private Keyer keyer;

    public RotateMesh(MeshView mesh, Keyer keyer) {
        this.mesh = mesh;
        this.keyer = keyer;
    }

    @Override
    public void run() {
        Rotate rY = new Rotate(1, Rotate.Y_AXIS);
        Rotate rX = new Rotate(1, Rotate.X_AXIS);

        if (keyer.getKeyStates().get(Press.up)) {
            rX.setAngle(2);
            mesh.getTransforms().add(rX);
        }

        if (keyer.getKeyStates().get(Press.down)) {
            rX.setAngle(-2);
            mesh.getTransforms().add(rX);
        }

        if (keyer.getKeyStates().get(Press.left)) {
            rY.setAngle(-2);
            mesh.getTransforms().add(rY);
        }

        if (keyer.getKeyStates().get(Press.right)) {
            rY.setAngle(2);
            mesh.getTransforms().add(rY);
        }
    }

}