package com.example.zboruri.repository.db;

import com.example.zboruri.domain.Client;
import com.example.zboruri.repository.IRepository;
import com.example.zboruri.utils.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientRepoDB implements IRepository<Integer, Client> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int clientID = resultSet.getInt("clientid");
                String username = resultSet.getString("username");
                String name = resultSet.getString("clientname");

                Client client = new Client(clientID, username, name);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client save(Client client) {
        String query = "INSERT INTO clients(clientid, username, clientname) VALUES (?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, client.getId());
            statement.setString(2, client.getUsername());
            statement.setString(3, client.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public boolean findClient(String username){
        String query = "SELECT EXISTS (SELECT 1 FROM clients WHERE username = ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() && Objects.equals(resultSet.getString(1), "t")){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
