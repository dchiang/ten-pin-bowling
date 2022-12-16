package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.exceptions.ScoreValueException;
import com.dchiang.bowling.utils.Validator;

public class TraditionalBowlingPlayer extends TenPinBowlingPlayer {

    public TraditionalBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, 10, rolls, true);
    }

    @Override
    protected int spareBonus(int frameIndex) {
        return rolls.get(frameIndex + 2);
    }

    @Override
    protected int strikeBonus(int frameIndex) {
        return rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
    }

    private String getRollStringRepresentation(List<String> rolls, int score, int frameIndex, int rollIndexInFrame,
            int rollIndex) {
        String representation = rolls.get(rollIndex);
        if (representation.equals("F")) {
            return representation;
        }
        if (score == 10 && frameIndex >= this.framesNumber) {
            return "X";
        }
        if (score == 10) {
            return "\tX";
        }
        if (rollIndexInFrame == 2 && (this.rolls.get(rollIndex - 1) + score) == 10) {
            return "/";
        }
        return representation;
    }

    private void validateExtraScore(int rollIndex, int iteration) throws ExtraScoreException {
        if ((rollIndex == 3 && !this.isStrike(iteration) && !this.isSpare(iteration)) || rollIndex > 3) {
            throw new ExtraScoreException();
        }
    }

    @Override
    protected void processRolls(List<String> rolls) throws FileContentException {
        int rollIndex = 0;
        int frameIndex = 1;
        for (int i = 0; i < rolls.size(); i++) {
            rollIndex++;
            validateExtraScore(rollIndex, i - 2);
            if (Validator.hasValidFormat(rolls.get(i), "^([0-9]|10|F){1}$")) {
                Integer score = rolls.get(i).equals("F") ? 0 : Integer.valueOf(rolls.get(i));
                rollsString.add(this.getRollStringRepresentation(rolls, score, frameIndex, rollIndex, i));
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
