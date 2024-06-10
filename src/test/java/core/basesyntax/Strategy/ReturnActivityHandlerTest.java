package core.basesyntax.Strategy;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReturnActivityHandlerTest {
    private static OperationHandler returnActivityHandler;

    @BeforeAll
    public static void setUp() {
        returnActivityHandler = new ReturnActivity();
    }

    @Test
    public void process_emptyStorage_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.RETURN,
                new Fruit("banana"), 30);
        returnActivityHandler.process(fruitTransaction);
        Integer expectedResult = 30;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @Test
    public void process_Ok() {
        Storage.fruits.put(new Fruit("banana"), 14);
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.RETURN, new Fruit("banana"), 24);
        returnActivityHandler.process(fruitTransaction);
        Integer expectedResult = 38;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
