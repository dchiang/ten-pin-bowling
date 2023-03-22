package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.TraditionalBowlingPlayer;

/**
 * Represents the scoreboard of the Ten-Pin Traditional Bowling.
 * <p>
 * extends {@link TenPinBowlingScoreboard}
 */
public class TraditionalBowlingScoreboard extends TenPinBowlingScoreboard {

    /**
     * This method creates a new TraditionalBowlingPlayer object by taking
     * playerName and rolls as parameters.
     *
     * @param playerName player name to create the player object.
     * @param rolls      list of roll scores reducing from 10 to 1 frames.
     * @throws FileContentException if error occurs during reading data from file.
     * @return a BowlingPlayer type of TraditionalBowlingPlayer with playerName and
     *         rolls as parameters.
     */
    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws FileContentException {
        return new TraditionalBowlingPlayer(playerName, rolls);
    }
}