package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.TraditionalBowlingScoreboard;

/**
 * The TenPinsTraditionalBowlingFactory class is an implementation of the
 * {@link GamesFactory} interface that creates a
 * {@link TraditionalBowlingScoreboard} object.
 */
public class TenPinsTraditionalBowlingFactory implements GamesFactory {

    /**
     * Creates a TraditionalBowlingScoreboard object.
     *
     * @return the Scoreboard object
     */
    @Override
    public Scoreboard createScoreboard() {
        return new TraditionalBowlingScoreboard();
    }

}
