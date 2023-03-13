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
import java.lang.Math;

public class Statistics extends JFrame {
    private String data;
    private int numLines;
    private int numWords;
    private int numSyllables = 0;
    private int numChars;
    private int numSentence;
    private int numParagraph;
    private String mostCommonWord;
    private int maxCount = 0;
    private String readingLevel;


    public Statistics(StringBuilder input) {
        data = input.toString();
    }

    public void calculate() {
        // WordCount
        String[] words = data.toLowerCase().split("\\s+");
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
        // SyllableCount
        for (String word : words) {
            numSyllables += countSyllablesInWord(word);
        }
        
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

        for (Map.Entry<String, Integer> entry : mostCommonWords.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
        
            if (count > maxCount) {
                maxCount = count;
                mostCommonWord = word;
            }
        }

        // Reading level
        // Using Flesch Reading Ease formula
        // -> 206.835-1.015(total words/total sentences) - 84.6(total chars/total words)
        double subindex = 206.835 - 1.015 * (numWords / numSentence) - 84.6 * (numSyllables / numWords);
        long index = Math.round(subindex);
        // Getting the Score
        if (index >= 90 && index <= 100) {
            readingLevel = String.format("%d - Very easy", index);
        } else if (index >= 80 && index < 90) {
            readingLevel = String.format("%d - Easy", index);
        } else if (index >= 70 && index < 80) {
            readingLevel = String.format("%d - Fairly easy", index);
        } else if (index >= 60 && index < 70) {
            readingLevel = String.format("%d - Standard", index);
        } else if (index >= 50 && index < 60) {
            readingLevel = String.format("%d - Fairly difficult", index);
        } else if (index >= 30 && index < 50) {
            readingLevel = String.format("%d - Difficult", index);
        } else if (index >= 0 && index < 30) {
            readingLevel = String.format("%d - Very difficult", index);
        } else {
            readingLevel = String.format("Error 404");
        }
    }

    // Syllable function
    public static int countSyllablesInWord(String word) {
        int count = 0;
        boolean prevVowel = false;
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            boolean isVowel = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y' || c == 'ä' || c == 'õ' || c == 'ö' || c == 'ü' );
            if (isVowel && !prevVowel) {
                count++;
                prevVowel = true;
            } else if (isVowel) {
                prevVowel = true;
            } else {
                prevVowel = false;
            }
        }
        char lastChar = Character.toLowerCase(word.charAt(word.length() - 1));
        if (lastChar == 'e') {
            count--;
        }
        return Math.max(count, 1);
    }

    // Uses frame to make it stylistic for the stats.
    public void displayStatistics(JFrame frame) {
        String stats = ("Number of words: " + numWords + "\n"
         + "Number of characters: " + numChars + "\n" 
         + "Number of syllables: " + numSyllables + "\n"
         + "Number of sentences: " + numSentence + "\n"
         + "Number of lines: " + numLines + "\n"
         + "Number of paragraphs: " + numParagraph + "\n"
         + "The most common word is: " + mostCommonWord + " (with " + Integer.toString(maxCount) + " uses)\n"
         + "The reading level: " + readingLevel + "\n"
         + "Reading level was determined with Flesch Reading Ease formula.\n"
         + "Lower score indicates more proficiency. (0-100)");
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Words - Stats of your file");

        JTextArea textInput = new JTextArea(stats);
        textInput.setFont(new Font("Timesnewroman", Font.PLAIN, 24));
        Dimension size = textInput.getPreferredSize();
        size.width += 10;
        size.height += 5;
        textInput.setPreferredSize(size);
        frame.getContentPane().add(textInput, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);
    }

}
