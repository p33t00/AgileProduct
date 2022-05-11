package com.hkrsdgroup.agileproduct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableWeeklyViewController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<WeeklySchedule, String> assignment;

    @FXML
    private TableColumn<WeeklySchedule, String> course;

    @FXML
    private TableColumn<WeeklySchedule, String> date;

    @FXML
    private TableColumn<WeeklySchedule, String> day;

    @FXML
    private TableView<WeeklySchedule> weeklyTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        day.setCellValueFactory(new PropertyValueFactory<WeeklySchedule, String>("Day"));
        date.setCellValueFactory(new PropertyValueFactory<WeeklySchedule, String>("Date"));
        course.setCellValueFactory(new PropertyValueFactory<WeeklySchedule, String>("Course"));
        assignment.setCellValueFactory(new PropertyValueFactory<WeeklySchedule, String>("Assignment"));
    }

    @FXML
    void OnReturnClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("timage-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

