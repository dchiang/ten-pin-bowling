package com.dchiang.bowling.player;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.exceptions.InvalidFrameException;
import com.dchiang.bowling.exceptions.MissingFrameException;
import com.dchiang.bowling.utils.Validator;

/**
 * Abstract class for a ten-pin bowling player.
 * <p>
 * extends {@link BowlingPlayer}
 */
public abstract class TenPinBowlingPlayer implements BowlingPlayer {

    /**
     * Player name.
     */
    protected String name;

    /**
     * Maximum allowed rolls for the player.
     */
    protected int maxRolls;

    /**
     * List of rolls made by the player.
     */
    protected List<Integer> rolls;

    /**
     * StringJoiner containing string representation of rolls made by the player.
     */
    protected StringJoiner rollsString;

    /**
     * Number of frames in the game.
     */
    protected int framesNumber;

    /**
     * Flag indicating whether to accumulate score among frames or not.
     */
    protected boolean accumulateScoreAmongFrames;

    /**
     * Constant representing the total number of pins in a frame.
     */
    static final int TOTAL_PINS = 10;

    /**
     * Sole constructor. (For invocation by subclass constructors).
     *
     * @param name                       name of the player
     * @param framesNumber               number of frames in the game
     * @param maxRolls                   maximum rolls that the player can perform
     * @param rolls                      a list of strings containing the rolls
     *                                   performed by the player
     * @param accumulateScoreAmongFrames flag indicating whether to accumulate score
     *                                   among frames or not
     * @throws FileContentException if the content passed in parameters is not valid
     *                              this exceptions is thrown with a descriptive
     *                              message
     */
    protected TenPinBowlingPlayer(String name, int framesNumber, int maxRolls, List<String> rolls,
            boolean accumulateScoreAmongFrames)
            throws FileContentException {
        validatePlayerName(name);
        this.name = name;
        this.framesNumber = framesNumber;
        this.maxRolls = maxRolls;
        validateRollsAmount(rolls);
        this.rolls = new ArrayList<>();
        rollsString = new StringJoiner("\t");
        rollsString.add("Pinfalls");
        this.accumulateScoreAmongFrames = accumulateScoreAmongFrames;
        this.processRolls(rolls);
    }

    private void validatePlayerName(String playerName) throws FileContentException {
        if (!Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$")) {
            throw new FileContentException("Invalid player name, found " + playerName);
        }
    }

    private void validateRollsAmount(List<String> rolls) throws ExtraScoreException {
        if (rolls.size() > this.maxRolls) {
            throw new ExtraScoreException();
        }
    }

    /**
     * Calculates the bonus score for a spare in the given frame.
     *
     * @param frameIndex Index of the current frame
     * @return an int value with the bonus score of the current frame
     */
    protected abstract int spareBonus(int frameIndex);

    /**
     * Calculates the bonus score in case of a strike in the given frame.
     *
     * @param frameIndex Index of the current frame.
     * @return Bonus score of the current frame.
     */
    protected abstract int strikeBonus(int frameIndex);

    /**
     * Processes the list of rolls performed by the player and updates the player's
     * score.
     *
     * @param rolls the list of all rolls made by the player
     * @throws FileContentException if there is an error in the file content
     */
    protected abstract void processRolls(List<String> rolls) throws FileContentException;

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the rolls information in the form of a string.
     *
     * @return the rolls information in the form of a string
     */
    public String getRollsString() {
        return rollsString.toString();
    }

    /**
     * Returns the number of frames in the game.
     *
     * @return the number of frames in the game
     */
    public int getFramesNumber() {
        return this.framesNumber;
    }

    /**
     * Checks if the current frame is a strike.
     *
     * @param frameIndex the index of the current frame.
     * @return true if the current frame is a strike, false otherwise.
     */
    protected boolean isStrike(int frameIndex) {
        return rolls.get(frameIndex) == TOTAL_PINS;
    }

    /**
     * Returns the sum of the balls in the current frame.
     *
     * @param frameIndex the index of the current frame
     * @return the sum of the balls in the current frame
     */
    protected int sumOfBallsInFrame(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1);
    }

    /**
     * Checks if the current frame is a spare.
     *
     * @param frameIndex the index of the current frame
     * @return true if the current frame is a spare, false otherwise
     */
    protected boolean isSpare(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1) == TOTAL_PINS;
    }

    /**
     * Validates if the sum of the balls in the current frame exceeds the maximum
     * allowed value.
     *
     * @param frameIndex the index of the current frame
     * @param iteration  the index of the current iteration
     *
     * @throws InvalidFrameException if the sum of the balls in the current frame
     *                               exceeds the maximum allowed value
     */
    protected void validateFrameSum(int frameIndex, int iteration) throws InvalidFrameException {
        int frameSum = this.sumOfBallsInFrame(iteration);
        if (frameSum > TOTAL_PINS && frameIndex < this.framesNumber) {
            throw new InvalidFrameException(frameIndex, frameSum);
        }
    }

    /**
     * Validates if there are any missing frames.
     *
     * @param frameIndex index of the current frame
     * @throws MissingFrameException if there are any missing frames
     */
    protected void validateMissingFrames(int frameIndex) throws MissingFrameException {
        if (frameIndex < this.framesNumber) {
            throw new MissingFrameException(frameIndex - 1);
        }
    }

    /**
     * Returns true if it is time to move to the next frame, false otherwise.
     *
     * @param frameIndex the index of the current frame
     * @param rollIndex  the index of the current roll
     * @param score      the score of the current roll
     *
     * @return true if it is time to move to the next frame, false otherwise
     */
    protected boolean goToNextFrame(int frameIndex, int rollIndex, int score) {
        return ((rollIndex == 1 && score == TOTAL_PINS) || rollIndex == 2) && frameIndex < this.framesNumber;
    }

    /**
     * Returns an array with the roll indexes for the current frame.
     *
     * @param frameIndex the index of the current frame
     * @param rollIndex  the index of the current roll
     * @param score      the score of the current roll
     *
     * @return an array with the roll indexes for the current frame
     */
    protected int[] getFrameRoll(int frameIndex, int rollIndex, int score) {
        if (goToNextFrame(frameIndex, rollIndex, score)) {
            return new int[] { frameIndex + 1, 0 };
        }
        return new int[] { frameIndex, rollIndex };
    }

    /**
     * Returns the scores of the game in the form of a string.
     *
     * @return the scores of the game in the form of a string
     */
    public String getScores() {
        int score = 0;
        int frameIndex = 0;
        StringJoiner results = new StringJoiner("\t\t");
        results.add("Score");
        for (int frame = 0; frame < this.framesNumber; frame++) {
            score = this.accumulateScoreAmongFrames ? score : 0;
            if (isStrike(frameIndex)) {
                score += TOTAL_PINS + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += TOTAL_PINS + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
            results.add(String.valueOf(score));
        }
        return results.toString();
    }
}
