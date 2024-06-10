package core.basesyntax.Strategy;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PurchaseActivityHandlerTest {
    private static OperationHandler purchaseActivityHandler;

    @BeforeAll
    public static void setUp() {
        purchaseActivityHandler = new PurchaseActivity();
    }

    @Test()
    public void process_QuantityLessThanCurrent_NotOk() {
        Storage.fruits.put(new Fruit("banana"), 10);
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.PURCHASE,
                new Fruit("banana"), 15);
        Assertions.assertThrows(RuntimeException.class, () -> purchaseActivityHandler.process(fruitTransaction));
    }

    @Test
    public void process_Ok() {
        Storage.fruits.put(new Fruit("orange"), 145);
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.PURCHASE, new Fruit("orange"), 123);
        purchaseActivityHandler.process(fruitTransaction);
        Integer expectedResult = 22;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
