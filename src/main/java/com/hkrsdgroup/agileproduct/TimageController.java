package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class TimageController {
    @FXML
    private Label welcomeText;

//    @FXML
//    protected void onHelloButtonClick() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText("Person editing error");
//        alert.setContentText("One person must be selected when editing");
//        alert.show();
//    }
    @FXML
    void onDailyScheduleButtonClick(ActionEvent event) {
        // this will print the schedule in the terminal(not in GUI)
        DBApi myConn = new DBApi("dsn");

        myConn.retrieveDailyScheduleFromDB();
    }

    @FXML
    void onFillProfileButtonClick(ActionEvent event) {
        DailySchedule myDay = new DailySchedule(8, "agile" ,"gym", 510);

        myDay.ScheduleDayOnlyLongSession();
    }

    @FXML
    void onFillWeeklyButtonClick(ActionEvent event) {

    }

    @FXML
    void onWeeklyScheduleButtonClick(ActionEvent event) {

    }
}