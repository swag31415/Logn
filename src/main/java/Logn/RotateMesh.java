package Logn;

import Logn.Keyer.Press;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

public class RotateMesh implements Runnable {

    private Node node;
    private Keyer keyer;

    public RotateMesh(Node node, Keyer keyer) {
        this.node = node;
        this.keyer = keyer;
    }

    @Override
    public void run() {
        Rotate rY = new Rotate(1, Rotate.Y_AXIS);
        Rotate rX = new Rotate(1, Rotate.X_AXIS);

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