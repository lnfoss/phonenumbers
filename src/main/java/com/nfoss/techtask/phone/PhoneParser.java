package com.nfoss.techtask.phone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class PhoneParser {

    public List<String> getFormattedNumbers(String path) {
        try {
            return  Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(this::isValidExtension)
                    .map(this::readFile)
                    .flatMap(List<String>::stream)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Boolean isValidExtension(Path path){
        return path.toString().endsWith(".txt");
    }

    private List<String> readFile(Path path) {
        try {
            return Files.lines(path)
                    .map(PhoneNumberFormatter::formatNumber)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
