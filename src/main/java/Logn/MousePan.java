package Logn;

import javafx.scene.PerspectiveCamera;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Rotate;
import javafx.stage.Window;

public class MousePan implements Runnable {

    Robot actor;
    Keyer keyer;
    Window window;
    PerspectiveCamera camera;

    public MousePan(Keyer keyer, Window window, PerspectiveCamera camera) {
        actor = new Robot();
        this.keyer = keyer;
        this.window = window;
        this.camera = camera;
    }

    @Override
    public void run() {
        double xShift = window.getX() + (window.getWidth() / 2);
        double yShift = window.getY() + (window.getHeight() / 2);
        camera.getTransforms().addAll(
            new Rotate((actor.getMouseX() - xShift - 0.5) / 10, Rotate.Y_AXIS),
            new Rotate((actor.getMouseY() - yShift) / -10, Rotate.X_AXIS)
        );
        actor.mouseMove(xShift, yShift);
    }

}