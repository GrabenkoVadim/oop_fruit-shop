package core.basesyntax.WorkWithFileTest;

import core.basesyntax.Model.Fruit;
import core.basesyntax.WorkWithFile.FileWriter;
import core.basesyntax.WorkWithFile.FileWriterImpl;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileWriterTest {
    public static final String OUTPUT_FILE_PATH = "src/test/resources/output.csv";
    private static FileWriter fileWriter;

    @BeforeAll
    public static void setUp() {
        fileWriter = new FileWriterImpl();
    }

    @BeforeEach
    public void InitializeStorage() {
        Storage.fruits.put(new Fruit("Watermelon"), 100);
        Storage.fruits.put(new Fruit("Grapefruit"), 200);
        Storage.fruits.put(new Fruit("Apple"), 300);
    }

    @Test
    void writeToFile_Ok() {
        String textToWrite = "fruit,quantity"
                + System.lineSeparator()
                + "Watermelon,100"
                + System.lineSeparator()
                + "Grapefruit,200"
                + System.lineSeparator()
                + "Apple,300";
        fileWriter.writeFile(OUTPUT_FILE_PATH, textToWrite);
        List<String> expected = new ArrayList<>();
        expected.add("fruit,quantity");
        expected.add("Watermelon,100");
        expected.add("Grapefruit,200");
        expected.add("Apple,300");
        List<String> actual = readFile();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void writeToFile_NotOk() {
        String textToWrite = "fruit, quantity"
                + System.lineSeparator()
                + "Watermelon,100"
                + System.lineSeparator()
                + "Grapefruit,200"
                + System.lineSeparator()
                + "Apple,300";
        fileWriter.writeFile("Oops...", textToWrite);
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }

    private List<String> readFile() {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(FileWriterTest.OUTPUT_FILE_PATH))) {
            List<String> list = new ArrayList<>();
            String reader = br.readLine();
            while (reader != null) {
                list.add(reader);
                reader = br.readLine();
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }
}
