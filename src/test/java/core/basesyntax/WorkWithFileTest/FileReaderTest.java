package core.basesyntax.WorkWithFileTest;

import core.basesyntax.WorkWithFile.FileReader;
import core.basesyntax.WorkWithFile.FileReaderImpl;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FileReaderTest {
    public static final String INPUT_FILE_PATH = "src/test/resources/fruits.csv";
    private static FileReader fileReader;

    @BeforeAll
    public static void setUp() {
        fileReader = new FileReaderImpl();
    }

    @Test
    public void testReadFile_Ok() {
        List<String> expected = new ArrayList<>();
        expected.add("b,grape,25");
        expected.add("b,pineapple,15");
        expected.add("b,pineapple,10");
        Assertions.assertEquals(expected, fileReader.readFile(INPUT_FILE_PATH));
    }

    @Test
    public void testReadFile_NotOk() {
        Assertions.assertThrows(RuntimeException.class, () -> fileReader.readFile("IncorrectFile.csv"));
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
