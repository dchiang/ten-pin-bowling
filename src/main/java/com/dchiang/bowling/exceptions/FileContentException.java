package com.dchiang.bowling.exceptions;

public class FileContentException extends Exception {

    public FileContentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ " " + this.getMessage();
    }
}
