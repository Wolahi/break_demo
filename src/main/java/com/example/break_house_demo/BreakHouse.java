package com.example.break_house_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class BreakHouse extends Application {

    private Scene scene;
    private GameController controller = new GameController();
// Иницализация сцены
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BreakHouse.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 1480, 1080);
        stage.setTitle("BreakHouse (alpha)");
        stage.setScene(scene);
        stage.show();
        controller.startScene(scene, stage);
    }

    public static void main(String[] args) {
        launch();
    }
}