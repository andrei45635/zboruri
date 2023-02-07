package com.example.zboruri.utils.observer;

import com.example.zboruri.utils.event.Event;

public interface Observer<E extends Event> {
    void update (E e);
}
