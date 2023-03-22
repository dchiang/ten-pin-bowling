package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.exceptions.ScoreValueException;
import com.dchiang.bowling.utils.Validator;

/**
 * Represents a player playing variants of ten-pin bowling with a fixed strike
 * bonus of 20 pins.
 * <p>
 * extends {@link TenPinBowlingPlayer}
 */
public class VariantBowlingPlayer extends TenPinBowlingPlayer {

    private static final int STRIKE_BONUS = 20;

    /**
     * Constructs a VariantBowlingPlayer by invoking the super constructor with the
     * given parameters
     *
     * @param name                       the name of the player
     * @param framesNumber               the number of frames in the game
     * @param maxRolls                   the maximum number of rolls allowed for the
     *                                   player
     * @param rolls                      the rolls of the player
     * @param accumulateScoreAmongFrames indicates whether or not the score should
     *                                   be accumulated among frames
     * @throws FileContentException if the content passed in the parameters is not
     *                              valid this exceptions is thrown with a
     *                              descriptive message
     */
    protected VariantBowlingPlayer(String name, int framesNumber, int maxRolls, List<String> rolls,
            boolean accumulateScoreAmongFrames) throws FileContentException {
        super(name, framesNumber, maxRolls, rolls, accumulateScoreAmongFrames);
    }

    /**
     * Calculates the bonus score for a spare in the given frame.
     * <p>
     * For variant bowling, the bonus score for a spare is the same
     * as the value of the first roll in the following frame.
     *
     * @param frameIndex the index of the frame where the spare occurred
     * @return the bonus score for the spare
     */
    @Override
    protected int spareBonus(int frameIndex) {
        return rolls.get(frameIndex);
    }

    /**
     * Calculates the bonus score in case of a strike in the given frame.
     *
     * @param frameIndex the index of the frame where the strike occurred
     * @return the bonus score for the strike
     */
    @Override
    protected int strikeBonus(int frameIndex) {
        return STRIKE_BONUS;
    }

    private String getRollStringRepresentation(List<String> rolls, int score, int rollIndexInFrame,
            int rollIndex) {
        String representation = rolls.get(rollIndex);
        if (representation.equals("F")) {
            return representation;
        }
        if (score == TenPinBowlingPlayer.TOTAL_PINS) {
            return "\tX";
        }
        if (rollIndexInFrame == 2 && (this.rolls.get(rollIndex - 1) + score) == TenPinBowlingPlayer.TOTAL_PINS) {
            return "/";
        }
        return representation;
    }

    private void validateExtraScore(int rollIndex, int frameIndex, int iteration) throws ExtraScoreException {
        if (rollIndex == 2 && frameIndex == this.framesNumber && this.isStrike(iteration)) {
            throw new ExtraScoreException();
        }
    }

    /**
     * Processes the list of rolls performed by the player and updates the player's score.
     *
     * @param rolls the list of all rolls made by the player
     * @throws FileContentException if there is an error with the file content
     */
    @Override
    protected void processRolls(List<String> rolls) throws FileContentException {
        int rollIndex = 0;
        int frameIndex = 1;
        for (int i = 0; i < rolls.size(); i++) {
            rollIndex++;
            validateExtraScore(rollIndex, frameIndex, i - 1);
            if (Validator.hasValidFormat(rolls.get(i), "^([0-9]|10|F){1}$")) {
                Integer score = rolls.get(i).equals("F") ? 0 : Integer.valueOf(rolls.get(i));
                rollsString.add(this.getRollStringRepresentation(rolls, score, rollIndex, i));
                this.rolls.add(score);
                if (rollIndex == 2) {
                    this.validateFrameSum(frameIndex, i - 1);
                }
                int[] frameRoll = getFrameRoll(frameIndex, rollIndex, score);
                frameIndex = frameRoll[0];
                rollIndex = frameRoll[1];
            } else {
                throw new ScoreValueException(rolls.get(i), (i + 1));
            }
        }
        this.validateMissingFrames(frameIndex);
    }
}
