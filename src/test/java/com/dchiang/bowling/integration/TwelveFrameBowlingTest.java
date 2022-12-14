package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;
import com.dchiang.bowling.AppTest;

public class TwelveFrameBowlingTest {

    private final String gameSelection = "2";

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private void testAppMain(String scoreFile, String scoreboard) {
        String expectedOutput = AppTest.twelveFrames + scoreboard;
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    private void testAppMainInteractiveInput(String scoreFile, String scoreboard) {
        String expectedOutput = AppTest.gameMenu + AppTest.fileAsk + AppTest.twelveFrames + scoreboard;
        systemInMock.provideLines(gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void twoPlayersTwelveFrameBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores-12.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\t8\t1\t\tX\n"
                + "Score\t\t30\t\t17\t\t9\t\t30\t\t8\t\t18\t\t6\t\t30\t\t30\t\t30\t\t9\t\t30\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t\tX\t9\t0\t8\t1\n"
                + "Score\t\t13\t\t9\t\t30\t\t9\t\t30\t\t30\t\t9\t\t17\t\t8\t\t30\t\t9\t\t9\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void perfectTwelveFrameBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\n";
        testAppMain(scoreFile, scoreboard);

    }

    @Test
    public void all0TwelveFrameBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void allF_TwelveFrameBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void twoPlayersTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores-12.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\t8\t1\t\tX\n"
                + "Score\t\t30\t\t17\t\t9\t\t30\t\t8\t\t18\t\t6\t\t30\t\t30\t\t30\t\t9\t\t30\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t\tX\t9\t0\t8\t1\n"
                + "Score\t\t13\t\t9\t\t30\t\t9\t\t30\t\t30\t\t9\t\t17\t\t8\t\t30\t\t9\t\t9\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void perfectTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void all0TwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void allF_TwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void extraScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/extra-score.txt";
        String expectedOutput = "ExtraScoreException Extra Score\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void all0ExtraScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/all0-extra-score.txt";
        String expectedOutput = "ExtraScoreException Extra Score\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void allF_ExtraScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/allF-extra-score.txt";
        String expectedOutput = "ExtraScoreException Extra Score\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void perfectExtraScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/perfect-extra-score.txt";
        String expectedOutput = "ExtraScoreException Extra Score\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void empty() {
        String scoreFile = AppTest.testResourcesPath + "/negative/empty.txt";
        String expectedOutput = "FileContentException Empty score file\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void freeText() {
        String scoreFile = AppTest.testResourcesPath + "/negative/free-text.txt";
        String expectedOutput = "FileContentException Invalid score file, missing column\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void invalidScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/invalid-score.txt";
        String expectedOutput = "ScoreValueException Invalid score, found lorem at line 2\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void missingRolls() {
        String scoreFile = AppTest.testResourcesPath + "/negative/missing-rolls.txt";
        String expectedOutput = "MissingFrameException Frames are missing: 8 frames found\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void negative() {
        String scoreFile = AppTest.testResourcesPath + "/negative/negative.txt";
        String expectedOutput = "ScoreValueException Invalid score, found -5 at line 2\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void invalidFrame() {
        String scoreFile = AppTest.testResourcesPath + "/negative/invalid-frame.txt";
        String expectedOutput = "InvalidFrameException Invalid frame sum at frame 2, total: 11\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }
}
