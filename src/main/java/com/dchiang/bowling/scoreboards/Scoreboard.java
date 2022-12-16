package com.dchiang.bowling.scoreboards;

import java.io.IOException;

import com.dchiang.bowling.exceptions.FileContentException;

public interface Scoreboard {

    public void execute(String scoreFile) throws IOException, FileContentException ;

}
