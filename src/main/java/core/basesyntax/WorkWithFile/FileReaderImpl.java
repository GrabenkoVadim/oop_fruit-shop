package core.basesyntax.WorkWithFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderImpl implements FileReader {

    @Override
    public List<String> readFile(String fileName) {
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            List<String> list = new ArrayList<>();
            String reader = br.readLine();
            while (reader != null) {
                list.add(reader);
                reader = br.readLine();
            }
            return list;
        }
        catch(IOException e){
            throw new RuntimeException("Can't read data from file" ,e);
        }
    }
}
