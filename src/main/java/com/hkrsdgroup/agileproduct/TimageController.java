package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TimageController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void onDailyScheduleButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        // TODO
    }

    @FXML
    void onResetScheduleClick(ActionEvent event) {
        DBApi myCon = new DBApi();
        myCon.removeDailyScheduleFromDB();
        myCon.resetIdDailyScheduleDB();
    }

    @FXML
    void onWeeklyScheduleButtonClick(ActionEvent event) {
        // TODO
    }
}
