package com.musala.drones.exception;

public class DataNotFoundException extends RuntimeException {


    private String message;

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
