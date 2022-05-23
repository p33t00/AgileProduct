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
    private DBApi dbc = new DBApi();

    @FXML
    void onDailyScheduleButtonClick(ActionEvent event) throws IOException {
        createView(event, "table-view.fxml");
    }

    @FXML
    void onFillProfileButtonClick(ActionEvent event) throws IOException {
        createView(event, "create-profile.fxml");
    }

    @FXML

    void onFillWeeklyButtonClick(ActionEvent event) throws IOException {
        createView(event, "fill-weekly.fxml");
    }

    @FXML
    void onWeeklyScheduleButtonClick(ActionEvent event) throws IOException {
        createView(event, "WeeklyTableView.fxml");
    }

    private void createView(ActionEvent event, String viewResourceName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(viewResourceName));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onResetScheduleClick(ActionEvent event) {
        dbc.removeDailyScheduleFromDB();
        dbc.resetIdDailyScheduleDB();
        dbc.removeWeeklyScheduleFromDB();
        dbc.resetIdWeeklyScheduleDB();
        dbc.removeTasksFromDB();
        dbc.resetIdTasksScheduleDB();
    }
}
