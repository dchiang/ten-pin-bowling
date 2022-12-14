package com.dchiang.bowling.exceptions;

public class InvalidFrameException extends FileContentException{
    public InvalidFrameException(int frameIndex, int frameSum) {
        super(String.format("Invalid frame sum at frame %d, total: %d", frameIndex,frameSum));
    }
}
