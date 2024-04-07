package FurniturePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Rack2D extends Application {

    private static final int WIDTH = 1550;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        Group root = createRackView();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Rack View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Group createRackView() {
        Image woodImage = new Image(Rack2D.class.getResourceAsStream("/resources/gold.jpg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Define the rack components as rectangles
        Rectangle post1 = new Rectangle(10, 300);
        post1.setFill(woodPattern);
        post1.setX(WIDTH / 2.0 - 60); // Center - Offset
        post1.setY((HEIGHT - post1.getHeight()) / 2.0);

        Rectangle post2 = new Rectangle(10, 300);
        post2.setFill(woodPattern);
        post2.setX(WIDTH / 2.0 + 50); // Center + Offset
        post2.setY((HEIGHT - post2.getHeight()) / 2.0);

        // Create horizontal shelves
        Rectangle shelf1 = new Rectangle(100, 10);
        shelf1.setFill(woodPattern);
        shelf1.setX(WIDTH / 2.0 - shelf1.getWidth() / 2.0);
        shelf1.setY((HEIGHT - 300) / 2.0 + 75);

        Rectangle shelf2 = new Rectangle(100, 10);
        shelf2.setFill(woodPattern);
        shelf2.setX(WIDTH / 2.0 - shelf2.getWidth() / 2.0);
        shelf2.setY((HEIGHT - 300) / 2.0 + 150);

        Rectangle shelf3 = new Rectangle(100, 10);
        shelf3.setFill(woodPattern);
        shelf3.setX(WIDTH / 2.0 - shelf3.getWidth() / 2.0);
        shelf3.setY((HEIGHT - 300) / 2.0 + 225);

        Rectangle shelf4 = new Rectangle(100, 10);
        shelf4.setFill(woodPattern);
        shelf4.setX(WIDTH / 2.0 - shelf4.getWidth() / 2.0);
        shelf4.setY((HEIGHT - 300) / 2.0 + 300);

        root.getChildren().addAll(post1, post2, shelf1, shelf2, shelf3, shelf4);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
