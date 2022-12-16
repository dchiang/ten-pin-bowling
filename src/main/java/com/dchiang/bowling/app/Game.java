package com.dchiang.bowling.app;

import java.io.IOException;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.factories.GamesFactory;
import com.dchiang.bowling.scoreboards.Scoreboard;

public class Game {

    private Scoreboard scoreboard;

    public Game(GamesFactory factory) {
        this.scoreboard = factory.createScoreboard();
    }

    public void execute(String scoreFile) throws IOException, FileContentException {
        this.scoreboard.execute(scoreFile);
    }
}
