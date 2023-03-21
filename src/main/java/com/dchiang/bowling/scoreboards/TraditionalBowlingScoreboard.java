package com.dchiang.bowling.scoreboards;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.player.TraditionalBowlingPlayer;

public class TraditionalBowlingScoreboard extends TenPinBowlingScoreboard {

    @Override
    protected BowlingPlayer createPlayer(String playerName, List<String> rolls) throws FileContentException {
        return new TraditionalBowlingPlayer(playerName, rolls);
    }
}