package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Statement;
import java.sql.ResultSet;

public class TableController implements Initializable {

        @FXML
        private TableColumn<DayScheduleItemBean, Integer> Id;

        @FXML
        private TableColumn<DayScheduleItemBean, String> Activity;

        @FXML
        private TableColumn<DayScheduleItemBean, String> Time;

        @FXML
        private TableColumn<DayScheduleItemBean, Byte> Done;

        @FXML
        private TableView<DayScheduleItemBean> Table;



        ObservableList<DayScheduleItemBean> l = FXCollections.observableArrayList();

        public void initialize(URL location, ResourceBundle resources){
                DBApi dbc = new DBApi();

                Id.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,Integer>("id"));
                Activity.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,String>("activity"));
                Time.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,String>("time"));
                Done.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,Byte>("done"));


                String connectQuery = "SELECT * FROM day_schedule_items";

                try{

                        List<DayScheduleItemBean> schedule = dbc.retrieveDailyScheduleFromDB();
                        for (int i = 0; i < schedule.size(); i++) {
                                DayScheduleItemBean sch = schedule.get(i);
                                Integer id = sch.getId();
                                String activity = sch.getActivity();
                                String time = sch.getTime();
                                Byte done = sch.getState();
                                l.add(new DayScheduleItemBean(id, activity, time, done));

                        }

                        Table.setItems(l);

                } catch (Exception e){
                        e.printStackTrace();

                }
        }

}


