package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.TwelveFramesBowlingScoreboard;

public class TwelveFramesBowlingFactory implements GamesFactory {

    @Override
    public Scoreboard createScoreboard() {
        return new TwelveFramesBowlingScoreboard();
    }

}
