package com.dchiang.bowling.exceptions;

/**
 * This exception is thrown if the sum of the scores in a frame of a bowling
 * game is invalid. It is a subclass of {@link FileContentException}.
 */
public class InvalidFrameException extends FileContentException {

    /**
     * Constructs an InvalidFrameException object with the given frame index and
     * frame sum.
     *
     * @param frameIndex the index of the frame where the sum is invalid.
     * @param frameSum   the total sum of the scores in the invalid frame.
     */
    public InvalidFrameException(int frameIndex, int frameSum) {
        super(String.format("Invalid frame sum at frame %d, total: %d", frameIndex, frameSum));
    }
}
