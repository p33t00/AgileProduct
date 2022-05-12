package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.WeeklyScheduleBean;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TableColumn<WeeklyScheduleBean, String> assignment;

    @FXML
    private TableColumn<WeeklyScheduleBean, String> course;

    @FXML
    private TableColumn<WeeklyScheduleBean, String> date;

    @FXML
    private TableColumn<WeeklyScheduleBean, String> difficulty;

    @FXML
    private TableView<WeeklyScheduleBean> weeklyTable;


    ObservableList<WeeklyScheduleBean> scheduleItems = FXCollections.observableArrayList(weekly ->
            new Observable[] {weekly.getStateProperty()});


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBApi dbc = new DBApi();

        date.setCellValueFactory(new PropertyValueFactory<WeeklyScheduleBean, String>("Date"));
        course.setCellValueFactory(new PropertyValueFactory<WeeklyScheduleBean, String>("Course"));
        assignment.setCellValueFactory(new PropertyValueFactory<WeeklyScheduleBean, String>("Assignment"));
        difficulty.setCellValueFactory(new PropertyValueFactory<WeeklyScheduleBean, String>("Difficulty"));

        scheduleItems.addAll(dbc.retrieveWeeklyScheduleFromDB());
        weeklyTable.setItems(scheduleItems);
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

