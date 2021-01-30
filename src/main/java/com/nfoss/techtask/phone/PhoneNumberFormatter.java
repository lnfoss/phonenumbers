package com.nfoss.techtask.phone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberFormatter {

    /*
    input [+cc|cc][-|s][ddd|(ddd)][-|s]<nnn-nnnn|nnn-nn-nn|nnnnnnn>
    output +cc (ddd) nnn-nnnn
     */

    static final String DEFAULT_OPERATOR = "+";
    static final String DEFAULT_CC = "7";
    static final String DEFAULT_CITY = "812";
    static final String REGEX = "(?:\\+\\d|\\d)?[-.\\s]?(?:\\(\\d{3}\\)|\\d{3})?[-.\\s]?(?:\\d{3}-\\d{4}|\\d{3}-\\d{2}-\\d{2}|\\d{7})";



    public static String formatNumber(String number) {
        return isValidNumber(number)? toUnified(number) : null;
    }

    private static boolean isValidNumber(String number) {
        final Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    private static String toUnified(String number){
        String str = number.replaceAll("[^0-9]", "");
        switch (str.length()){
            case 7 : return String.format("%s%s (%s) %s", DEFAULT_OPERATOR, DEFAULT_CC, DEFAULT_CITY, str);
            case 8 : return String.format("%s%s (%s) %s", DEFAULT_OPERATOR, str.substring(0, 1), DEFAULT_CITY, str.substring(1, 8));
            case 10 :return String.format("%s%s (%s) %s", DEFAULT_OPERATOR, DEFAULT_CC, str.substring(0, 3), str.substring(3, 10));
            case 11 :return String.format("%s%s (%s) %s", DEFAULT_OPERATOR, str.substring(0, 1), str.substring(1, 4), str.substring(4, 11));
            default: return "NOT MACH";
        }
    }
}
