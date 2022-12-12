package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.TraditionalBowlingScoreboard;

public class TenPinsTraditionalBowlingFactory implements GamesFactory {

    @Override
    public Scoreboard createScoreboard() {
        return new TraditionalBowlingScoreboard();
    }

}
