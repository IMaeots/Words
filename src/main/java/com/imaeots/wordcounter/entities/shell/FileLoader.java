package com.imaeots.wordcounter.entities.shell;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

public class FileLoader {

    /**
     * Method to read all lines from the provided file.
     * @param fileURL location of the file that will be readen
     * @return List of text that was in the file
     * @throws IOException incase provided file is incorrect
     */
    public List<String> readFile(String fileURL) throws IOException {
        File f = new File(fileURL);
        if (f.isFile() && f.canRead()) {
            Path path = f.toPath();
            return Files.readAllLines(path);
        }
        else {
            throw new IOException("Can't read file " +  fileURL);
        }
    }

    public List<String> readFile(Path path) throws IOException {
            return Files.readAllLines(path);
    }
}
