package FurniturePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Cupboard2D extends Application {

    // Window dimensions for centering
    private static final int WINDOW_WIDTH = 1550;
    private static final int WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = createCupboardView();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.LIGHTGRAY);

        primaryStage.setTitle("2D Cupboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Group createCupboardView() {
        Image woodImage = new Image(Cupboard2D.class.getResourceAsStream("/resources/bg6.jpeg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Create the body of the cupboard
        Rectangle body = new Rectangle(200, 300);
        body.setFill(woodPattern);

        // Calculate center position for the cupboard
        double centerX = (WINDOW_WIDTH - body.getWidth()) / 2;
        double centerY = (WINDOW_HEIGHT - body.getHeight()) / 2;

        // Set the position of the cupboard body to the center
        body.setX(centerX);
        body.setY(centerY);

        // Doors of the cupboard, positioned to appear open
        Rectangle doorLeft = new Rectangle(50, 290);
        doorLeft.setFill(woodPattern);
        doorLeft.setX(body.getX() - doorLeft.getWidth());
        doorLeft.setY(body.getY() + 5);

        Rectangle doorRight = new Rectangle(50, 290);
        doorRight.setFill(woodPattern);
        doorRight.setX(body.getX() + body.getWidth());
        doorRight.setY(body.getY() + 5);

        // Shelves inside the cupboard
        Rectangle shelf1 = new Rectangle(190, 10);
        shelf1.setFill(woodPattern);
        shelf1.setX(body.getX() + 5);
        shelf1.setY(body.getY() + 50);

        Rectangle shelf2 = new Rectangle(190, 10);
        shelf2.setFill(woodPattern);
        shelf2.setX(body.getX() + 5);
        shelf2.setY(body.getY() + 150);

        // Add all components to the root group
        root.getChildren().addAll(body, doorLeft, doorRight, shelf1, shelf2);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
