package com.company;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Regextest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String text = "ched";
        String patternString = "[A-Za-z]{4}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        if (Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}").matcher("555-555-5555").matches()) {
            System.out.println("match found yo");
        } else {
            System.out.println("bruh");
        }
    }
}
