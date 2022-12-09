package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.WorldBowlingPlayer;

public class WorldBowlingScoreboard extends TenPinBowlingScoreboard {

    public WorldBowlingScoreboard() {
        this.framesNumber = 10;
        this.maxRolls = framesNumber * 2;
    }

    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws Exception {
        return new WorldBowlingPlayer(playerName, rolls);
    }
}