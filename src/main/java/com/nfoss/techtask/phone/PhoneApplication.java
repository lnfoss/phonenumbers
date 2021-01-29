package com.nfoss.techtask.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneApplication.class, args);
        PhoneParser phoneParser = new PhoneParser();
        System.out.println("Looking for valid files...");
        phoneParser.getFormattedNumbers("./src/main/resources/files/")
        .forEach(System.out::println);
    }

}
