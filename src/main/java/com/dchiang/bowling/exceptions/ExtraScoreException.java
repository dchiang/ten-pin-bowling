package com.dchiang.bowling.exceptions;

/**
 * Represents an exception that is thrown if the number of rolls or frames
 * contained in the score file by player, exceeds the limit specified by the
 * rules of the particular type of bowling game.
 * <p>
 * extends {@link FileContentException}
 */
public class ExtraScoreException extends FileContentException {

    /**
     * Constructs a ExtraScoreException with a "Extra Score" message.
     */
    public ExtraScoreException() {
        super("Extra Score");
    }

}
