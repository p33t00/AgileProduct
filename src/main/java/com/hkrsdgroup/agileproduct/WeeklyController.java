package com.hkrsdgroup.agileproduct;

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

        WeeklySchedule weeklyData = new WeeklySchedule(course, level, endDate, task);
        weeklyData.sortAddOnEndDate(weeklyData.getMyWeekList(), weeklyData);

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
