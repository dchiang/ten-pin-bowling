package com.dchiang.bowling;

import java.io.File;

public class AppTest {

    public static final String gameMenu = "Type the number of the game you would like to play (0 to exit):\n"
            + "1 -> Traditional Ten Frames Bowling\n"
            + "2 -> Twelve Frames Bowling\n"
            + "3 -> World Bowling\n";

    public static final String fileAsk = "Enter the absolute path to the score file:\n";

    public static final String tenFrames = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

    public static final String twelveFrames = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\n";

    public static String resourcesPath = getResourcesAbsolutePath();

    private static String getResourcesAbsolutePath() {
        String path = "src/test/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}
