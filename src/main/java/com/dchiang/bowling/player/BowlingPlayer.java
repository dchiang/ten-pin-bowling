package com.dchiang.bowling.player;

/** An interface for a bownling player */
public interface BowlingPlayer {

    /**
     *
     * @return the name of the player
     */
    public String getName();

    /**
     * Returns the rolls information in the form of a string.
     *
     * @return the rolls information in the form of a string
     */
    public String getRollsString();

    /**
     * Returns the number of frames in the game.
     *
     * @return the number of frames in the game
     */
    public int getFramesNumber();

    /**
     * Returns the scores of the game in the form of a string.
     *
     * @return the scores of the game in the form of a string
     */
    public String getScores();
}
