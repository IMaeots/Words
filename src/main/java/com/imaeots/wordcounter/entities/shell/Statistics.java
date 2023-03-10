package com.imaeots.wordcounter.entities.shell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Statistics {
    private String filePath;
    private int numLines;
    private int numWords;
    private int numChars;

    public Statistics(String filePath) {
        this.filePath = filePath;
    }

    public void calculate() throws IOException {
        numLines = 0;
        numWords = 0;
        numChars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numLines++;
                numWords += line.split("\\s+").length;
                numChars += line.length();
            }
        }
    }

    public void printStatistics() {
        System.out.println("Number of lines: " + numLines);
        System.out.println("Number of words: " + numWords);
        System.out.println("Number of characters: " + numChars);
    }
}