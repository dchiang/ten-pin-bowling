package com.dchiang.bowling.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dchiang.bowling.utils.Validator;

public class ValidatorTest {

    @Test
    public void hasValidFormatStringTrueResult() {
        String playerName = "Carl";
        assertTrue(Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$"));
    }

    @Test
    public void hasValidFormatStringFalseResult() {
        String playerName = "Carl.";
        assertFalse(Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$"));
    }
}
