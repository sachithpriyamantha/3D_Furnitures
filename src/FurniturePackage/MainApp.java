package FurniturePackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FurniturePackage/MainPage.fxml"));
        primaryStage.setTitle("2D and 3D Viewer");
        Scene scene = new Scene(root, 1550, 800);
        scene.getStylesheets().add(getClass().getResource("/FurniturePackage/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
