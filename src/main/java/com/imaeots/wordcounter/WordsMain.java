package com.imaeots.wordcounter;

import com.imaeots.wordcounter.entities.shell.FileLoader;
import com.imaeots.wordcounter.entities.shell.Statistics;
import java.io.IOException;
import java.util.List;

public class WordsMain {
    public static void main(String[] args) throws IOException {
        System.out.println("hi");
        // checking for valid terminal input
        if (args != null && args.length == 1) {
            String path = args[0];

            // Opening the file and reading it
            FileLoader file = new FileLoader();
            List<String> text = file.readFile(path);
            
            // Getting statistics
            Statistics stats = new Statistics();
            stats.showStats(text);
        }
        else {
            // Output error message
            System.out.println("Invalid file path parameter.");
        }

    }
}