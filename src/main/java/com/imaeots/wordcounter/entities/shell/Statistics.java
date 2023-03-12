package com.imaeots.wordcounter.entities.shell;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.awt.BorderLayout;

public class Statistics extends JFrame {
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

    // Uses frame to make it stylistic for the stats.
    public void displayStatistics(JFrame frame) {
        String stats = ("Number of lines: " + numLines + "\n" + "Number of words: " + numWords + "\n" + "Number of characters: " + numChars);
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
