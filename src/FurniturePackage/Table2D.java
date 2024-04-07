package FurniturePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Table2D extends Application {

    // Window dimensions for centering
    private static final int WINDOW_WIDTH = 1550;
    private static final int WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        Group root = createTableView();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.LIGHTGRAY);

        primaryStage.setTitle("Centered Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Group createTableView() {
        Image woodImage = new Image(Table2D.class.getResourceAsStream("/resources/bg3.jpeg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Create the tabletop with the wood image pattern
        Rectangle tabletop = new Rectangle(300, 30);
        tabletop.setFill(woodPattern);

        // Center the tabletop within the window
        double centerX = (WINDOW_WIDTH - tabletop.getWidth()) / 2;
        double centerY = (WINDOW_HEIGHT - tabletop.getHeight()) / 2;

        tabletop.setX(centerX);
        tabletop.setY(centerY);

        // Table legs - adjust the position according to the centered tabletop's position
        Rectangle leg1 = new Rectangle(20, 100);
        leg1.setFill(woodPattern);
        leg1.setX(tabletop.getX());
        leg1.setY(tabletop.getY() + tabletop.getHeight());

        Rectangle leg2 = new Rectangle(20, 100);
        leg2.setFill(woodPattern);
        leg2.setX(tabletop.getX() + tabletop.getWidth() - leg2.getWidth());
        leg2.setY(tabletop.getY() + tabletop.getHeight());

        Rectangle leg3 = new Rectangle(20, 100);
        leg3.setFill(woodPattern);
        leg3.setX(tabletop.getX() + tabletop.getWidth() / 3 - leg3.getWidth() / 2);
        leg3.setY(tabletop.getY() + tabletop.getHeight());

        Rectangle leg4 = new Rectangle(20, 100);
        leg4.setFill(woodPattern);
        leg4.setX(tabletop.getX() + 2 * (tabletop.getWidth() / 3) - leg4.getWidth() / 2);
        leg4.setY(tabletop.getY() + tabletop.getHeight());

        // Add all components to the root group
        root.getChildren().addAll(tabletop, leg1, leg2, leg3, leg4);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
