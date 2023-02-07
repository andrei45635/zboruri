package com.example.zboruri.repository;

import com.example.zboruri.domain.Entity;

import java.util.List;

public interface IRepository<ID, E extends Entity<Integer>>{
    List<E> findAll();
    E save (E e);
}
