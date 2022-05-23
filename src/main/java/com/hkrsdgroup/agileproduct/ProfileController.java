package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProfileController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private DBApi dbc = new DBApi();

    @FXML
    private TextField sleepHour;

    @FXML
    private TextField studyHour;

    @FXML
    private TextField studyMinute;

    @FXML
    private TextField workoutType;


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
       String selfActivity = workoutType.getText();
       String combineCourseWithTask = "free_studying";

        List<CourseScheduleTaskBean> scheduleTasks = dbc.retrieveCourseScheduleTaskForTodayFromDB();
        if(scheduleTasks.size() >= 1){
        String courseName = scheduleTasks.get(0).getCourse();
        String taskName = scheduleTasks.get(0).getTaskName();
        combineCourseWithTask = courseName + '_' + taskName;
       }
       DailySchedule myDay = new DailySchedule(sleepTime, combineCourseWithTask, selfActivity, studyHr, studyMin);

       myDay.sendDailyScheduleToDB(myDay.ScheduleDayOnlyLongSession());
       onCancelClick(event);
    }
}
