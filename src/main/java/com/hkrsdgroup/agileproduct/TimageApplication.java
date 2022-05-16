package com.hkrsdgroup.agileproduct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TimageApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimageApplication.class.getResource("timage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Image icon = new Image(getClass().getResourceAsStream("timage-icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Timage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DBApi myConnection = new DBApi();
        myConnection.initDB();
        myConnection.initDBWeeklyOneTask();
        myConnection.initDBCourseTask();
        launch();
    }
}