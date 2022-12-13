package com.dchiang.bowling;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppTest {

    public static final String gameMenu = "Type the number of the game you would like to play (0 to exit):\n"
            + "1 -> Traditional Ten Frames Bowling\n"
            + "2 -> Twelve Frames Bowling\n"
            + "3 -> World Bowling\n";

    public static final String fileAsk = "Enter the absolute path to the score file:\n";

    public static final String tenFrames = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

    public static final String twelveFrames = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\n";

    public static String testResourcesPath = getResourcesAbsolutePath("src/test/resources");

    public static String appResourcesPath = getResourcesAbsolutePath("src/main/resources");

    public static List<String[]> gameMenuOptions = getGameMenuOptions();

    private static String getResourcesAbsolutePath(String path) {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    private static List<String[]> getGameMenuOptions() {
        List<String[]> records = new ArrayList<>();
        records.add(new String[] { "1", "Traditional Ten Frames Bowling" });
        records.add(new String[] { "2", "Twelve Frames Bowling" });
        records.add(new String[] { "3", "World Bowling" });
        return records;
    }
}
