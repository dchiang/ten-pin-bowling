package com.dchiang.bowling.player;

import java.util.*;

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

    protected abstract void processRolls(List<String> rolls) throws Exception;

    public String getName() {
        return this.name;
    }

    public String getRollsString() {
        return rollsString.toString();
    }

    protected String getRollStringRepresentation(List<String> rolls, int score, int frameIndex, int rollIndexInFrame,
            int rollIndex) {
        return rolls.get(rollIndex).equals("F") ? "F"
                : (score == 10 && frameIndex >= this.framesNumber ? "X"
                        : (score == 10 ? "\tX"
                                : (rollIndexInFrame == 2 && (this.rolls.get(rollIndex - 1) + score) == 10 ? "/"
                                        : String.valueOf(score))));
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
