package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;

/**
 * Represents a world bowling player who plays a game
 * consisting of 10 frames, with a maximum of 20 rolls.
 * <p>
 * extends {@link VariantBowlingPlayer}
 */
public class WorldBowlingPlayer extends VariantBowlingPlayer {

    private static final int FRAMES_TO_PLAY = 10;
    private static final int MAX_ROLLS = 20;

    /**
     * Creates a WorldBowlingPlayer object with a name and a list of rolls as
     * strings.
     *
     * @param name  the name of the player
     * @param rolls the list of rolls made by the player
     * @throws FileContentException if the content passed in the parameters is not valid
     *                              this exceptions is thrown with a descriptive
     *                              message
     */
    public WorldBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, FRAMES_TO_PLAY, MAX_ROLLS, rolls, true);
    }
}
