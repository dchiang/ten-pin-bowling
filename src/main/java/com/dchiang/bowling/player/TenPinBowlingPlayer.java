package com.dchiang.bowling.player;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.exceptions.InvalidFrameException;
import com.dchiang.bowling.exceptions.MissingFrameException;
import com.dchiang.bowling.utils.Validator;

public abstract class TenPinBowlingPlayer implements BowlingPlayer {
    protected String name;
    protected int maxRolls;
    protected List<Integer> rolls;
    protected StringJoiner rollsString;
    protected int framesNumber;
    protected boolean accumulateScoreAmongFrames;

    protected TenPinBowlingPlayer(String name, int frameNumbers, int maxRolls, List<String> rolls,
            boolean accumulateScoreAmongFrames)
            throws FileContentException {
        validatePlayerName(name);
        this.name = name;
        this.framesNumber = frameNumbers;
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

    protected abstract int spareBonus(int frameIndex);

    protected abstract int strikeBonus(int frameIndex);

    protected abstract void processRolls(List<String> rolls) throws FileContentException;

    public String getName() {
        return this.name;
    }

    public String getRollsString() {
        return rollsString.toString();
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

    protected void validateFrameSum(int frameIndex, int iteration) throws InvalidFrameException {
        int frameSum = this.sumOfBallsInFrame(iteration);
        if (frameSum > 10 && frameIndex < this.framesNumber) {
            throw new InvalidFrameException(frameIndex, frameSum);
        }
    }

    protected void validateMissingFrames(int frameIndex) throws MissingFrameException {
        if (frameIndex < this.framesNumber) {
            throw new MissingFrameException(frameIndex - 1);
        }
    }

    protected boolean goToNextFrame(int frameIndex, int rollIndex, int score) {
        return ((rollIndex == 1 && score == 10) || rollIndex == 2) && frameIndex < this.framesNumber;
    }

    protected int[] getFrameRoll(int frameIndex, int rollIndex, int score) {
        if (goToNextFrame(frameIndex, rollIndex, score)) {
            return new int[] { frameIndex + 1, 0 };
        }
        return new int[] { frameIndex, rollIndex };
    }

    public String getScores() {
        int score = 0;
        int frameIndex = 0;
        StringJoiner results = new StringJoiner("\t\t");
        results.add("Score");
        for (int frame = 0; frame < this.framesNumber; frame++) {
            score = this.accumulateScoreAmongFrames ? score : 0;
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
