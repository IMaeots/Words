package com.imaeots.wordcounter.entities.shell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
    private String filePath;

    public FileLoader(String filePath) {
        this.filePath = filePath;
    }

    public String loadData() throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

}
