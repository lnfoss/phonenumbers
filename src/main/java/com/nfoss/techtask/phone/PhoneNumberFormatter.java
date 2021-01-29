package com.nfoss.techtask.phone;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneNumberFormatter {

    /*
    input [+][-|spc][cc][ddd|(ddd)][-|spc]<nnn-nnnn|nnn-nn-nn|nnnnnnn>
    output +cc (ddd) nnn-nnnn
     */

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
        String str = number.replaceAll("[^0-9]", "");
        switch (str.length()){
            case 7 : return DEFAULT_OPERATOR + DEFAULT_CC + " ("+DEFAULT_CITY+") "+ str;
            case 8 : return DEFAULT_OPERATOR + str.substring(0,1) + " ("+DEFAULT_CITY+") "+ str.substring(1,8);
            case 10 :return  DEFAULT_OPERATOR + DEFAULT_CC +  " ("+str.substring(0,3)+") "+ str.substring(3,10);
            case 11 :return  DEFAULT_OPERATOR + str.substring(0,1) +  " ("+str.substring(1,4)+") "+ str.substring(4,11);
            default: return "INVALID";
        }
    }
}
