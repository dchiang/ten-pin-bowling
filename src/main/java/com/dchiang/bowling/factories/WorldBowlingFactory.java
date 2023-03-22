package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;
import com.dchiang.bowling.scoreboards.WorldBowlingScoreboard;

/**
 * The WorldBowlingFactory class is an implementation of the
 * {@link GamesFactory} interface that creates a {@link WorldBowlingScoreboard}
 * object.
 */
public class WorldBowlingFactory implements GamesFactory {

    /**
     * Creates a WorldBowlingScoreboard object.
     *
     * @return the Scoreboard object
     */
    @Override
    public Scoreboard createScoreboard() {
        return new WorldBowlingScoreboard();
    }

}
