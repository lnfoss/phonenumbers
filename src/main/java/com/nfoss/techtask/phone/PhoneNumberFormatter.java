package com.nfoss.techtask.phone;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneNumberFormatter {

    /*
    input [+][-|spc][cc][ddd|(ddd)][-|spc]<nnn-nnnn|nnn-nn-nn|nnnnnnn>
    output +cc (ddd) nnn-nnnn
    default cc 7
    default ddd 812
     */

    static final String DEFAULT = "+7 (123) 123 456 ";
    static final String DEFAULT_OPERATOR = "+";
    static final String DEFAULT_CC = "7";
    static final String DEFAULT_CITY = "812";
    static final String REGEX = "\\+?\\d?[-.\\s]?(?:\\(?\\d{3}?\\)?)?[-.\\s]?\\d{3}[-.\\s]?\\d{2}?[-.\\s]?\\d{2}?";



    public static String formatNumber(String number) {
        if (isValidNumber(number)){
            return toUnified(number);
        }
        else return ("INVALID");

    }

    private static boolean isValidNumber(String number) {
        final Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    private static String toUnified(String number){
        return number;
    }

}
