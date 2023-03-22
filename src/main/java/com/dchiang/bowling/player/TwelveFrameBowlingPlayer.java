package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;

/**
 * Represents a twelve frames bowling player with a maximum of 24 rolls.
 * <p>
 * extends {@link VariantBowlingPlayer}
 */
public class TwelveFrameBowlingPlayer extends VariantBowlingPlayer {

    /**
     * The number of frames to play in a game for a 12-frame bowling player.
     */
    private static final int FRAMES_TO_PLAY = 12;

    /**
     * The maximum number of rolls a 12-frame bowling player can make in a game.
     */
    private static final int MAX_ROLLS = 24;

    /**
     * Creates a TwelveFrameBowlingPlayer object with a name and a list of rolls as
     * strings.
     *
     * @param name  the name of the player
     * @param rolls the list of rolls made by the player
     * @throws FileContentException if the content passed in parameters is not valid
     *                              this exceptions is thrown with a descriptive
     *                              message
     */
    public TwelveFrameBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, FRAMES_TO_PLAY, MAX_ROLLS, rolls, false);
    }
}
