package com.company.controllers.base;

import com.company.data.DatabaseManager;

public abstract class Controller<T> {
    protected final DatabaseManager database;

    protected Controller(DatabaseManager database) {
        this.database = database;
    }

    protected abstract int saveBase(T object);
}
