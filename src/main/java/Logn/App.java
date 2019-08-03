package Logn;

import java.util.HashMap;
import java.util.Map;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.animation.AnimationTimer;
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
import javafx.scene.transform.Rotate;
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

    private Map<String, Boolean> keyStates;

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

        keyStates = new HashMap<String, Boolean>();
        keyStates.put("w", false);
        keyStates.put("s", false);
        keyStates.put("a", false);
        keyStates.put("d", false);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Rotate rY = new Rotate(1, Rotate.Y_AXIS);
                Rotate rX = new Rotate(1, Rotate.X_AXIS);
                
                if (keyStates.get("w")) {
                    rX.setAngle(2);
                    mesh.getTransforms().add(rX);
                }

                if (keyStates.get("s")) {
                    rX.setAngle(-2);
                    mesh.getTransforms().add(rX);
                }

                if (keyStates.get("a")) {
                    rY.setAngle(-2);
                    mesh.getTransforms().add(rY);
                }

                if (keyStates.get("d")) {
                    rY.setAngle(2);
                    mesh.getTransforms().add(rY);
                }
                
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @FXML private void keyPressed(KeyEvent event) {
        String text = event.getText();
        if (keyStates.containsKey(text)) { 
            keyStates.put(text, true);
        }
    }

    @FXML private void keyReleased(KeyEvent event) {
        String text = event.getText();
        if (keyStates.containsKey(text)) { 
            keyStates.put(text, false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}