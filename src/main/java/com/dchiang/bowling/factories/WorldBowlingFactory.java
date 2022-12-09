package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.WorldBowlingScoreboard;

public class WorldBowlingFactory implements GamesFactory {

    @Override
    public Scoreboard createScoreboard() {
        return new WorldBowlingScoreboard();
    }

}
