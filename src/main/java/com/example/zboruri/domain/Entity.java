package com.example.zboruri.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private final long serialVersionUID = 327845221L;
    protected ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
}
