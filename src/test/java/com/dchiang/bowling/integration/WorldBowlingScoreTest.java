package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;
import com.dchiang.bowling.AppTest;

public class WorldBowlingScoreTest {

    private final String gameSelection = "3";

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
        String expectedOutput = AppTest.gameMenu + AppTest.fileAsk + AppTest.tenFrames +scoreboard;
        systemInMock.provideLines(gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void twoPlayersWorldBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores-world.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t47\t\t56\t\t86\t\t94\t\t112\t\t118\t\t148\t\t178\t\t208\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t5\t/\n"
                + "Score\t\t13\t\t22\t\t52\t\t61\t\t91\t\t121\t\t130\t\t147\t\t155\t\t170\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void perfectWorldBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect-world.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMain(scoreFile, scoreboard);

    }

    @Test
    public void all0WorldBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void allF_WorldBowlingPositiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, scoreboard);
    }

    @Test
    public void twoPlayersWorldBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/scores-world.txt";
        String scoreboard = "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t47\t\t56\t\t86\t\t94\t\t112\t\t118\t\t148\t\t178\t\t208\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t5\t/\n"
                + "Score\t\t13\t\t22\t\t52\t\t61\t\t91\t\t121\t\t130\t\t147\t\t155\t\t170\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void perfectWorldBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/perfect-world.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void all0WorldBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void allF_WorldBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

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
