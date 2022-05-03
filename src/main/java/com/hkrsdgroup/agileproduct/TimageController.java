package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ResourceBundle;

public class TimageController {
    @FXML
//    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private

//    @FXML
//    protected void onHelloButtonClick() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText("Person editing error");
//        alert.setContentText("One person must be selected when editing");
//        alert.show();
//    }
    @FXML
    void onDailyScheduleButtonClick(ActionEvent event) throws IOException {
        // this will print the schedule in the terminal(not in GUI)
        root = FXMLLoader.load(getClass().getResource("Scenebuilder_output.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        myConn.retrieveDailyScheduleFromDB();
    }

    @FXML
    void onFillProfileButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("create-profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onFillWeeklyButtonClick(ActionEvent event) {

    }

    @FXML
    void onWeeklyScheduleButtonClick(ActionEvent event) {

    }
}
