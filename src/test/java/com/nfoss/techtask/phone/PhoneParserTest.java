package com.nfoss.techtask.phone;

import org.junit.jupiter.api.Test;

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
        //the file contains 12 valid numbers
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

    @Test
    void shouldRemoveDuplicatedNumbers() {
        // 3 valid numbers repeated several times
        List<String> result = phoneParser
                .getFormattedNumbers("./src/main/resources/files/inner/duplicated.txt");
        assert result.size() == 3;
    }

}