package com.company.exceptions;

public class TooManyContractsException extends RuntimeException {
    public TooManyContractsException() {
        super("cannot assign another contract to this client.");
    }
}
