package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;

public class TwelveFrameBowlingPlayer extends VariantBowlingPlayer {

    public TwelveFrameBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, 12, 24, rolls, false);
    }
}
