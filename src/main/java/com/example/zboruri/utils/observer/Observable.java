package com.example.zboruri.utils.observer;

import com.example.zboruri.utils.event.Event;

import java.util.List;

public interface Observable<E extends Event>{
    void addObserver(Observer<E> obs);
    void notifyObservers(E e);
}
