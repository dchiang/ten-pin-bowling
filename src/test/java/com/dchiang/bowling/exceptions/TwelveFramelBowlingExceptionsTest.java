package com.dchiang.bowling.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;

import com.dchiang.bowling.App;
import com.dchiang.bowling.AppTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TwelveFramelBowlingExceptionsTest {

    private String gameSelection = "2";

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    @Parameters({
            "/negative/extra-score.txt,ExtraScoreException Extra Score",
            "/negative/all0-extra-score.txt,ExtraScoreException Extra Score",
            "/negative/allF-extra-score.txt,ExtraScoreException Extra Score",
            "/negative/empty.txt,FileContentException Empty score file",
            "/negative/free-text.txt,FileContentException Invalid score file, missing column",
            "/negative/invalid-score.txt,ScoreValueException Invalid score, found lorem at line 2",
            "/negative/missing-rolls.txt,MissingFrameException Frames are missing: 8 frames found",
            "/negative/negative.txt,ScoreValueException Invalid score, found -5 at line 2",
            "/negative/invalid-frame.txt,InvalidFrameException Invalid frame sum at frame 2, total: 11",
            "/negative/invalid-player-name.txt,FileContentException Invalid player name, found @Carl"
    })
    public void testExceptions(String scoreFile, String... expectedOutput) {
        App.main(new String[] { gameSelection, AppTest.testResourcesPath + scoreFile });
        assertEquals(String.join(", ",expectedOutput) + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
    }
}
