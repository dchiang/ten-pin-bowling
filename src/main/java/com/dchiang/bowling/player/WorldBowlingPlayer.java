package com.dchiang.bowling.player;

import java.util.List;

public class WorldBowlingPlayer extends TenPinBowlingPlayer{

    public WorldBowlingPlayer(String name, List<String> rolls) throws Exception {
        super(name, 10, rolls);
        this.framesNumber = 10;
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
}
