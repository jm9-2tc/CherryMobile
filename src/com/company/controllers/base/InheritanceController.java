package com.company.controllers.base;

import com.company.data.DatabaseManager;

public abstract class InheritanceController<T> extends Controller {
    public InheritanceController(DatabaseManager database) {
        super(database);
    }

    protected abstract int saveBase(T object);
    protected abstract T loadBase(int id);
}
