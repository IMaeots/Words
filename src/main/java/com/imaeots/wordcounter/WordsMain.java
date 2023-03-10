package com.imaeots.wordcounter;

import com.imaeots.wordcounter.entities.shell.DragAndDropFileFrame;
import com.imaeots.wordcounter.entities.shell.FileLoader;
//import com.imaeots.wordcounter.entities.shell.Statistics;

import java.io.IOException;
import javax.swing.JFrame;

public class WordsMain {
    public static void main(String[] args) {
        
        // Making the main frame 
        DragAndDropFileFrame frame = new DragAndDropFileFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);

        boolean input = false;
        // File located
        while (!input) {
            // Loading the file and it's data
            if (frame.isReady) {
                FileLoader loader = new FileLoader(frame.filePath);
                try {
                    String data = loader.loadData();
                    input = true;
                    System.out.println(data);
                } catch (IOException e) {
                    e.printStackTrace();
                    input = true;
                }
            }
            else continue;
        
        /* // checking for valid input
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
            System.out.println("Invalid file.");
        }
        */

    }
}