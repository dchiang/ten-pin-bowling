package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;

public class WorldBowlingPlayer extends VariantBowlingPlayer {

    public WorldBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, 10, rolls, true);
    }
}
