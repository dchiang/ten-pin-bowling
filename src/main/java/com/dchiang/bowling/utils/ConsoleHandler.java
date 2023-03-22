package com.dchiang.bowling.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Wrapper class for I/O operations in the cli
 */
public class ConsoleHandler {

    private ConsoleHandler() {
    }

    /**
     *
     * @return a String containing a line of text received through the cli
     * @throws IOException if failed or interrupted input operations.
     */
    public static String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    /**
     *
     * @param message  the message to be printed in the cli
     */
    public static void println(String message) {
        System.out.println(message);
    }
}
