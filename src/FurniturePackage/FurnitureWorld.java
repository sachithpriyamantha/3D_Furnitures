package FurniturePackage;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class FurnitureWorld extends Application {

    private static final float WIDTH = 1550;
    private static final float HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private Group furnitureGroup = new Group();

    @Override
    public void start(Stage primaryStage) {
        launchFurnitureWorld(primaryStage);
    }

    public void launchFurnitureWorld(Stage primaryStage) {
        BorderPane root = new BorderPane();
        prepareBackground(root);

        HBox navigationPanel = createNavigationPanel();
        root.setTop(navigationPanel);

        furnitureGroup = new Group();
        root.setCenter(furnitureGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        initMouseControl(scene, primaryStage);

        primaryStage.setTitle("Furniture World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createNavigationPanel() {
        HBox navigationPanel = new HBox(10);
        navigationPanel.setStyle("-fx-background-color: transparent; -fx-padding: 10px;");

        Button chairButton = new Button("Chair");
        chairButton.setOnAction(event -> displayChair());
        styleButton(chairButton);

        Button tableButton = new Button("Table");
        tableButton.setOnAction(event -> displayTable());
        styleButton(tableButton);

        Button bedButton = new Button("Bed");
        bedButton.setOnAction(event -> displayBed());
        styleButton(bedButton);

        Button cupboardButton = new Button("Cupboard");
        cupboardButton.setOnAction(event -> displayCupboard());
        styleButton(cupboardButton);

        Button roundedTableButton = new Button("Rounded Table");
        roundedTableButton.setOnAction(event -> displayRoundedTable());
        styleButton(roundedTableButton);

        Button bookshelfButton = new Button("Bookshelf");
        bookshelfButton.setOnAction(event -> displayBookshelf());
        styleButton(bookshelfButton);

        Button deskButton = new Button("Desk");
        deskButton.setOnAction(event -> displayDesk());
        styleButton(deskButton);

        navigationPanel.getChildren().addAll(chairButton, tableButton, bedButton, cupboardButton, roundedTableButton, bookshelfButton, deskButton);

        return navigationPanel;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #0078D7; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px 10px;");
    }

    private void prepareBackground(BorderPane root) {
        PhongMaterial backgroundMaterial = new PhongMaterial();
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/4K.jpg")));

        Box background = new Box(WIDTH * 3, HEIGHT * 3, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(1500);

        root.getChildren().add(background);
    }

    private void displayChair() {
        furnitureGroup.getChildren().clear();
        SmartGroup chairGroup = new SmartGroup();
        prepareChair(chairGroup);
        furnitureGroup.getChildren().add(chairGroup);
        chairGroup.setTranslateX(WIDTH / 2);
        chairGroup.setTranslateY(HEIGHT / 2);
        chairGroup.setTranslateZ(-800);

    }
    private void displayTable() {
        furnitureGroup.getChildren().clear();
        SmartGroup tableGroup = new SmartGroup();
        prepareTable(tableGroup);
        furnitureGroup.getChildren().add(tableGroup);
        tableGroup.setTranslateX(WIDTH / 2);
        tableGroup.setTranslateY(HEIGHT / 2);
        tableGroup.setTranslateZ(-900);
    }

    private void displayBed() {
        furnitureGroup.getChildren().clear();
        SmartGroup bedGroup = new SmartGroup();
        prepareBed(bedGroup);
        furnitureGroup.getChildren().add(bedGroup);
        bedGroup.setTranslateX(WIDTH / 2);
        bedGroup.setTranslateY(HEIGHT / 2);
        bedGroup.setTranslateZ(-900);
    }

    private void displayCupboard() {
        furnitureGroup.getChildren().clear();
        SmartGroup cupboardGroup = new SmartGroup();
        prepareCupboard(cupboardGroup);
        furnitureGroup.getChildren().add(cupboardGroup);
        cupboardGroup.setTranslateX(WIDTH / 2);
        cupboardGroup.setTranslateY(HEIGHT / 2);
        cupboardGroup.setTranslateZ(-900);
    }



    private void displayRoundedTable() {
        furnitureGroup.getChildren().clear();
        SmartGroup roundedTableGroup = new SmartGroup();
        prepareRoundedTable(roundedTableGroup);
        furnitureGroup.getChildren().add(roundedTableGroup);
        roundedTableGroup.setTranslateX(WIDTH / 2);
        roundedTableGroup.setTranslateY(HEIGHT / 2);
        roundedTableGroup.setTranslateZ(-900);
    }

    private void displayBookshelf() {
        furnitureGroup.getChildren().clear();
        SmartGroup bookshelfGroup = new SmartGroup();
        prepareBookshelf(bookshelfGroup);
        furnitureGroup.getChildren().add(bookshelfGroup);
        bookshelfGroup.setTranslateX(WIDTH / 2);
        bookshelfGroup.setTranslateY(HEIGHT / 2);
        bookshelfGroup.setTranslateZ(-900); 
    }


    private void displayDesk() {
        furnitureGroup.getChildren().clear();
        SmartGroup deskGroup = new SmartGroup();
        prepareDesk(deskGroup);
        furnitureGroup.getChildren().add(deskGroup);
        deskGroup.setTranslateX(WIDTH / 2);
        deskGroup.setTranslateY(HEIGHT / 2);
        deskGroup.setTranslateZ(-900);
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

    private void prepareTable(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/spec.jpg")));

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

    private void prepareBed(SmartGroup group) {
        PhongMaterial mattressMaterial = new PhongMaterial();
        mattressMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpg")));
        // Mattress
        Box mattress = new Box(200, 30, 150);
        mattress.setMaterial(mattressMaterial);
        mattress.setTranslateY(-15);

        // Headboard
        Box headboard = new Box(210, 50, 10);
        headboard.setMaterial(mattressMaterial); 
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
        leg3.setTranslateZ(-70); 

        Box leg4 = new Box(10, 20, 10);
        leg4.setMaterial(mattressMaterial);
        leg4.setTranslateY(10);
        leg4.setTranslateX(95);
        leg4.setTranslateZ(-70); 

        group.getChildren().addAll(mattress, headboard, leg1, leg2, leg3, leg4);
    }

    private void prepareCupboard(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg1.jpg")));

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

    private void prepareRoundedTable(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/gold.jpg")));

        // Tabletop (rounded)
        Cylinder tabletop = new Cylinder(100, 10);
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




    private void initMouseControl(Scene scene, Stage stage) {
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
            furnitureGroup.translateZProperty().set(furnitureGroup.getTranslateZ() + delta);
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
