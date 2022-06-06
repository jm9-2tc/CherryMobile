package com.company.exception;

public class ObjectNotSavedException extends RuntimeException {
    public ObjectNotSavedException(String objectName) {
        super("object '" + objectName + "' is not saved in the database.");
    }
}
