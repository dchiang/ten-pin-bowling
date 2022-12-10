package com.dchiang.bowling.exceptions;

public class FileContentException extends Exception {

    public FileContentException(String message) {
        super(message);
    }

    public String toString() {
        return this.getClass().getName() + " " + this.getMessage();
    }
}
