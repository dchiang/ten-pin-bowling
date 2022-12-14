package com.dchiang.bowling.unit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dchiang.bowling.AppTest;
import com.dchiang.bowling.utils.FileHandler;

public class FileHandlerTest extends FileHandler {


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
    public void unsupportedReaderObject() throws IOException {
        assertNull(FileHandler.readFile(new ArrayList<>(), "\\t"));
    }

    @Test
    public void fileNotFoundHandled() throws IOException{
        String filename = "dummy text";
        assertNull(FileHandler.readFile(filename, "\\t"));
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
}
