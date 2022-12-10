package com.dchiang.bowling.exceptions;

public class MissingFrameException extends FileContentException {

    public MissingFrameException(int frameIndex) {
        super(String.format("Frames are missing: %d frames found",frameIndex));
    }
}
