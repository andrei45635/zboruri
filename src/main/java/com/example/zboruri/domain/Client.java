package com.example.zboruri.domain;

public class Client extends Entity<Integer>{
    private String username;
    private String name;

    public Client(Integer integer, String username, String name) {
        super(integer);
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
