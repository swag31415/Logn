package Logn;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.stage.Stage;

public class App extends Application {

    @FXML
    private VBox vbox;

    @FXML
    private StackPane stackPane;

    @FXML
    private AmbientLight light;

    private FXMLLoader loader;
    private Scene scene;
    private PerspectiveCamera camera;

    Keyer keyer;
    Processor processor;

    boolean initalized;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI.fxml"));
        vbox = loader.<VBox>load();

        scene = new Scene(vbox, vbox.getWidth(), vbox.getHeight(), true);
        camera = new PerspectiveCamera(true);
        scene.setCamera(camera);
        camera.translateXProperty().set(vbox.getPrefWidth() / 2);
        camera.translateYProperty().set(vbox.getPrefHeight() / 2);
        camera.setFarClip(30000);
        camera.translateZProperty().set(-2000);

        vbox.requestFocus();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void play() {
        light.colorProperty().set(Color.WHITE);

        MeshView sphere = MeshLoader.loadMesh("TheBawl/Bawl3.obj");
        MeshView teapot = MeshLoader.loadMesh("TheTeapot/Teapot.obj");

        stackPane.getChildren().addAll(sphere, teapot);

        // sphere.translateXProperty().set(vbox.getWidth() / 2);
        // sphere.translateYProperty().set(vbox.getHeight() / 2);
        sphere.setScaleX(300);
        sphere.setScaleY(300);
        sphere.setScaleZ(300);

        // teapot.translateXProperty().set(sphere.getTranslateX());
        teapot.translateYProperty().set(/*sphere.getTranslateY()*/ -300);
        // teapot.translateZProperty().set(sphere.getTranslateZ() - 300);

        keyer = new Keyer();
        processor = new Processor(40);

        processor.addRunnable(new WASDRotateMesh(sphere, Point3D.ZERO, keyer));
        processor.addRunnable(new WASDRotateMesh(teapot, new Point3D(0, 300, 0), keyer));
        processor.addRunnable(new MousePan(keyer, vbox.getScene().getWindow(), (PerspectiveCamera) stackPane.getScene().getCamera()));

        processor.start();
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        if (!initalized) {
            play();
            initalized = true;
        }
        keyer.reportKeyPressed(event.getCode());
    }

    @FXML
    private void keyReleased(KeyEvent event) {
        keyer.reportKeyReleased(event.getCode());
    }

    public static void main(String[] args) {
        launch(args);
    }
}