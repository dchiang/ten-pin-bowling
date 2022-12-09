package com.dchiang.bowling.player;

import java.util.*;

import com.dchiang.bowling.utils.Validator;

public abstract class TenPinBowlingPlayer implements BowlingPlayer {
    protected String name;
    protected List<Integer> rolls;
    protected StringJoiner rollsString;
    protected int framesNumber;

    protected TenPinBowlingPlayer(String name, int frameNumbers, List<String> rolls) throws Exception {
        this.name = name;
        this.rolls = new ArrayList<>();
        this.framesNumber = frameNumbers;
        rollsString = new StringJoiner("\t");
        rollsString.add("Pinfalls");
        this.processRolls(rolls);
    }

    protected abstract int spareBonus(int frameIndex);

    protected abstract int strikeBonus(int frameIndex);

    public String getName() {
        return this.name;
    }

    public String getRollsString() {
        return rollsString.toString();
    }

    protected String getRollStringRepresentation(List<String> rolls, int score, int frameIndex, int rollIndexInFrame, int rollIndex){
        return rolls.get(rollIndex).equals("F") ? "F"
        : (score == 10 && frameIndex >= this.framesNumber ? "X"
                : (score == 10 ? "\tX"
                        : (rollIndexInFrame == 2 && (this.rolls.get(rollIndex - 1) + score) == 10 ? "/"
                                : String.valueOf(score))));
    }

    protected void processRolls(List<String> rolls) throws Exception {
        int roll = 0;
        int frame = 1;
        for (int i = 0; i < rolls.size(); i++) {
            roll++;
            if (Validator.hasValidFormat(rolls.get(i), "^([0-9]|10|F){1}$")) {
                Integer score = rolls.get(i).equals("F") ? 0 : Integer.valueOf(rolls.get(i));
                rollsString.add(this.getRollStringRepresentation(rolls,score,frame,roll,i));
                this.rolls.add(score);
                if (roll == 1 && score == 10) {
                    roll = 0;
                    frame++;
                } else if (roll == 2) {
                    int frameSum = this.sumOfBallsInFrame(i - 1);
                    if (frameSum > 10 && frame < this.framesNumber) {
                        throw new Exception("Invalid frame sum, total: " + frameSum);
                    }
                    roll = 0;
                    frame++;
                }
            } else {
                throw new Exception("Invalid score");
            }
        }
    }

    protected boolean isStrike(int frameIndex) {
        return rolls.get(frameIndex) == 10;
    }

    protected int sumOfBallsInFrame(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1);
    }

    protected boolean isSpare(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1) == 10;
    }

    public String getScores() {
        int score = 0;
        int frameIndex = 0;
        StringJoiner results = new StringJoiner("\t\t");
        results.add("Score");
        for (int frame = 0; frame < this.framesNumber; frame++) {
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
}
