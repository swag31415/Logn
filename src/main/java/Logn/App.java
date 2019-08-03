package Logn;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.stage.Stage;

public class App extends Application {

    @FXML
    private MeshView mesh;

    @FXML
    private VBox vbox;

    @FXML
    private AmbientLight light;
    
    private Camera camera;
    private FXMLLoader loader;
    private Scene scene;

    Keyer keyer;
    Processor processor;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI.fxml"));
        vbox = loader.<VBox>load();

        camera = new PerspectiveCamera(true);
        scene = new Scene(vbox);
        scene.setFill(Color.SILVER);
        //Attach to scene
        scene.setCamera(camera);
 
        camera.translateZProperty().set(-2000);
        camera.translateXProperty().set(960);
        camera.translateYProperty().set(540);
 
        //Set the clipping planes
        camera.setNearClip(1);
        camera.setFarClip(30000);
 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private void showModel(ActionEvent event) {
        light.colorProperty().set(Color.WHITE);

        MeshLoader.loadMesh("TheBawl/Bawl3.obj", mesh);
        mesh.requestFocus();

        keyer = new Keyer();
        processor = new Processor(40);

        processor.addRunnable(new RotateMesh(mesh, keyer));

        processor.start();
    }

    @FXML private void keyPressed(KeyEvent event) {
        keyer.reportKeyPressed(event.getCode());
    }

    @FXML private void keyReleased(KeyEvent event) {
        keyer.reportKeyReleased(event.getCode());
    }

    public static void main(String[] args) {
        launch(args);
    }
}