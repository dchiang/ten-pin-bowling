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
    public void allFTwelveFrameBowlingPositiveInput() {
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
    public void allFTwelveFrameBowlingPositiveInteractiveInput() {
        String scoreFile = AppTest.testResourcesPath + "/positive/allF-12.txt";
        String scoreboard = "Carl\n"
                + "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\n"
                + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        testAppMainInteractiveInput(scoreFile, scoreboard);
    }
}
