package com.dchiang.bowling.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.dchiang.bowling.App;

public class TenPinsTraditionalBowlingTest {

    private final String gameMenu = "Type the number of the game you would like to play (0 to exit):\n"
            + "1 -> Traditional Ten Frames Bowling\n"
            + "2 -> Twelve Frames Bowling\n"
            + "3 -> World Bowling\n";

    private final String fileAsk = "Enter the absolute path to the score file:\n";

    private final String gameSelection = "1";

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
    public void TwoPlayersTraditionalBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/scores.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n"
                + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n"
                + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void PerfectTraditionalBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMain(scoreFile, expectedOutput);

    }

    @Test
    public void All0TraditionalBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/all0.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void AllFTraditionalBowlingPositiveInput() {
        String scoreFile = resourcesPath + "/positive/allF.txt";
        String expectedOutput = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMain(scoreFile, expectedOutput);
    }

    @Test
    public void TwoPlayersTraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/scores.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Jeff\n"
                + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n"
                + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n"
                + "John\n"
                + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n"
                + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void PerfectTraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/perfect.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n"
                + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void All0TraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/all0.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }

    @Test
    public void AllFTraditionalBowlingPositiveInteractiveInput() {
        String scoreFile = resourcesPath + "/positive/allF.txt";
        String scoreboard = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                + "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        testAppMainInteractiveInput(scoreFile, scoreboard);
    }
}
