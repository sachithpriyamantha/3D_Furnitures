package FurniturePackage;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Chair2D {

    // Assume window size of 800x600 for centering
    private static final int WINDOW_WIDTH = 1550;
    private static final int WINDOW_HEIGHT = 800;

    public Group createChairView() {
        Image woodImage = new Image(getClass().getResourceAsStream("/resources/bg2.jpeg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        Rectangle seat = new Rectangle(100, 20);
        seat.setFill(woodPattern);

        Rectangle backrest = new Rectangle(80, 100);
        backrest.setFill(woodPattern);

        Rectangle leg1 = new Rectangle(20, 50);
        leg1.setFill(woodPattern);

        Rectangle leg2 = new Rectangle(20, 50);
        leg2.setFill(woodPattern);

        // Calculate center position for the chair
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
}
