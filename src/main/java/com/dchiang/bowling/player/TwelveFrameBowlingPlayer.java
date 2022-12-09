package com.dchiang.bowling.player;

import java.util.List;
import java.util.StringJoiner;

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
}
