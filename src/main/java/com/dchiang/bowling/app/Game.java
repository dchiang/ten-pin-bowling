package com.dchiang.bowling.app;

import java.io.IOException;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.factories.GamesFactory;
import com.dchiang.bowling.scoreboards.Scoreboard;

/**
 * Represents a game of bowling, which is responsible for creating a scoreboard
 * and executing the score file.
 */
public class Game {

    private Scoreboard scoreboard;

    /**
     * Constructs a new Game object with the specified GamesFactory object.
     *
     * @param factory - the GamesFactory object used to create the scoreboard
     */
    public Game(GamesFactory factory) {
        this.scoreboard = factory.createScoreboard();
    }

    /**
     * Executes the game from a score file using the created scoreboard.
     *
     * @param scoreFile the path to the file containing the scores
     * @throws IOException          if an I/O error occurs while reading the file
     * @throws FileContentException if the contents of the file is invalid
     */
    public void execute(String scoreFile) throws IOException, FileContentException {
        this.scoreboard.execute(scoreFile);
    }
}
