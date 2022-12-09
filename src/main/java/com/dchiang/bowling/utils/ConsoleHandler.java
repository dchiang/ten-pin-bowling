package com.dchiang.bowling.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleHandler {

    private static ConsoleHandler instance = null;
    private BufferedReader reader = null;

    private ConsoleHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static ConsoleHandler getInstance() {
        if (instance == null) {
            return new ConsoleHandler();
        } else {
            return instance;
        }
    }

    public String readLine() throws IOException {
        String input = reader.readLine();
        return input;
    }

    public void closeReader() throws IOException {
        this.reader.close();
    }
}
