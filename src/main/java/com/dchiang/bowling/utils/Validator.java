package com.dchiang.bowling.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
    }

    public static boolean hasValidFormat(String inputString, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }
}
