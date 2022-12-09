package com.dchiang.bowling.player;

import java.util.List;

public class TraditionalBowlingPlayer extends TenPinBowlingPlayer{

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
}
