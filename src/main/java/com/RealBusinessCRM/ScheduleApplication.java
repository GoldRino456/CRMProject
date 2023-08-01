package com.RealBusinessCRM;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleApplication extends Application {

    private static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        ScheduleApplication.stage = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleApplication.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RBSS - Real Business Scheduling Solutions");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the scene currently on the stage.
     *
     * @param fxml String - fxml file name or path
     * @exception IOException required to handle FXMLLoader.load()
     */
    public void changeScene(String fxml) throws IOException
    {
        Parent pane = FXMLLoader.load(ScheduleApplication.class.getResource(fxml));
        stage.getScene().setRoot(pane);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}