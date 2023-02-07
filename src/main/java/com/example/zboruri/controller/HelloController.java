package com.example.zboruri.controller;

import com.example.zboruri.HelloApplication;
import com.example.zboruri.domain.Client;
import com.example.zboruri.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button loginButton;
    private Service service;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameLoginTF;

    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private void onClickLogin(ActionEvent actionEvent) throws IOException {
        if(!service.checkIfUserExists(usernameLoginTF.getText())){
            errorLabel.setText("Invalid username!\n");
            return;
        }
        Client loggedInClient = service.findLoggedInClient(usernameLoginTF.getText());
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("clients-view.fxml"));
        Parent root = loader.load();
        ClientsController clientsController = loader.getController();
        clientsController.setService(service);
        clientsController.setClient(loggedInClient);
        clientsController.setWelcomeLabel(loggedInClient.getName());
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 600));
        stage.setTitle("Hello!");
        stage.show();

        Stage thisStage = (Stage) loginButton.getScene().getWindow();
        thisStage.close();
    }
}