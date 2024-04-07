package FurniturePackage;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Cupboard extends Application {

    private static final float WIDTH = 1000;
    private static final float HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        prepareBackground(root);

        SmartGroup cupboardGroup = new SmartGroup();
        prepareCupboard(cupboardGroup);

        root.getChildren().add(cupboardGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        cupboardGroup.translateXProperty().set(WIDTH / 2);
        cupboardGroup.translateYProperty().set(HEIGHT / 2);
        cupboardGroup.translateZProperty().set(-900);

        initMouseControl(cupboardGroup, scene, primaryStage);

        primaryStage.setTitle("3D Cupboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg5.jpg")));

        Box background = new Box(WIDTH * 2, HEIGHT * 2, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50);

        root.getChildren().add(background);
    }

    private void prepareCupboard(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg1.jpg"))); // Replace with a modern texture

        // Main body of the cupboard
        Box body = new Box(200, 300, 50);
        body.setMaterial(material);

        // Shelves
        Box shelf1 = new Box(190, 5, 40);
        shelf1.setMaterial(material);
        shelf1.setTranslateY(-75);

        Box shelf2 = new Box(190, 5, 40);
        shelf2.setMaterial(material);
        shelf2.setTranslateY(0);

        Box shelf3 = new Box(190, 5, 40);
        shelf3.setMaterial(material);
        shelf3.setTranslateY(75);

        // Adding doors
        Box door1 = new Box(95, 290, 2);
        door1.setMaterial(material);
        door1.setTranslateX(-52.5);
        door1.setTranslateZ(-26);

        Box door2 = new Box(95, 290, 2);
        door2.setMaterial(material);
        door2.setTranslateX(52.5);
        door2.setTranslateZ(-26);

        group.getChildren().addAll(body, shelf1, shelf2, shelf3, door1, door2);
    }

    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group {
        SmartGroup() {
            Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
            Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
            xRotate.angleProperty().bind(angleX);
            yRotate.angleProperty().bind(angleY);
            this.getTransforms().addAll(xRotate, yRotate);
        }
    }
}
