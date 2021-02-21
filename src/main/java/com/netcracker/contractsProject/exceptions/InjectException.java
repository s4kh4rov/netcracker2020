package com.netcracker.contractsProject.exceptions;

public class InjectException extends Exception {
    public InjectException(Exception e) {
        super(e);
    }
    public InjectException(String message) {
        super(message);
    }
}
