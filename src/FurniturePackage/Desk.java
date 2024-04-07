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

public class Desk extends Application {

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

        SmartGroup deskGroup = new SmartGroup();
        prepareDesk(deskGroup);

        root.getChildren().add(deskGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        deskGroup.translateXProperty().set(WIDTH / 2);
        deskGroup.translateYProperty().set(HEIGHT / 2);
        deskGroup.translateZProperty().set(-800);

        initMouseControl(deskGroup, scene, primaryStage);

        primaryStage.setTitle("3D Desk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg6.jpeg")));

        Box background = new Box(WIDTH * 2, HEIGHT * 2, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50); // Adjusted for visualization

        root.getChildren().add(background);
    }

    private void prepareDesk(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpg")));

        // Desk surface
        Box surface = new Box(200, 5, 100);
        surface.setMaterial(material);
        surface.setTranslateY(-15);

        // Legs
        Box leg1 = new Box(10, 30, 10);
        leg1.setMaterial(material);
        leg1.setTranslateX(-90);
        leg1.setTranslateZ(40);

        Box leg2 = new Box(10, 30, 10);
        leg2.setMaterial(material);
        leg2.setTranslateX(90);
        leg2.setTranslateZ(40);

        Box leg3 = new Box(10, 30, 10);
        leg3.setMaterial(material);
        leg3.setTranslateX(-90);
        leg3.setTranslateZ(-40);

        Box leg4 = new Box(10, 30, 10);
        leg4.setMaterial(material);
        leg4.setTranslateX(90);
        leg4.setTranslateZ(-40);

        group.getChildren().addAll(surface, leg1, leg2, leg3, leg4);
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
