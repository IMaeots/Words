package com.imaeots.wordcounter.entities.shell;

import org.junit.jupiter.api.Test;
import java.io.IOException;

// Testing
public class FileLoaderTest {
    @Test
    public void testFileLoader() throws IOException {
        FileLoader f = new FileLoader();
        f.readFile("file.txt");
    }
}
