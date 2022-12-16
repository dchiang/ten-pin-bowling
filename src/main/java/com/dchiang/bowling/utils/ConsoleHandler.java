package com.dchiang.bowling.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHandler {

    private ConsoleHandler() {
    }

    public static String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
