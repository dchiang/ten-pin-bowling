package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;

public class TwelveFrameBowlingTest {

    private final String gameMenu = "Type the number of the game you would like to play (0 to exit):\n"
            + "1 -> Traditional Ten Frames Bowling\n"
            + "2 -> Twelve Frames Bowling\n"
            + "3 -> World Bowling\n";

    private final String fileAsk = "Enter the absolute path to the score file:\n";

    private final String frames = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\n";

    private final String gameSelection = "2";

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
    public void TwoPlayersTwelveFrameBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/scores-12.txt";
        String expectedOutput = frames
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\t8\t1\t\tX\n"
                + "Score\t\t30\t\t17\t\t9\t\t30\t\t8\t\t18\t\t6\t\t30\t\t30\t\t30\t\t9\t\t30\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t\tX\t9\t0\t8\t1\n"
                + "Score\t\t13\t\t9\t\t30\t\t9\t\t30\t\t30\t\t9\t\t17\t\t8\t\t30\t\t9\t\t9\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void PerfectTwelveFrameBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect-12.txt";
        String expectedOutput = frames
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\n";
        testAppMain(scoreFile, expectedOutput);

    }

    @Test
    public void All0TwelveFrameBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/all0-12.txt";
        String expectedOutput = frames
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void AllFTwelveFrameBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/allF-12.txt";
        String expectedOutput = frames
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void TwoPlayersTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/scores-12.txt";
        String scoreboard = frames
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\t\tX\t8\t1\t\tX\n"
                + "Score\t\t30\t\t17\t\t9\t\t30\t\t8\t\t18\t\t6\t\t30\t\t30\t\t30\t\t9\t\t30\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\t\tX\t9\t0\t8\t1\n"
                + "Score\t\t13\t\t9\t\t30\t\t9\t\t30\t\t30\t\t9\t\t17\t\t8\t\t30\t\t9\t\t9\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void PerfectTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect-12.txt";
        String scoreboard = frames
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\n"
                + "Score\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\t\t30\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void All0TwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/all0-12.txt";
        String scoreboard = frames
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void AllFTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/allF-12.txt";
        String scoreboard = frames
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        testAppMainInteractiveInput(scoreFile, scoreboard);
    }
}
