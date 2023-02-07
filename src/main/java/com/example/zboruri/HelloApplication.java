package com.example.zboruri;

import com.example.zboruri.controller.HelloController;
import com.example.zboruri.repository.db.ClientRepoDB;
import com.example.zboruri.repository.db.FlightRepoDB;
import com.example.zboruri.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        HelloController controller = fxmlLoader.getController();
        controller.setService(new Service(new ClientRepoDB(), new FlightRepoDB()));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}