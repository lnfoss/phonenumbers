package com.nfoss.techtask.phone;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PhoneParser {

    public void printNumbers(String path) {
        System.out.println("Reading files");
        try {
         Files.walk(Paths.get(path))
                 .filter(Files::isRegularFile)
                 .filter(this::isValidExtension)
                 .map(this::readFile)
                 .flatMap(List::stream)
                 .distinct()
                 .sorted()
                 .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Boolean isValidExtension(Path path){
        return path.toString().endsWith(".txt");
    }

    private List<String> readFile(Path path) {
        try {
            return Files.lines(path)
                    .map(PhoneNumberFormatter::formatNumber)
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    /*
    recorrer la lista de files - OK
    obtener las txt - OK
    skip invalid phones
    [+][-|spc][cc][ddd|(ddd)][-|spc]<nnn-nnnn|nnn-nn-nn|nnnnnnn> ---- Unified +cc (ddd) nnn-nnnn
    default cc 7
    default ddd 812
    sacar duplicados
    ordenar de menor a mayor
    */



}
