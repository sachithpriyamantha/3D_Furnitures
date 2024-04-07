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
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;

public class Bookshelf extends Application {

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

        SmartGroup bookshelfGroup = new SmartGroup();
        prepareBookshelf(bookshelfGroup);

        root.getChildren().add(bookshelfGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        bookshelfGroup.translateXProperty().set(WIDTH / 2);
        bookshelfGroup.translateYProperty().set(HEIGHT / 2);
        bookshelfGroup.translateZProperty().set(-800);

        initMouseControl(bookshelfGroup, scene, primaryStage);

        primaryStage.setTitle("3D Bookshelf");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg5.jpg")));

        Box background = new Box(WIDTH * 2, HEIGHT * 2, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50); // Adjusted for visualization

        root.getChildren().add(background);
    }

    private void prepareBookshelf(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg4.jpeg")));

        // Main body of the bookshelf
        Box body = new Box(150, 200, 50);
        body.setMaterial(material);

        // Shelves
        Box shelf1 = new Box(140, 5, 40);
        shelf1.setMaterial(material);
        shelf1.setTranslateY(-80);

        Box shelf2 = new Box(140, 5, 40);
        shelf2.setMaterial(material);
        shelf2.setTranslateY(-25);

        Box shelf3 = new Box(140, 5, 40);
        shelf3.setMaterial(material);
        shelf3.setTranslateY(30);

        // Adding vertical separators between shelves
        Box separator1 = new Box(2, 170, 2);
        separator1.setMaterial(material);
        separator1.setTranslateX(-70);

        Box separator2 = new Box(2, 170, 2);
        separator2.setMaterial(material);
        separator2.setTranslateX(70);

        group.getChildren().addAll(body, shelf1, shelf2, shelf3, separator1, separator2);
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
