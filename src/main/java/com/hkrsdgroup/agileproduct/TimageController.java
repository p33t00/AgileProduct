package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

    void onFillWeeklyButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fill-weekly.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        // TODO
//        DBApi myCon = new DBApi();
//        myCon.initDBWeeklyOneTask();
//
//        ArrayList<WeeklySchedule> myWeek = new ArrayList<>();
//        ArrayList<ArrayList> completeWeek = new ArrayList<>();
//
//        WeeklySchedule myCourse = new WeeklySchedule("agile", "hard", 220321, "learn jira");
//        myCourse.sortAddOnEndDate(myWeek, myCourse);
//        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "medium", 240321, "assignment connect");
//        myCourse.sortAddOnEndDate(myWeek, myCourse2);
//        WeeklySchedule myCourse3 = new WeeklySchedule("python", "easy", 210532, "make clean code");
//        myCourse.sortAddOnEndDate(myWeek, myCourse3);
//        WeeklySchedule myCourse4 = new WeeklySchedule("python", "easy", 210532, "implement functions");
//        myCourse.sortAddOnEndDate(myWeek, myCourse4);
//
//        completeWeek = myCourse.createWeeklyOneTask(myWeek);
//        myCon.insertWeeklyScheduleItems(completeWeek);
        
    }

    @FXML
    void onResetScheduleClick(ActionEvent event) {
        DBApi myCon = new DBApi();
        myCon.removeDailyScheduleFromDB();
        myCon.resetIdDailyScheduleDB();
        myCon.removeWeeklyScheduleFromDB();
        myCon.resetIdWeeklyScheduleDB();
    }

    @FXML
    void onWeeklyScheduleButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WeeklyTableView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
