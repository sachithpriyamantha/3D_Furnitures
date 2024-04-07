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

public class Bed extends Application {

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

        SmartGroup bedGroup = new SmartGroup();
        prepareBed(bedGroup);

        root.getChildren().add(bedGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        bedGroup.translateXProperty().set(WIDTH / 2);
        bedGroup.translateYProperty().set(HEIGHT / 2);
        bedGroup.translateZProperty().set(-900);

        initMouseControl(bedGroup, scene, primaryStage);

        primaryStage.setTitle("3D Bed");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg5.jpg")));

        Box background = new Box(WIDTH * 2, HEIGHT * 2, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50); // Adjusted for bed visualization

        root.getChildren().add(background);
    }

    private void prepareBed(SmartGroup group) {
        PhongMaterial mattressMaterial = new PhongMaterial();
        mattressMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpg"))); // Consider using a fabric texture for a more realistic mattress

        // Mattress
        Box mattress = new Box(200, 30, 150);
        mattress.setMaterial(mattressMaterial);
        mattress.setTranslateY(-15);

        // Headboard
        Box headboard = new Box(210, 50, 10);
        headboard.setMaterial(mattressMaterial); // Optionally, use a distinct material for contrast
        headboard.setTranslateY(-35);
        headboard.setTranslateZ(-80);

        // Legs
        Box leg1 = new Box(10, 20, 10);
        leg1.setMaterial(mattressMaterial);
        leg1.setTranslateY(10);
        leg1.setTranslateX(-95);
        leg1.setTranslateZ(70);

        Box leg2 = new Box(10, 20, 10);
        leg2.setMaterial(mattressMaterial);
        leg2.setTranslateY(10);
        leg2.setTranslateX(95);
        leg2.setTranslateZ(70);

        // Adding two more legs at the opposite side
        Box leg3 = new Box(10, 20, 10);
        leg3.setMaterial(mattressMaterial);
        leg3.setTranslateY(10);
        leg3.setTranslateX(-95);
        leg3.setTranslateZ(-70); // Position adjusted to be at the other end of the bed

        Box leg4 = new Box(10, 20, 10);
        leg4.setMaterial(mattressMaterial);
        leg4.setTranslateY(10);
        leg4.setTranslateX(95);
        leg4.setTranslateZ(-70); // Position adjusted to be at the other end of the bed

        group.getChildren().addAll(mattress, headboard, leg1, leg2, leg3, leg4);
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
