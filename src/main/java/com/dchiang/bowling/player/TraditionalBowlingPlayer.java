package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.InvalidFrameException;
import com.dchiang.bowling.exceptions.MissingFrameException;
import com.dchiang.bowling.exceptions.ScoreValueException;
import com.dchiang.bowling.utils.Validator;

public class TraditionalBowlingPlayer extends TenPinBowlingPlayer {

    public TraditionalBowlingPlayer(String name, List<String> rolls) throws Exception {
        super(name, 10, rolls);
    }

    @Override
    protected int spareBonus(int frameIndex) {
        return rolls.get(frameIndex + 2);
    }

    @Override
    protected int strikeBonus(int frameIndex) {
        return rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
    }

    @Override
    protected void processRolls(List<String> rolls) throws Exception {
        int rollIndex = 0;
        int frameIndex = 1;
        for (int i = 0; i < rolls.size(); i++) {
            rollIndex++;
            if (rollIndex == 3 && !this.isStrike(i - 2) && !this.isSpare(i - 2)) {
                throw new ExtraScoreException();
            } else if (rollIndex > 3) {
                throw new ExtraScoreException();
            }
            if (Validator.hasValidFormat(rolls.get(i), "^([0-9]|10|F){1}$")) {
                Integer score = rolls.get(i).equals("F") ? 0 : Integer.valueOf(rolls.get(i));
                rollsString.add(this.getRollStringRepresentation(rolls, score, frameIndex, rollIndex, i));
                this.rolls.add(score);
                if (rollIndex == 1 && score == 10 && frameIndex < this.framesNumber) {
                    rollIndex = 0;
                    frameIndex++;
                } else if (rollIndex == 2) {
                    int frameSum = this.sumOfBallsInFrame(i - 1);
                    if (frameSum > 10 && frameIndex < this.framesNumber) {
                        throw new InvalidFrameException(frameIndex, frameSum);
                    }
                    if (frameIndex < this.framesNumber) {
                        rollIndex = 0;
                        frameIndex++;
                    }
                }
            } else {
                throw new ScoreValueException(rolls.get(i), (i + 1));
            }
        }
        if (frameIndex < this.framesNumber) {
            throw new MissingFrameException(frameIndex - 1);
        }
    }
}
