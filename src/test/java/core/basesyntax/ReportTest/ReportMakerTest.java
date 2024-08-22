package core.basesyntax.ReportTest;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Report.ReportMaker;
import core.basesyntax.Report.ReportMakerImpl;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.*;

class ReportMakerTest {
    private static ReportMaker reportMaker;

    @BeforeAll
    public static void setUp() {
        reportMaker = new ReportMakerImpl();
    }

    @BeforeEach
    public void InitializeStorage() {
        Storage.fruits.put(new Fruit("Apple"), 22);
        Storage.fruits.put(new Fruit("Banana"), 44);
        Storage.fruits.put(new Fruit("Grape"), 66);
        Storage.fruits.put(new Fruit("Orange"), 80);
        Storage.fruits.put(new Fruit("Watermelon"), 90);
    }

    @Test
    public void makeReport() {
        String expected = "fruit, quantity"
                + System.lineSeparator()
                + "Apple, 22"
                + System.lineSeparator()
                + "Watermelon, 90"
                + System.lineSeparator()
                + "Grape, 66"
                + System.lineSeparator()
                + "Banana, 44"
                + System.lineSeparator()
                + "Orange, 80";
        Assertions.assertEquals(expected, reportMaker.makeReport());
    }

    @AfterEach
    public void ClearStorage() {
        Storage.fruits.clear();
    }
}
