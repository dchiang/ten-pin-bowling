package com.dchiang.bowling.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileReader;

/**
 * Wrapper class that provides utility methods for file handling operations.
 */
public class FileHandler {

    private FileHandler() {
    }

    /**
     * Reads a file from a given path or input stream and returns the records in the
     * file as a List of String Arrays.
     *
     * @param t         file path or input stream of the file to be read
     * @param separator the separator character to be used to split records in the
     *                  file
     * @return a list of String Arrays containing the records in the file
     */
    public static List<String[]> readFile(Object t, String separator) {
        List<String[]> records = null;
        BufferedReader reader = null;
        try {
            if (t instanceof String) {
                reader = new BufferedReader(new FileReader((String) t));
            } else if (t instanceof InputStream) {
                reader = new BufferedReader(new InputStreamReader((InputStream) t));
            } else {
                return records;
            }
            Stream<String> input = reader.lines();
            records = input.map(x -> x.split(separator)).collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            ConsoleHandler.println(e.getClass().getSimpleName() + " Error loading file " + e.getMessage());
        }
        return records;
    }

    /**
     * Checks if a file exists and is readable at a given path.
     *
     * @param path path of the file to be checked
     * @return true if the file exists and is readable, false otherwise
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        boolean result = false;
        if (file.isFile()) {
            result = file.canRead();
        }
        return result;
    }
}
