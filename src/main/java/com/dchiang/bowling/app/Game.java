package com.dchiang.bowling.app;

import com.dchiang.bowling.factories.GamesFactory;
import com.dchiang.bowling.scoreboards.Scoreboard;

public class Game {

    private Scoreboard scoreboard;

    public Game(GamesFactory factory) {
        this.scoreboard = factory.createScoreboard();
    }

    public void execute(String scoreFile) throws Exception {
        this.scoreboard.execute(scoreFile);
    }
}
