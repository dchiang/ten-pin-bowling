package com.dchiang.bowling.exceptions;

/**
 * This exception is thrown if a frame is missing while parsing a score file.
 * Inherits from FileContentException. It extends the
 * {@link FileContentException} class and includes the number of frames found in
 * the file when the exception is thrown.
 */
public class MissingFrameException extends FileContentException {

    /**
     * Constructs a MissingFrameException with the given frameIndex.
     *
     * @param frameIndex the number of frames that were found before the missing
     *                   frame.
     */
    public MissingFrameException(int frameIndex) {
        super(String.format("Frames are missing: %d frames found", frameIndex));
    }
}
