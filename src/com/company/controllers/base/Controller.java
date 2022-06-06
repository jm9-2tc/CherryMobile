package com.company.controllers.base;

import com.company.data.DatabaseManager;

public abstract class Controller<T> {
    protected final DatabaseManager database;

    public Controller(DatabaseManager database) {
        this.database = database;
    }

    public abstract T load(int id);
    public abstract void save(T object);
}
