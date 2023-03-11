package com.imaeots.wordcounter.entities.shell;

public class Statistics {
    private String data;
    private int numLines;
    private int numWords;
    private int numChars;

    public Statistics(StringBuilder input) {
        data = input.toString();
    }

    public void calculate() {
        numLines = 0;
        numWords = 0;
        numChars = 0;

        char[] charArray = data.toCharArray();
    
        for (char c : charArray) {
            numChars += 1;
            if (c == ' ') {
                numWords += 1;
            }
            if (c == '\n') {
                numLines += 1;
            }
        }

    }

    public void printStatistics() {
        System.out.println("Number of lines: " + numLines);
        System.out.println("Number of words: " + numWords);
        System.out.println("Number of characters: " + numChars);
    }

}
