package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField sleepHour;

    @FXML
    private TextField studyHour;

    @FXML
    private TextField studyMinute;

    @FXML
    private TextField workoutType;

    @FXML
    private TextField courseName;

    @FXML
    void onCancelClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("timage-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSubmitClick(ActionEvent event) throws IOException {
       int sleepTime = Integer.parseInt(sleepHour.getText());
       int studyHr = Integer.parseInt(studyHour.getText());
       int studyMin = Integer.parseInt(studyMinute.getText());
       String workout = workoutType.getText();
       String course = courseName.getText();
       DailySchedule myDay = new DailySchedule(sleepTime, course, workout, studyHr, studyMin);

       myDay.sendDailyScheduleToDB(myDay.ScheduleDayOnlyLongSession());
       onCancelClick(event);
    }
}
