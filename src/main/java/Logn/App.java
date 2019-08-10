package Logn;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class App extends SimpleApplication {

    public static void main(String[] args) {
        new App().start();
    }

    @Override
    public void simpleInitApp() {
        Spatial ball = assetManager.loadModel("TheBawl/Bawl3.obj");
        Material testMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture ballTexture = assetManager.loadTexture("TheBawl/UV.png");
        testMat.setTexture("ColorMap", ballTexture);
        ball.setMaterial(testMat);
        rootNode.attachChild(ball);
    }
}