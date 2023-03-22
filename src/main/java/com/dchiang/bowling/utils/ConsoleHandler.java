package com.dchiang.bowling.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Wrapper class for I/O operations in the cli.
 */
public class ConsoleHandler {

    private ConsoleHandler() {
    }

    /**
     * Reads a line string from the standard input and returns it.
     *
     * @return a string containing a line of text read from standard input
     * @throws IOException if failed or interrupted input operations
     */
    public static String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    /**
     * Prints a message to the standard output stream in the command line interface.
     *
     * @param message the message to be printed in the standard output stream
     */
    public static void println(String message) {
        System.out.println(message);
    }
}
