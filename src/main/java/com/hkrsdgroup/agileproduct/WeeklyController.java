package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.TaskBean;
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
import java.util.Comparator;
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
        myCon.removeWeeklyScheduleFromDB();

        String course = course_name.getText();
        String task = task_name.getText();
        String level = difficulty.getValue();
        int endDate = Integer.parseInt(deadline.getText());

        myCon.insertTaskItems(new TaskBean(course, level, endDate, task));

        List<TaskBean> task_list = myCon.retrieveCourseTaskFromDB();
        WeeklySchedule weeklyData = new WeeklySchedule();

        // sorting tasks by deadline
        task_list.sort(Comparator.comparing(TaskBean::getDeadline));

        myCon.insertWeeklyScheduleItems(weeklyData.createWeeklyOneTask(task_list));
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
