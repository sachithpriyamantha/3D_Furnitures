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

public class Chair extends Application {

    private static final float WIDTH = 1000;
    private static final float HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) {
        // Root group
        Group root = new Group();

        // Background
        prepareBackground(root);

        // Chair
        SmartGroup chairGroup = new SmartGroup();
        prepareChair(chairGroup);

        // Add chair group to the root group
        root.getChildren().add(chairGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        chairGroup.translateXProperty().set(WIDTH / 2);
        chairGroup.translateYProperty().set(HEIGHT / 2);
        chairGroup.translateZProperty().set(-800);

        initMouseControl(chairGroup, scene, primaryStage);

        primaryStage.setTitle("3D Chair");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg1.jpg")));

        Box background = new Box(WIDTH * 2, HEIGHT * 2, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50);

        root.getChildren().add(background);
    }

    private void prepareChair(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/wood.jpg")));

        // Components of the chair
        Box seat = new Box(100, 20, 50);
        seat.setMaterial(material);
        seat.setTranslateY(-10);

        Box backrest = new Box(100, 100, 5);
        backrest.setMaterial(material);
        backrest.setTranslateY(-60);
        backrest.setTranslateZ(-25);

        Box leg1 = new Box(5, 50, 5);
        leg1.setMaterial(material);
        leg1.setTranslateY(25);
        leg1.setTranslateX(-45);
        leg1.setTranslateZ(20);

        Box leg2 = new Box(5, 50, 5);
        leg2.setMaterial(material);
        leg2.setTranslateY(25);
        leg2.setTranslateX(45);
        leg2.setTranslateZ(20);

        Box leg3 = new Box(5, 50, 5);
        leg3.setMaterial(material);
        leg3.setTranslateY(25);
        leg3.setTranslateX(-45);
        leg3.setTranslateZ(-20);

        Box leg4 = new Box(5, 50, 5);
        leg4.setMaterial(material);
        leg4.setTranslateY(25);
        leg4.setTranslateX(45);
        leg4.setTranslateZ(-20);

        group.getChildren().addAll(seat, backrest, leg1, leg2, leg3, leg4);
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
