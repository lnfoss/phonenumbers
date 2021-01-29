package com.nfoss.techtask.phone;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class PhoneParserTest {

    private final PhoneParser phoneParser = new PhoneParser();


    @Test
    void shouldGetAnEmptyListOfNumbers() {
        List<String> result = phoneParser
                .getFormattedNumbers("./src/main/resources/files/inner/invalidnumbers.txt");
        assert result.isEmpty();
    }

    @Test
    void shouldGetAListOfValidNumbers() {
        List<String> result = phoneParser
                .getFormattedNumbers("./src/main/resources/files/validnumbers.txt");
        assert result.size() == 12;
    }

    @Test
    void shouldGetEmptyListWhenNoValidExtension() {
        List<String> result = phoneParser
                    .getFormattedNumbers("./src/main/resources/files/inner/data");
        assert result.isEmpty();

    }

}