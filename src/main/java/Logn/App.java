package Logn;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AmbientLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.stage.Stage;

public class App extends Application {

    @FXML
    private VBox vbox;

    @FXML
    private AmbientLight light;

    private FXMLLoader loader;
    private Scene scene;

    Keyer keyer;
    Processor processor;

    boolean initalized;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI.fxml"));
        vbox = loader.<VBox>load();

        scene = new Scene(vbox);

        vbox.requestFocus();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void play() {
        light.colorProperty().set(Color.WHITE);

        MeshView sphere = MeshLoader.loadMesh("TheBawl/Bawl3.obj");
        // MeshLoader.loadMesh("TheTeapot/Teapot.obj", mesh);
        vbox.getChildren().add(sphere);

        sphere.translateXProperty().set(vbox.getWidth() / 2);
        sphere.translateYProperty().set(vbox.getHeight() / 2);
        sphere.setScaleX(100);
        sphere.setScaleY(100);
        sphere.setScaleZ(100);

        keyer = new Keyer();
        processor = new Processor(40);

        processor.addRunnable(new RotateMesh(sphere, keyer));

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