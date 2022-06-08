package com.company.exception;

public class TooManyContractsException extends RuntimeException {
    public TooManyContractsException() {
        super("cannot assign another contract to this client.");
    }
}
