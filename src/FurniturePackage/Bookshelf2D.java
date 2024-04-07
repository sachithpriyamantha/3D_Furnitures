package FurniturePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Bookshelf2D extends Application {

    private static final int WIDTH = 1550;
    private static final int HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group bookshelfGroup = createBookshelfView();

        Scene scene = new Scene(bookshelfGroup, WIDTH, HEIGHT, Color.WHITE);
        primaryStage.setTitle("2D Bookshelf");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Group createBookshelfView() {
        Image woodImage = new Image(Bookshelf2D.class.getResourceAsStream("/resources/wood.jpg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Calculate the center position for the bookshelf
        double bookshelfWidth = 400; // Width of the shelves
        double postWidth = 10; // Width of the vertical posts
        double bookshelfCenterX = (WIDTH - bookshelfWidth) / 2;
        double post1X = bookshelfCenterX - postWidth / 2;
        double post2X = bookshelfCenterX + bookshelfWidth - postWidth / 2;

        // Create vertical posts
        Rectangle post1 = new Rectangle(post1X, HEIGHT / 2 - 150, 10, 300);
        post1.setFill(woodPattern);

        Rectangle post2 = new Rectangle(post2X, HEIGHT / 2 - 150, 10, 300);
        post2.setFill(woodPattern);

        // Adjust positions of shelves to center
        Rectangle shelf1 = new Rectangle(bookshelfCenterX, HEIGHT / 2 - 150, 400, 10);
        shelf1.setFill(woodPattern);

        Rectangle shelf2 = new Rectangle(bookshelfCenterX, HEIGHT / 2 - 50, 400, 10);
        shelf2.setFill(woodPattern);

        Rectangle shelf3 = new Rectangle(bookshelfCenterX, HEIGHT / 2 + 50, 400, 10);
        shelf3.setFill(woodPattern);

        Rectangle shelf4 = new Rectangle(bookshelfCenterX, HEIGHT / 2 + 150, 400, 10);
        shelf4.setFill(woodPattern);

        root.getChildren().addAll(post1, post2, shelf1, shelf2, shelf3, shelf4);

        return root;
    }
}
