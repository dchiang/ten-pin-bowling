package com.dchiang.bowling.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Validator class provides methods for validating input strings against a
 * given regular expression.
 *
 */
public class Validator {

    private Validator() {
    }

    /**
     * Checks if the given input string matches the provided regular expression.
     *
     * @param inputString the input string to be validated
     * @param regex       the regular expression to match against
     * @return true if inputString matches the regex, false otherwise
     */
    public static boolean hasValidFormat(String inputString, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }
}
