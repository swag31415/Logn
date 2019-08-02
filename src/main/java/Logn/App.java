package Logn;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
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
    
    private Camera camera;
    private FXMLLoader loader;
    private Scene scene;

    boolean ballClicked;

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
        ObjModelImporter meshImporter = new ObjModelImporter();
        
        meshImporter.read(getClass().getClassLoader().getResource("Bawl2.obj"));
        MeshView imp = meshImporter.getImport()[0];
        
        mesh.setMesh(imp.getMesh());
        mesh.setMaterial(imp.getMaterial());
        mesh.requestFocus();
    }

    @FXML private void keyPressed(KeyEvent event) {
        Rotate rX = new Rotate(1, Rotate.X_AXIS);
        Rotate rY = new Rotate(1, Rotate.Y_AXIS);
        switch (event.getCharacter()) {
            case "w":
            rY.setAngle(2);
            mesh.getTransforms().add(rY);
                break;
            case "s":
            rY.setAngle(-2);
            mesh.getTransforms().add(rY);
                break;
            case "a":
            rX.setAngle(-2);
            mesh.getTransforms().add(rX);
                break;
            case "d":
            rX.setAngle(2);
            mesh.getTransforms().add(rX);
                break;
            default:
            System.out.println(event.getCharacter());
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}