package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.TwelveFrameBowlingPlayer;

public class TwelveFramesBowlingScoreboard extends TenPinBowlingScoreboard {

    public TwelveFramesBowlingScoreboard() {
        this.framesNumber = 12;
        this.maxRolls = framesNumber * 2;
    }

    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws Exception {
        return new TwelveFrameBowlingPlayer(playerName,rolls);
    }
}