package core.basesyntax.Strategy;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SupplyActivityHandlerTest {
    private static OperationHandler supplyActivityHandler;

    @BeforeAll
    public static void setUp() {
        supplyActivityHandler = new BalanceActivity();
    }

    @Test
    public void process_emptyStorage_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.SUPPLY,
                new Fruit("banana"), 150);
        supplyActivityHandler.process(fruitTransaction);
        Integer expectedResult = 150;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @Test
    public void process_Ok() {
        Storage.fruits.put(new Fruit("banana"), 104);
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.SUPPLY, new Fruit("banana"), 521);
        supplyActivityHandler.process(fruitTransaction);
        Integer expectedResult = 625;
        Integer actual = Storage.fruits.get(fruitTransaction.getFruit());
        Assertions.assertEquals(expectedResult, actual);
    }

    @AfterEach
    public void clearStorage() {
        Storage.fruits.clear();
    }
}
