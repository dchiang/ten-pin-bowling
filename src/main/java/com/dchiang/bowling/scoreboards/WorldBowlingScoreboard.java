package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.WorldBowlingPlayer;

/**
 * Represents the scoreboard of the World Bowling Variant.
 * <p>
 * extends {@link WorldBowlingPlayer}
 */
public class WorldBowlingScoreboard extends TenPinBowlingScoreboard {

    /**
     * This method creates a new WorldBowlingPlayer object by taking playerName
     * and rolls as parameters and using them to create new WorldBowlingPlayer
     * object.
     *
     * @param playerName player name to create the player object.
     * @param rolls      list of roll scores reducing from 10 to 1 frames.
     * @throws FileContentException if error occurs during reading data from file.
     * @return a BowlingPlayer type of WorldBowlingPlayer with playerName and rolls
     *         as parameters.
     */
    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws FileContentException {
        return new WorldBowlingPlayer(playerName, rolls);
    }
}