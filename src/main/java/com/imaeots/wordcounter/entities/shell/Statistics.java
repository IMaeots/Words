package com.imaeots.wordcounter.entities.shell;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;

public class Statistics extends JFrame {
    private String data;
    private int numLines;
    private int numWords;
    private int numChars;
    private int numSentence;
    private int numParagraph;
    private String mostCommonWord;
    //private int avgWordLength;
    //private String readingLevel;


    public Statistics(StringBuilder input) {
        data = input.toString();
    }

    public void calculate() {
        // WordCount
        String[] words = data.split("\\s+");
        numWords = words.length;
        // CharCount
        numChars = data.length();
        // LineCount
        String[] lines = data.split("\n");
        numLines = lines.length;
        // SentenceCount
        String[] sentences = data.split("[.!?]\\s+");
        numSentence = sentences.length;
        // ParagraphCount
        String[] paragraphs = data.split("\n\n");
        numParagraph = paragraphs.length;
        // MostCommonWords
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : words) {
            String cleanedWord = word.replaceAll("[^a-zA-Z ]","").toLowerCase();

            if (cleanedWord.length() > 0) {
                wordCountMap.put(cleanedWord, wordCountMap.getOrDefault(cleanedWord, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCountMap.entrySet());
        wordList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> mostCommonWords = new LinkedHashMap<>();
        for (int i = 0; i < numWords && i < wordList.size(); i++) {
            mostCommonWords.put(wordList.get(i).getKey(), wordList.get(i).getValue());
        }

        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : mostCommonWords.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
        
            if (count > maxCount) {
                maxCount = count;
                mostCommonWord = word;
            }
        }

    }

    // Uses frame to make it stylistic for the stats.
    public void displayStatistics(JFrame frame) {
        String stats = ("Number of lines: " + numLines + "\n"
         + "Number of words: " + numWords + "\n"
         + "Number of characters: " + numChars + "\n"
         + "Number of sentences: " + numSentence + "\n"
         + "Number of paragraphs: " + numParagraph + "\n"
         + "Most common word is: " + mostCommonWord);
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Words - Stats of your file");

        JTextArea textInput = new JTextArea(stats);
        textInput.setFont(new Font("Arial", Font.PLAIN, 24));
        Dimension size = textInput.getPreferredSize();
        size.width += 10;
        size.height += 5;
        textInput.setPreferredSize(size);
        frame.getContentPane().add(textInput, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);
        System.out.println(stats);
    }

}
