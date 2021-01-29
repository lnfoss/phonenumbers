package com.nfoss.techtask.phone;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberFormatter {

    static final String DEFAULT = "+7 (123) 123 456 ";

    public static String formatNumber(String number) {
        return DEFAULT + number;
    }

}
