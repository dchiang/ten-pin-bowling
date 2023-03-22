package com.dchiang.bowling.exceptions;

/**
 * This exception is thrown if an invalid score value is encountered while
 * parsing a score file. It extends the {@link FileContentException} class and
 * includes the line number and score value that caused the exception to be
 * thrown.
 */
public class ScoreValueException extends FileContentException {

    /**
     * Creates a new ScoreValueException with the specified score value and file
     * line number.
     *
     * @param scoreValue the invalid score value that caused the exception
     * @param fileLine   the line number in the file where the invalid score value
     *                   was encountered
     */
    public ScoreValueException(String scoreValue, int fileLine) {
        super(String.format("Invalid score, found %s at line %d", scoreValue, fileLine));
    }
}
