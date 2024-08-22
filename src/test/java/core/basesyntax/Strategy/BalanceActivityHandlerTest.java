package core.basesyntax.Strategy;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BalanceActivityHandlerTest {
    private static OperationHandler balanceActivityHandler;

    @BeforeAll
    public static void setUp() {
        balanceActivityHandler = new BalanceActivity();
    }

    @Test
    public void process_emptyStorage_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.BALANCE,
                new Fruit("banana"), 15);
        balanceActivityHandler.process(fruitTransaction);
        Integer expectedResult = 15;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @Test
    public void process_Ok() {
        Storage.fruits.put(new Fruit("banana"), 14);
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.BALANCE, new Fruit("banana"), 13);
        balanceActivityHandler.process(fruitTransaction);
        Integer expectedResult = 27;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @Test
    public void process_NegativeQuantity_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.BALANCE, new Fruit("banana"), -15);
        balanceActivityHandler.process(fruitTransaction);
        Integer expectedResult = -15;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
