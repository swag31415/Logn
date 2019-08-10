package Logn;

import Logn.Keyer.Press;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

public class WASDRotateMesh implements Runnable {

    private Node node;
    private Point3D center;
    private Keyer keyer;

    public WASDRotateMesh(Node node, Point3D center, Keyer keyer) {
        this.node = node;
        this.center = center;
        this.keyer = keyer;
    }

    @Override
    public void run() {
        Rotate rY = new Rotate(1, center.getX(), center.getY(), center.getZ(), Rotate.Y_AXIS);
        Rotate rX = new Rotate(1, center.getX(), center.getY(), center.getZ(), Rotate.X_AXIS);

        if (keyer.getKeyStates().get(Press.up)) {
            rX.setAngle(2);
            node.getTransforms().add(rX);
        }

        if (keyer.getKeyStates().get(Press.down)) {
            rX.setAngle(-2);
            node.getTransforms().add(rX);
        }

        if (keyer.getKeyStates().get(Press.left)) {
            rY.setAngle(-2);
            node.getTransforms().add(rY);
        }

        if (keyer.getKeyStates().get(Press.right)) {
            rY.setAngle(2);
            node.getTransforms().add(rY);
        }
    }

}