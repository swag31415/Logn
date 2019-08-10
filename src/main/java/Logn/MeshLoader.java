package Logn;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;

import javafx.scene.shape.MeshView;

public class MeshLoader {

    public static MeshView loadMesh(String fileString) {

        String extension = fileString.substring(fileString.lastIndexOf("."));

        switch (extension) {

        case ".obj":
            ObjModelImporter meshImporter = new ObjModelImporter();
            meshImporter.read(meshImporter.getClass().getClassLoader().getResource(fileString));
            return meshImporter.getImport()[0];

        case ".stl": // TODO test this
            StlMeshImporter meshImporter2 = new StlMeshImporter();
            meshImporter2.read(meshImporter2.getClass().getClassLoader().getResource(fileString));
            return new MeshView(meshImporter2.getImport());

        default:
            System.err.println("Unknown mesh file format");
            return null;
        }
    }

    public static void loadMesh(String fileString, MeshView targetMeshView) {
        MeshView imp = MeshLoader.loadMesh(fileString);

        targetMeshView.setMesh(imp.getMesh());
        targetMeshView.setMaterial(imp.getMaterial());
    }
}