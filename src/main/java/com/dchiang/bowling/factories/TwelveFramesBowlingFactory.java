package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.TwelveFramesBowlingScoreboard;

/**
 * The TwelveFramesBowlingFactory class is an implementation of the
 * {@link GamesFactory} interface that creates a
 * {@link TwelveFramesBowlingScoreboard} object.
 */
public class TwelveFramesBowlingFactory implements GamesFactory {

    /**
     * Creates a TwelveFramesBowlingScoreboard object.
     *
     * @return the Scoreboard object
     */
    @Override
    public Scoreboard createScoreboard() {
        return new TwelveFramesBowlingScoreboard();
    }

}
