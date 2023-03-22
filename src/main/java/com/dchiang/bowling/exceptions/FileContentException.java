package com.dchiang.bowling.exceptions;

/**
 * Grepresents an exception that is thrown if the content of a file does not
 * meet the expected format or requirements.
 * <p>
 * extends {@link Exception}
 */
public class FileContentException extends Exception {

    /**
     * Constructs a FileContentException with a specific message.
     *
     * @param message the message of the exception to be thrown
     */
    public FileContentException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this object. The string representation
     * consists of the simple name of the class plus the detail message of the
     * exception.
     *
     * @return a string representation of this exception
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getMessage();
    }
}
