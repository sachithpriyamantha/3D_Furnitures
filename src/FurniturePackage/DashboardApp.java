package FurniturePackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DashboardApp extends Application {
    private Stage primaryStage; // Field to hold the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();

        // Navigation Bar Buttons
        Button homeButton = new Button("Dashboard");
        homeButton.getStyleClass().add("nav-button");
        homeButton.setOnAction(e -> System.out.println("Dashboard!"));

        Button aboutButton = new Button("About");
        aboutButton.getStyleClass().add("nav-button");
        aboutButton.setOnAction(e -> System.out.println("About Clicked!"));

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("nav-button");

        logoutButton.setOnAction(e -> {
            System.out.println("Logout Clicked!");


        });



        // Linked navigation
        logoutButton.setOnAction(e -> root.setCenter(openLogIN(primaryStage)));
        aboutButton.setOnAction(e -> root.setCenter(createAboutPage()));
        homeButton.setOnAction(e -> root.setCenter(opendash(primaryStage)));

        // Navigation Bar Layout
        HBox navBar = new HBox(10, homeButton, aboutButton, logoutButton);
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setPadding(new Insets(10));

        // Profile Picture
        Image image = new Image("resources/profile.png"); // Adjust the path to your image
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50); // Adjust the size as necessary
        imageView.setFitWidth(50);

        // Creating a circle clip
        Circle clip = new Circle(25, 25, 25); // Assuming the imageView is 50x50, adjust the radius accordingly
        imageView.setClip(clip);

        // Wrap the ImageView in a StackPane to apply a border
        StackPane imageContainer = new StackPane(imageView);
        imageContainer.getStyleClass().add("image-container");

        // Top-Right Container for Profile Picture
        HBox profileContainer = new HBox(imageContainer);
        profileContainer.setAlignment(Pos.CENTER_RIGHT);
        profileContainer.setPadding(new Insets(10));

        // Top Container for Navigation Bar and Profile Picture
        HBox topContainer = new HBox(navBar, profileContainer);
        HBox.setHgrow(navBar, Priority.ALWAYS); // This ensures the navBar can expand and push the profileContainer to the right

        root.setTop(topContainer);


        // Main Container for Dashboard Content
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(25));
        mainContainer.setPrefSize(800, 600);

        Label lblTitle = new Label("DASHBOARD");
        lblTitle.setId("dashboard-title");

        StackPane centerContent = new StackPane();
        centerContent.setAlignment(Pos.CENTER);

        Rectangle border = new Rectangle(600, 400);
        border.setFill(Color.TRANSPARENT); // Set fill to transparent
        border.setStroke(Color.BLACK); // Define the border color
        border.setStrokeWidth(8); // Define the border width

        // Apply the shadow effect to create a 3D look
        border.setEffect(new javafx.scene.effect.DropShadow(10, 5, 5, Color.WHITE));

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);

        GridPane boxesGrid = new GridPane();
        boxesGrid.setHgap(20);
        boxesGrid.setVgap(20);
        boxesGrid.setAlignment(Pos.CENTER);

        Pane boxObject = createBox("Furniture View");
        boxesGrid.add(boxObject, 0, 0);

        Pane boxFurnitureRoom = createBox("Interior Room"); // Added for completeness
        boxesGrid.add(boxFurnitureRoom, 1, 0);

        content.getChildren().add(boxesGrid);
        centerContent.getChildren().addAll(border, content);
        mainContainer.getChildren().addAll(lblTitle, centerContent);

        root.setCenter(mainContainer);

        Scene scene = new Scene(root, 1550, 800);
        scene.getStylesheets().add(getClass().getResource("dashboard.css").toExternalForm());

        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Pane createAboutPage() {
        VBox aboutPage = new VBox(20); // Increase spacing for better readability
        aboutPage.setAlignment(Pos.TOP_CENTER);
        aboutPage.setPadding(new Insets(25));

        Label aboutTitle = new Label("ABOUT US");
        aboutTitle.setId("about-title");

        Label visionTitle = new Label("Our Vision");
        visionTitle.setId("vision-title");
        Label visionText = new Label("To be the leading provider of quality furniture worldwide.");
        visionText.setWrapText(true);
        visionText.setId("vision-text");

        Label missionTitle = new Label("Our Mission");
        missionTitle.setId("mission-title");
        Label missionText = new Label("To design, build, and sell the finest quality furniture with a commitment to sustainability.");
        missionText.setWrapText(true);
        missionText.setId("mission-text");

        Label contactTitle = new Label("Contact Us");
        contactTitle.setId("contact-title");
        Label contactInfo = new Label("Name: Furniture House\nPhone: +94 76 656 8943\nEmail: FurnitureHouse@gmail.com.");
        contactInfo.setId("contact-info");
        contactInfo.setWrapText(true);

        aboutPage.getChildren().addAll(aboutTitle, visionTitle, visionText, missionTitle, missionText, contactTitle, contactInfo);
        return aboutPage;
    }
    private Node openLogIN(Stage primaryStage) {
        login Login = new login();
        Login.start(primaryStage);
        return null;
    }
    private Node opendash(Stage primaryStage) {
        DashboardApp Dash = new DashboardApp();
        Dash.start(primaryStage);
        return null;
    }
    private Pane createBox(String boxType) {
        VBox box = new VBox();
        box.setPrefSize(150, 100);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("box");

        Button btn = new Button(boxType);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(btn, Priority.ALWAYS);
        btn.getStyleClass().add("box-button");

        if ("Furniture View".equals(boxType)) {
            btn.setOnAction(event -> loadMainPage()); // Handle "Object" box type
        } else if ("Interior Room".equals(boxType)) {
            btn.setOnAction(event -> {
                FurnitureWorld furnitureWorld = new FurnitureWorld();
                Stage stage = new Stage();
                furnitureWorld.launchFurnitureWorld(stage); // Launch FurnitureWorld for "Furniture Room"
            });
        } else {
            btn.setOnAction(event -> System.out.println(boxType + " button clicked")); // Default action for other box types
        }

        box.getChildren().add(btn);
        return box;
    }


    private void loadMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FurniturePackage/MainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1550, 800);
            scene.getStylesheets().add(getClass().getResource("/FurniturePackage/styles.css").toExternalForm());
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
