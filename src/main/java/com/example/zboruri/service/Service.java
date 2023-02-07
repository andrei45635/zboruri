package com.example.zboruri.service;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Flight;
import com.example.zboruri.repository.db.ClientRepoDB;
import com.example.zboruri.repository.db.FlightRepoDB;
import com.example.zboruri.utils.event.EntityChangeEvent;
import com.example.zboruri.utils.observer.Observable;
import com.example.zboruri.utils.observer.Observer;

import java.time.LocalDateTime;
import java.util.*;

public class Service implements Observable<EntityChangeEvent> {
    private final List<Observer<EntityChangeEvent>> observers = new ArrayList<>();
    private ClientRepoDB clientRepoDB = new ClientRepoDB();
    private FlightRepoDB flightRepoDB = new FlightRepoDB();

    public Service(ClientRepoDB clientRepoDB, FlightRepoDB flightRepoDB) {
        this.clientRepoDB = clientRepoDB;
        this.flightRepoDB = flightRepoDB;
    }

    public boolean checkIfUserExists(String username){
        return clientRepoDB.findClient(username);
    }

    public Client findLoggedInClient(String username){
        for(Client client: this.getAllClients()){
            if(Objects.equals(client.getUsername(), username)){
                return client;
            }
        }
        return null;
    }

    public List<Flight> getFlights(String from, String to, LocalDateTime date){
        return flightRepoDB.findFlightsGivenLocation(from, to, date);
    }

    public List<Client> getAllClients(){
        return clientRepoDB.findAll();
    }

    public Set<String> getFromLocations(){
        List<Flight> flights = this.getAllFlights();
        Set<String> from = new HashSet<>();
        for(Flight fl : flights){
            from.add(fl.getFrom());
        }
        return from;
    }

    public Set<String> getToLocations(){
        List<Flight> flights = this.getAllFlights();
        Set<String> to = new HashSet<>();
        for(Flight fl : flights){
            to.add(fl.getTo());
        }
        return to;
    }

    public List<Flight> getAllFlights() {
        return flightRepoDB.findAll();
    }

    @Override
    public void addObserver(Observer<EntityChangeEvent> obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers(EntityChangeEvent entityChangeEvent) {
        observers.forEach(x->x.update(entityChangeEvent));
    }
}
