package com.imaeots.wordcounter.entities.shell;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;

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

    public void displayStatistics(JFrame frame) {
        String stats = ("Number of lines: " + numLines + "\n" + "Number of words: " + numWords + "\n" + "Number of characters: " + numChars + "\n");
        frame.getContentPane().removeAll();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setTitle("Words - Stats of your file");
        frame.setSize(300,300);

        JTextField textInput = new JTextField(stats);
        Dimension textDimension = new Dimension(200,200);
        textInput.setSize(textDimension);
        JPanel panel = new JPanel();
        panel.add(textInput);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(textInput);
        frame.setVisible(true);
        System.out.println(stats);
    }

}
