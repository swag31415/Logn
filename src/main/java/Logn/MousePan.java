package Logn;

import Logn.Keyer.Press;
import javafx.scene.PerspectiveCamera;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Window;

public class MousePan implements Runnable {

    Robot actor;
    Keyer keyer;
    Window window;
    PerspectiveCamera camera;

    private Rotate rX, rY;
    private Translate move;

    public MousePan(Keyer keyer, Window window, PerspectiveCamera camera) {
        actor = new Robot();
        this.keyer = keyer;
        this.window = window;
        this.camera = camera;

        rX = new Rotate(0, Rotate.X_AXIS);
        rY = new Rotate(0, Rotate.Y_AXIS);
        move = new Translate();

        camera.getTransforms().addAll(rX, rY, move);
    }

    @Override
    public void run() {
        double xShift = window.getX() + (window.getWidth() / 2);
        double yShift = window.getY() + (window.getHeight() / 2);

        double yAngle = rY.getAngle();
        double xAngle = rX.getAngle();

        double mouseY = actor.getMouseY();
        double mouseX = actor.getMouseX();

        double rotationSpeed = 1.0 / 10.0;

        panCamera(xShift, yShift, yAngle, xAngle, mouseY, mouseX, rotationSpeed);

        // - - - - - - - - - - //

        double rho = (rY.getAngle() * Math.PI) / 180;
        double theta = (rX.getAngle() * Math.PI) / 180;
        
        if (keyer.getKeyStates().get(Press.up)) {
            moveCamera(rho, theta, 2); 
        } else if (keyer.getKeyStates().get(Press.down)) {
            moveCamera(rho, theta, -2);
        }
    }

    private void panCamera(double xShift, double yShift, double yAngle, double xAngle, double mouseY, double mouseX,
            double rotationSpeed) {
        double xRotation = xAngle + ((-1) * (mouseY - yShift) * rotationSpeed);
        rX.setAngle((xRotation <= 90) ? ((xRotation >= -90) ? xRotation : -90) : 90);
        rY.setAngle(yAngle + ((mouseX - xShift - 0.5) * rotationSpeed));
        actor.mouseMove(xShift, yShift);
    }

    private void moveCamera(double rho, double theta, double dist) {
        double sinRho = Math.sin(rho);
        move.setX(move.getX() + sinRho * Math.cos(theta) * dist);
        move.setY(move.getY() + sinRho * Math.sin(theta) * dist);
        move.setZ(move.getZ() + Math.cos(rho) * dist);
    }

}