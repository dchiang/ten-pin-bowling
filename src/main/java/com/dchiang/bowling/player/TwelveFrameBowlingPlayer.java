package com.dchiang.bowling.player;

import java.util.List;
import java.util.StringJoiner;

import com.dchiang.bowling.utils.Validator;

public class TwelveFrameBowlingPlayer extends TenPinBowlingPlayer {

    public TwelveFrameBowlingPlayer(String name, List<String> rolls) throws Exception {
        super(name, 12, rolls);
    }

    @Override
    protected int spareBonus(int frameIndex) {
        return rolls.get(frameIndex);
    }

    @Override
    protected int strikeBonus(int frameIndex) {
        return 20;
    }

    @Override
    protected String getRollStringRepresentation(List<String> rolls, int score, int frameIndex, int rollIndexInFrame,
            int rollIndex) {
        return rolls.get(rollIndex).equals("F") ? "F"
                : (score == 10 ? "\tX"
                        : (rollIndexInFrame == 2 && (this.rolls.get(rollIndex - 1) + score) == 10 ? "/"
                                : String.valueOf(score)));
    }

    @Override
    public String getScores() {
        int frameIndex = 0;
        StringJoiner results = new StringJoiner("\t\t");
        results.add("Score");
        for (int frame = 0; frame < this.framesNumber; frame++) {
            int score = 0;
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
            results.add(String.valueOf(score));
        }
        return results.toString();
    }

    @Override
    protected void processRolls(List<String> rolls) throws Exception {
        int rollIndex = 0;
        int frameIndex = 1;
        for (int i = 0; i < rolls.size(); i++) {
            rollIndex++;
            if (rollIndex == 3) {
                throw new Exception("Extra score");
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
                        throw new Exception("Invalid frame sum, total: " + frameSum);
                    }
                    if (frameIndex < this.framesNumber) {
                        rollIndex = 0;
                        frameIndex++;
                    }
                }
            } else {
                throw new Exception("Invalid score");
            }
        }
        if (frameIndex < this.framesNumber) {
            throw new Exception("Frames are missing, only found "+(frameIndex-1));
        }
    }
}
