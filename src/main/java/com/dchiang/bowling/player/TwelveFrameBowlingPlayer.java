package com.dchiang.bowling.player;

import java.util.List;

import com.dchiang.bowling.exceptions.FileContentException;

public class TwelveFrameBowlingPlayer extends VariantBowlingPlayer {

    private static final int FRAMES_TO_PLAY = 12;
    private static final int MAX_ROLLS = 24;

    public TwelveFrameBowlingPlayer(String name, List<String> rolls) throws FileContentException {
        super(name, FRAMES_TO_PLAY, MAX_ROLLS, rolls, false);
    }
}
