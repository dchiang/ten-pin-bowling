package com.dchiang.bowling.exceptions;

public class ScoreValueException extends FileContentException {

    public ScoreValueException(String scoreValue, int fileLine) {
        super(String.format("Invalid score, found %s at line %d",scoreValue,fileLine));
    }
}
