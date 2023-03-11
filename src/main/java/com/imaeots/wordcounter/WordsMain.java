package com.imaeots.wordcounter;

import com.imaeots.wordcounter.entities.shell.DragAndDropFileFrame;
import com.imaeots.wordcounter.entities.shell.Statistics;

public class WordsMain {
    public static void main(String[] args) {
        
        // Making the main frame
        DragAndDropFileFrame frame = new DragAndDropFileFrame();

        // Loop until the user has dropped a file
        while(!frame.isReady) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Receive the data from the file
        StringBuilder data = frame.getData();
        System.out.println(data);

        // Get the statistics of the data
        Statistics stats = new Statistics(data);
        stats.calculate();
        stats.printStatistics();

    }
}
