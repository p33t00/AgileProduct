package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {

        @FXML
        private TableColumn<DayScheduleItemBean, Integer> Id;

        @FXML
        private TableColumn<DayScheduleItemBean, String> Activity;

        @FXML
        private TableColumn<DayScheduleItemBean, String> Time;

        @FXML
        private TableColumn<DayScheduleItemBean, Boolean> State;

        @FXML
        private TableView<DayScheduleItemBean> Table;

        ObservableList<DayScheduleItemBean> scheduleItems = FXCollections.observableArrayList(daily ->
                new Observable[] {daily.getStateProperty()});

        public void initialize(URL location, ResourceBundle resources) {
                DBApi dbc = new DBApi();

                Id.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,Integer>("id"));
                Activity.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,String>("activity"));
                Time.setCellValueFactory(new PropertyValueFactory<DayScheduleItemBean,String>("time"));
                State.setCellValueFactory(cellData -> cellData.getValue().getStateProperty());
                State.setCellFactory(p -> new CheckBoxTableCell<>());

                try {
                        scheduleItems.addListener((ListChangeListener<DayScheduleItemBean>) b -> {
                                while (b.next()) {
                                        if (b.wasUpdated()) {
                                                dbc.updateDailyItemState(scheduleItems.get(b.getFrom()).getId(),
                                                        scheduleItems.get(b.getFrom()).getStateProperty().get());
                                        }
                                }
                        });
                        scheduleItems.addAll(dbc.retrieveDailyScheduleFromDB());
                        Table.setItems(scheduleItems);
                } catch (Exception e){
                        e.printStackTrace();
                }
        }
}
