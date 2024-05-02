package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    public static Iterator<List<String>> readCSV(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.lines().map(line -> Arrays.asList(line.split(","))).iterator();
    }
}