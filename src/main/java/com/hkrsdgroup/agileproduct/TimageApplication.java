package com.hkrsdgroup.agileproduct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class TimageApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimageApplication.class.getResource("timage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Timage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        DBApi myConnection = new DBApi(rb.getString("dsn"));
        myConnection.initDB();
        launch();
    }
}