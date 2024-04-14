package FurniturePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Bench2D extends Application {





    
    // Assume window size of 1550x800 for centering, matching Chair2D settings
    private static final int WINDOW_WIDTH = 1550;
    private static final int WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        Group root = createBenchView();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("2D Bench in JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Group createBenchView() {
        Image woodImage = new Image(Bench2D.class.getResourceAsStream("/resources/metal.jpg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Create the bench components and apply the wood pattern
        Rectangle seat = new Rectangle(300, 30);
        seat.setFill(woodPattern);

        Rectangle backrest = new Rectangle(280, 80);
        backrest.setFill(woodPattern);

        Rectangle leg1 = new Rectangle(20, 50);
        leg1.setFill(woodPattern);

        Rectangle leg2 = new Rectangle(20, 50);
        leg2.setFill(woodPattern);

        // Calculate center position for the bench
        double centerX = (WINDOW_WIDTH - seat.getWidth()) / 2;
        double centerY = (WINDOW_HEIGHT - (seat.getHeight() + backrest.getHeight())) / 2 + backrest.getHeight();

        // Set positions relative to the center
        seat.setX(centerX);
        seat.setY(centerY);

        backrest.setX(centerX + (seat.getWidth() - backrest.getWidth()) / 2);
        backrest.setY(centerY - backrest.getHeight());

        leg1.setX(centerX);
        leg1.setY(centerY + seat.getHeight());

        leg2.setX(centerX + seat.getWidth() - leg2.getWidth());
        leg2.setY(centerY + seat.getHeight());

        root.getChildren().addAll(seat, backrest, leg1, leg2);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
