package com.example.zboruri.controller;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Flight;
import com.example.zboruri.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ClientsController {
    @FXML
    private Label warningFlightLabel;
    @FXML
    private TableView<Flight> flightTableView;
    @FXML
    private TableColumn<Flight, String> fromColumn;
    @FXML
    private TableColumn<Flight, String> toColumn;
    @FXML
    private TableColumn<Flight, LocalDateTime> arrivalColumn;
    @FXML
    private TableColumn<Flight, LocalDateTime> departureColumn;
    @FXML
    private TableColumn<String, Integer> seatsColumn;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private ComboBox<String> fromComboBox;
    @FXML
    private ComboBox<String> toComboBox;
    @FXML
    private Label welcomeLabel;
    private ObservableList<Flight> flightModel = FXCollections.observableArrayList();
    private Service service;
    private Client loggedInClient;
    public void setService(Service service) {
        this.service = service;
        fromComboBox.setItems(FXCollections.observableArrayList(service.getFromLocations()));
        toComboBox.setItems(FXCollections.observableArrayList(service.getToLocations()));
    }

    public void setClient(Client client){
        this.loggedInClient = client;
    }

    public void setWelcomeLabel(String text){
        welcomeLabel.setText("Welcome, " + text);
    }

    @FXML
    public void initialize(){
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        flightTableView.setItems(flightModel);
    }

    public void initModel(){
        LocalDate dateVal = departureDatePicker.getValue();
        LocalDateTime departureDate = dateVal.atStartOfDay();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();
        List<Flight> flights = service.getFlights(from, to, departureDate);
        if(flights.isEmpty()){
            warningFlightLabel.setText("No flights available for the given dates and locations!\n");
        }
        else{
            flightModel.setAll(service.getFlights(from, to, departureDate));
            warningFlightLabel.setText("");
        }
    }

    @FXML
    private void onClickFindFlight(ActionEvent actionEvent) {
        initModel();
    }
}
