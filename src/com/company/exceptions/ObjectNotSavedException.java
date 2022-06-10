package com.company.exceptions;

public class ObjectNotSavedException extends RuntimeException {
    public ObjectNotSavedException(String objectName) {
        super("object '" + objectName + "' is not saved in the database.");
    }
}
