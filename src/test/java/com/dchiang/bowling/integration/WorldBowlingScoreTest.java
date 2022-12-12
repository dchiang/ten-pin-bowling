package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;

public class WorldBowlingScoreTest {

    private final String gameMenu = "Type the number of the game you would like to play (0 to exit):\n"
            + "1 -> Traditional Ten Frames Bowling\n"
            + "2 -> Twelve Frames Bowling\n"
            + "3 -> World Bowling\n";

    private final String fileAsk = "Enter the absolute path to the score file:\n";

    private final String gameSelection = "3";

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private final String resourcesPath = getFileAbsolutePath();

    private String getFileAbsolutePath() {
        String path = "src/test/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    private void testAppMain(String scoreFile, String expectedOutput) {
        App.main(new String[] { gameSelection, scoreFile });
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    private void testAppMainInteractiveInput(String scoreFile, String scoreboard) {
        String expectedOutput = gameMenu + fileAsk + scoreboard;
        systemInMock.provideLines(gameSelection, scoreFile);
        App.main(new String[] {});
        assertEquals(expectedOutput, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void TwoPlayersWorldBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/scores-world.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t47\t\t56\t\t86\t\t94\t\t112\t\t118\t\t148\t\t178\t\t208\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t5\t/\n"
                + "Score\t\t13\t\t22\t\t52\t\t61\t\t91\t\t121\t\t130\t\t147\t\t155\t\t170\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void PerfectWorldBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect-world.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMain(scoreFile, expectedOutput);

    }

    @Test
    public void All0WorldBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/all0.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void AllFWorldBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/allF.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void TwoPlayersWorldBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/scores-world.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t47\t\t56\t\t86\t\t94\t\t112\t\t118\t\t148\t\t178\t\t208\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t5\t/\n"
                + "Score\t\t13\t\t22\t\t52\t\t61\t\t91\t\t121\t\t130\t\t147\t\t155\t\t170\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void PerfectWorldBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect-world.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void All0WorldBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/all0.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void AllFWorldBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/allF.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        testAppMainInteractiveInput(scoreFile, scoreboard);
    }
}
