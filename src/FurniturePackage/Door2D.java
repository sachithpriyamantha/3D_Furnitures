package FurniturePackage;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Door2D {

    // Assume window size for centering
    private static final int WINDOW_WIDTH = 1550;
    private static final int WINDOW_HEIGHT = 800;

    public static Group createDoorView() {
        Image woodImage = new Image(Door2D.class.getResourceAsStream("/resources/metal.jpg"));
        ImagePattern woodPattern = new ImagePattern(woodImage);

        Group root = new Group();

        // Create the door panel with wood texture
        Rectangle doorPanel = new Rectangle(200, 400); // Adjust dimensions as needed
        doorPanel.setFill(woodPattern);

        // Create the door frame slightly larger than the door panel
        Rectangle doorFrame = new Rectangle(210, 410); // Adjust dimensions as needed
        doorFrame.setFill(woodPattern);

        // Calculate center position for the door
        double centerX = (WINDOW_WIDTH - doorPanel.getWidth()) / 2;
        double centerY = (WINDOW_HEIGHT - doorPanel.getHeight()) / 2;

        // Set positions relative to the center
        doorPanel.setX(centerX);
        doorPanel.setY(centerY);

        doorFrame.setX(centerX - 5); // Slightly offset for the frame effect
        doorFrame.setY(centerY - 5); // Slightly offset for the frame effect

        // Add frame first so it's below the door panel
        root.getChildren().add(doorFrame);
        root.getChildren().add(doorPanel);

        return root;
    }
}
