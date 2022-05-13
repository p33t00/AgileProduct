package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
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
    private TableColumn<CourseScheduleTaskBean, String> task;

    @FXML
    private TableColumn<CourseScheduleTaskBean, String> course;

    @FXML
    private TableColumn<CourseScheduleTaskBean, String> taskDate;

    @FXML
    private TableColumn<CourseScheduleTaskBean, String> difficulty;

    @FXML
    private TableView<CourseScheduleTaskBean> weeklyTable;


    ObservableList<CourseScheduleTaskBean> scheduleTasks = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBApi dbc = new DBApi();

        dbc.retrieveCourseScheduleTaskFromDB();


        taskDate.setCellValueFactory(new PropertyValueFactory<CourseScheduleTaskBean, String>("taskDate"));
        course.setCellValueFactory(new PropertyValueFactory<CourseScheduleTaskBean, String>("course"));
        task.setCellValueFactory(new PropertyValueFactory<CourseScheduleTaskBean, String>("task"));
        difficulty.setCellValueFactory(new PropertyValueFactory<CourseScheduleTaskBean, String>("difficulty"));

        scheduleTasks.addAll(dbc.retrieveCourseScheduleTaskFromDB());
        weeklyTable.setItems(scheduleTasks);
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

