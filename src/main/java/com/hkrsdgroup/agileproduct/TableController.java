package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {
        private Stage stage;
        private Scene scene;
        private Parent root;
        private DBApi dbc = new DBApi();

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

        @FXML
        void onBackClick(ActionEvent event) throws IOException {
                root = FXMLLoader.load(getClass().getResource("timage-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


        ObservableList<DayScheduleItemBean> scheduleItems = FXCollections.observableArrayList(daily ->
                new Observable[] {daily.getStateProperty()});

        public void initialize(URL location, ResourceBundle resources) {
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
