package com.dchiang.bowling.unit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runners.MethodSorters;

import com.dchiang.bowling.AppTest;
import com.dchiang.bowling.utils.ConsoleHandler;
import com.dchiang.bowling.utils.FileHandler;
import com.dchiang.bowling.utils.Validator;

@FixMethodOrder(MethodSorters.DEFAULT)
public class UtilsTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void readConsoleLine() throws IOException {
        String input = "Hello World!";
        systemInMock.provideLines(input);
        String line = ConsoleHandler.readLine();
        assertEquals(input, line);
    }

    @Test
    public void fileExist() {
        String scoreFile = AppTest.testResourcesPath + "/positive/all0.txt";
        assertTrue(FileHandler.fileExists(scoreFile));
    }

    @Test
    public void fileDontExist() {
        String scoreFile = AppTest.testResourcesPath + "/positive/dummy-name.txt";
        assertFalse(FileHandler.fileExists(scoreFile));
    }

    @Test
    public void readFileUsingFileReader() throws IOException {
        String filename = AppTest.appResourcesPath + "/games.txt";
        List<String[]> records = FileHandler.readFile(filename, "\\t");
        List<String[]> expectedRecords = AppTest.gameMenuOptions;
        assertTrue(records.size() == expectedRecords.size());
        for (int i = 0; i < records.size(); i++) {
            records.get(i);
            assertArrayEquals(records.get(i), expectedRecords.get(i));
        }
    }

    @Test
    public void readFileUsingInputStream() throws IOException {
        String filename = "/games.txt";
        List<String[]> records = FileHandler.readFile(AppTest.class.getResourceAsStream(filename), "\\t");
        List<String[]> expectedRecords = AppTest.gameMenuOptions;
        assertTrue(records.size() == expectedRecords.size());
        for (int i = 0; i < records.size(); i++) {
            records.get(i);
            assertArrayEquals(records.get(i), expectedRecords.get(i));
        }
    }

    @Test
    public void hasValidFormatStringTrueResult() {
        String playerName = "Carl";
        assertTrue(Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$"));
    }

    @Test
    public void hasValidFormatStringFalseResult() {
        String playerName = "Carl.";
        assertFalse(Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$"));
    }
}