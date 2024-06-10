package core.basesyntax.WorkWithFile;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterImpl implements FileWriter {
    @Override
    public void writeFile(String fileName, String content) {
        try(BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileName))) {
            bw.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file.", e);
        }
    }
}
