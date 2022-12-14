package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;
import com.dchiang.bowling.AppTest;

public class TenPinsTraditionalBowlingTest {

    private final String gameSelection = "1";

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private void testAppMain(String scoreFile, String scoreboard) {
        String expectedOutput = AppTest.tenFrames + scoreboard;
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    private void testAppMainInteractiveInput(String scoreFile, String scoreboard) {
        String expectedOutput = AppTest.gameMenu + AppTest.fileAsk + AppTest.tenFrames + scoreboard;
        systemInMock.provideLines(gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void twoPlayersTraditionalBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n"
                + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n"
                + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void perfectTraditionalBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void all0TraditionalBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void allF_TraditionalBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void twoPlayersTraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n"
                + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n"
                + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void perfectTraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void all0TraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void allF_TraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void requestValidFileTraditionalBowlingPositiveInteractiveInput() {
        String invalidFile = AppTest.testResourcesPath + "/positive/dummy-file-name.txt";
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        String expectedOutput = AppTest.gameMenu + AppTest.fileAsk
                + "Not a valid file\n"
                + "Enter the absolute path to the score file:\n"
                + AppTest.tenFrames + scoreboard;
        systemInMock.provideLines(gameSelection, invalidFile, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void invalidInputForGameSelectionTraditionalBowlingPositiveInteractiveInput() {
        String invalidGameSelection = "dummy string";
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        String expectedOutput = AppTest.gameMenu
                + "Not a valid input: \""
                + invalidGameSelection
                + "\", enter a valid option (0 to exit)\n"
                + AppTest.fileAsk
                + AppTest.tenFrames + scoreboard;
        systemInMock.provideLines(invalidGameSelection, gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void invalidValueForGameSelectionTraditionalBowlingPositiveInteractiveInput() {
        String invalidGameSelection = "-1";
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        String expectedOutput = AppTest.gameMenu
                + "Not a valid input: \""
                + invalidGameSelection
                + "\", enter a valid option (0 to exit)\n"
                + AppTest.fileAsk
                + AppTest.tenFrames + scoreboard;
        systemInMock.provideLines(invalidGameSelection, gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
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
    public void noBonusRollExtraScore() {
        String scoreFile = AppTest.testResourcesPath + "/negative/no-bonus-roll-extra-score.txt";
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

    @Test
    public void invalidPlayerName() {
        String scoreFile = AppTest.testResourcesPath + "/negative/invalid-player-name.txt";
        String expectedOutput = "FileContentException Invalid player name, found @Carl\n";
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }
}
