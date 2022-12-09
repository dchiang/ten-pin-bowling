package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;

public interface GamesFactory {
    public Scoreboard createScoreboard();
}
