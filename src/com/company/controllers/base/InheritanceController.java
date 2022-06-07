package com.company.controllers.base;

import com.company.data.DatabaseManager;

public abstract class InheritanceController<T> {
    protected final DatabaseManager database;

    public InheritanceController(DatabaseManager database) {
        this.database = database;
    }

    protected abstract int saveBase(T object);
    protected abstract T loadBase(int id);
}
