package com.dchiang.bowling.unit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runners.MethodSorters;

import com.dchiang.bowling.utils.ConsoleHandler;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ConsoleHandlerTest extends ConsoleHandler{

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void readConsoleLine() throws IOException {
        String input = "Hello World!";
        systemInMock.provideLines(input);
        String line = ConsoleHandler.readLine();
        assertEquals(input, line);
    }
}