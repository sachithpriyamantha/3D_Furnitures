package FurniturePackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FurniturePageController {

    @FXML
    protected void onViewChair(ActionEvent event) {
        Chair2D chair2D = new Chair2D();
        Group chairView = chair2D.createChairView();

        // Assuming the button is part of the scene you want to change
        Scene currentScene = ((Node)event.getSource()).getScene();
        currentScene.setRoot(chairView);
    }

    @FXML
    protected void onViewBed(ActionEvent event) {
        Group bedView = Bed2D.createBedView();
        Scene currentScene = ((Node)event.getSource()).getScene();
        currentScene.setRoot(bedView);
    }

    @FXML
    protected void onViewCupboard(ActionEvent event) {
        Group cupboardView = Cupboard2D.createCupboardView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(cupboardView);
    }

    @FXML
    protected void onViewTable(ActionEvent event) {
        Group tableView = Table2D.createTableView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(tableView);
    }

    @FXML
    protected void onViewRack(ActionEvent event) {
        Group rackView = Rack2D.createRackView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(rackView);
    }

    @FXML
    protected void onViewBench(ActionEvent event) {
        Group benchView = Bench2D.createBenchView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(benchView);
    }

    @FXML
    protected void onViewDoor(ActionEvent event) {
        Group doorView = Door2D.createDoorView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(doorView);
    }

    @FXML
    protected void onViewBookshelf(ActionEvent event) {
        Group bookshelfView = Bookshelf2D.createBookshelfView();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(bookshelfView);
    }


    @FXML
    protected void onBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FurniturePackage/MainPage.fxml"));
            Scene scene = new Scene(root, 1550, 800);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
