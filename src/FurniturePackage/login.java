package FurniturePackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class login extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox mainContainer = new VBox(); // Main container
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(25));
        mainContainer.setPrefSize(800, 600); // Increase height and width

        StackPane root = new StackPane(); // StackPane for the transparent box
        root.setAlignment(Pos.CENTER);

        // Transparent box with white border
        Rectangle border = new Rectangle(600, 400);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(5); // Define the border width

        // Apply the shadow effect to create a 3D look
        border.setEffect(new javafx.scene.effect.DropShadow(10, 5, 5, Color.WHITE));

        VBox content = new VBox(20); // Container for login components
        content.setAlignment(Pos.CENTER);

        Label lblTitle = new Label("Login");
        lblTitle.setId("dashboard-title");

        TextField txtInput = new TextField();
        txtInput.setPromptText("Username");
        txtInput.setPrefWidth(150);

        PasswordField txtpwd = new PasswordField();
        txtpwd.setPromptText("Password");
        txtpwd.setPrefWidth(150);

        Label lblFeedback = new Label("Welcome to the Furniture shop !");
        lblFeedback.setId("feedback-label");

        Button btnLogin = new Button("Login");
        Hyperlink signInLink = new Hyperlink("Sign In");
        btnLogin.getStyleClass().add("dashboard-button");
        signInLink.getStyleClass().add("sign-in-link");

        txtInput.textProperty().addListener((observable, oldValue, newValue) -> {
            lblFeedback.setText("Input: " + newValue);
        });

        // Example navigation functionality for btnLogin and signInLink
        btnLogin.setOnAction(e -> {
            System.out.println("Login button clicked...");
            opendash(primaryStage);

        });


        signInLink.setOnAction(e -> {
            System.out.println("Sign In link clicked...");
            openSignInPage(primaryStage);
        });

        HBox buttonBox = new HBox(10); // Spacing between buttons
        buttonBox.getChildren().addAll(signInLink, btnLogin);
        buttonBox.setAlignment(Pos.CENTER); // Centering buttons horizontally

        // Adding components to the content VBox
        content.getChildren().addAll(lblTitle, txtInput, txtpwd, lblFeedback, buttonBox);

        // Adding the content VBox and border to the StackPane
        root.getChildren().addAll(border, content);

        mainContainer.getChildren().add(root); // Add the transparent box to the main container

        Scene scene = new Scene(mainContainer, 1550, 800); // Increase scene size
        scene.getStylesheets().add(getClass().getResource("logsig.css").toExternalForm());

        primaryStage.setTitle("Furniture_Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openSignInPage(Stage primaryStage) {
        SignIn signIn = new SignIn();
        signIn.start(primaryStage);
    }
    private void opendash(Stage primaryStage) {
        DashboardApp Dash = new DashboardApp();
        Dash.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
