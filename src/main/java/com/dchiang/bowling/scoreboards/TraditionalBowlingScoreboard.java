package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.TraditionalBowlingPlayer;

public class TraditionalBowlingScoreboard extends TenPinBowlingScoreboard {

    public TraditionalBowlingScoreboard() {
        this.framesNumber = 10;
        this.maxRolls = framesNumber * 2 + 1;
    }

    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws Exception {
        return new TraditionalBowlingPlayer(playerName, rolls);
    }
}