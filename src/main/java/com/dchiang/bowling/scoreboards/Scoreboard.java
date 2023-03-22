package com.dchiang.bowling.scoreboards;

import java.io.IOException;

import com.dchiang.bowling.exceptions.FileContentException;

/**
 * The Scoreboard interface defines a method for creating a new type of
 * scoreboard. Any class that implements this interface must define this method.
 */
public interface Scoreboard {

    /**
     * Executes the scoreboard logic for a given score file.
     *
     * @param scoreFile a String indicating the file path to the score file to be
     *                  processed
     * @throws IOException          if there is an error with the I/O of the file
     * @throws FileContentException if the file content is malformed or invalid
     */
    public void execute(String scoreFile) throws IOException, FileContentException;

}
