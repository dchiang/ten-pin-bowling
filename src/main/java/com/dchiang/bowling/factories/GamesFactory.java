package com.dchiang.bowling.factories;

import com.dchiang.bowling.scoreboards.Scoreboard;

/**
 * The GamesFactory interface defines a method for creating a Scoreboard object.
 * Any class that implements this interface must define this method.
 */
public interface GamesFactory {

    /**
     * Creates a Scoreboard object of the appropriate type.
     *
     * @return the Scoreboard object
     */
    public Scoreboard createScoreboard();
}
