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

public class FileHandler {

    public static List<String[]> readFile(Object t, String separator) throws IOException {
        List<String[]> records;
        BufferedReader reader;
        if (t instanceof String) {
            reader = new BufferedReader(new FileReader((String) t));
        } else if (t instanceof InputStream) {
            reader = new BufferedReader(new InputStreamReader((InputStream) t));
        } else {
            return null;
        }
        Stream<String> input = reader.lines();
        records = input.map(x -> x.split(separator)).collect(Collectors.toList());
        reader.close();
        return records;
    }

    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.isFile() && file.canRead();
    }
}
