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

public class Table extends Application {

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

        SmartGroup tableGroup = new SmartGroup();
        prepareTable(tableGroup);
        root.getChildren().add(tableGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        tableGroup.translateXProperty().set(WIDTH / 2);
        tableGroup.translateYProperty().set(HEIGHT / 2);
        tableGroup.translateZProperty().set(-900);

        initMouseControl(tableGroup, scene, primaryStage);

        primaryStage.setTitle("3D Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareBackground(Group root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg6.jpeg")));

        Box background = new Box(WIDTH * 3, HEIGHT * 3, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(-50);

        root.getChildren().add(background);
    }

    private void prepareTable(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/green.jpg")));

        // Tabletop
        Box tabletop = new Box(200, 10, 100);
        tabletop.setMaterial(material);
        tabletop.setTranslateY(-15);

        // Legs
        Box leg1 = createTableLeg(material);
        leg1.setTranslateX(-90);
        leg1.setTranslateZ(40);

        Box leg2 = createTableLeg(material);
        leg2.setTranslateX(90);
        leg2.setTranslateZ(40);

        Box leg3 = createTableLeg(material);
        leg3.setTranslateX(-90);
        leg3.setTranslateZ(-40);

        Box leg4 = createTableLeg(material);
        leg4.setTranslateX(90);
        leg4.setTranslateZ(-40);

        group.getChildren().addAll(tabletop, leg1, leg2, leg3, leg4);
    }

    private Box createTableLeg(PhongMaterial material) {
        Box leg = new Box(10, 100, 10);
        leg.setMaterial(material);
        leg.setTranslateY(40);
        return leg;
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
