package core.basesyntax.WorkWithFileTest;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.WorkWithFile.DataConverter;
import core.basesyntax.WorkWithFile.DataConverterImpl;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    private static DataConverter converter;

    @BeforeAll
    public static void setUp() {
        converter = new DataConverterImpl();
    }

    @Test
    public void testConvert_Ok() {
        FruitTransaction expected = new FruitTransaction(FruitTransaction.Operation.BALANCE
                , new Fruit("apple"), 500);
        FruitTransaction actual = converter.Convert("b,apple,500");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testConvert_NotOk() {
        Assertions.assertThrows(RuntimeException.class, () -> converter.Convert("there,is,problem"));
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
