package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.TaskBean;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WeeklyController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField course_name;

    @FXML
    private TextField deadline;

    @FXML
    private TextField task_name;

    @FXML
    private ChoiceBox<String> difficulty;

    private final String[] levels = {"Easy","Medium","Hard"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficulty.getItems().addAll(levels);
        deadline.setPromptText("ex. 2201128");
        difficulty.setValue("Easy");
    }

    @FXML
    void onSubmitClick(ActionEvent event) throws IOException {
        DBApi myCon = new DBApi();
//        myCon.initDBWeeklyOneTask();

        String course = course_name.getText();
        String task = task_name.getText();
        String level = difficulty.getValue();
        int endDate = Integer.parseInt(deadline.getText());

        // 0. Table course_schedule_tasks should be truncated.
        // 1. Create course_tasks table with data above (NOT in this method)
        // 2. Store data above in course_tasks table (create a method to store this data)

        myCon.insertTaskItems(new TaskBean(course, level, endDate, task));

        List<TaskBean> task_list = myCon.retrieveCourseTaskFromDB();
        WeeklySchedule weeklyData = new WeeklySchedule("Agile", "Hard", 220610, "learn");

        for (TaskBean t: task_list) {
            WeeklySchedule myCourse = new WeeklySchedule(t.getCourse_name(), t.getDifficulty(), t.getDeadline(), t.getTask_name());
            weeklyData.sortAddOnEndDate(weeklyData.getMyWeekList(), myCourse);
        }

        myCon.insertWeeklyScheduleItems(weeklyData.createWeeklyOneTask(weeklyData.getMyWeekList()));
        onCancelClick(event);
    }

    @FXML
    void onCancelClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("timage-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
