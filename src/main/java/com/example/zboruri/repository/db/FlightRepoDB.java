package com.example.zboruri.repository.db;

import com.example.zboruri.domain.Flight;
import com.example.zboruri.repository.IRepository;
import com.example.zboruri.utils.db.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightRepoDB implements IRepository<Integer, Flight> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                long flightID = resultSet.getLong("flightid");
                String fromLoc = resultSet.getString("fromloc");
                String toLoc = resultSet.getString("toloc");
                LocalDateTime departure = resultSet.getTimestamp("departure").toLocalDateTime();
                LocalDateTime arrival = resultSet.getTimestamp("arrival").toLocalDateTime();
                int seats = resultSet.getInt("seats");

                Flight flight = new Flight(id, flightID, fromLoc, toLoc, departure, arrival, seats);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public List<Flight> findFlightsGivenLocation(String from, String to, LocalDateTime date){
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE fromloc = ? AND toloc = ? AND departure = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setTimestamp(3, Timestamp.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                long flightID = resultSet.getLong("flightid");
                String fromLoc = resultSet.getString("fromloc");
                String toLoc = resultSet.getString("toloc");
                LocalDateTime departure = resultSet.getTimestamp("departure").toLocalDateTime();
                LocalDateTime arrival = resultSet.getTimestamp("arrival").toLocalDateTime();
                int seats = resultSet.getInt("seats");

                Flight flight = new Flight(id, flightID, fromLoc, toLoc, departure, arrival, seats);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    @Override
    public Flight save(Flight flight) {
        String query = "INSERT INTO flights(id, flightid, fromloc, toloc, departure, arrival, seats) values (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, flight.getId());
            statement.setLong(2, flight.getFlightID());
            statement.setString(3, flight.getFrom());
            statement.setString(4, flight.getTo());
            statement.setTimestamp(5, Timestamp.valueOf(flight.getDeparture()));
            statement.setTimestamp(6, Timestamp.valueOf(flight.getArrival()));
            statement.setInt(7, flight.getSeats());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flight;
    }
}
