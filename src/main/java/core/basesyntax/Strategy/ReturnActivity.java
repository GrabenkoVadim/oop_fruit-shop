package core.basesyntax.Strategy;

import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;

public class ReturnActivity implements OperationHandler {
    @Override
    public void process(FruitTransaction fruitTransaction) {
        Storage.fruits.compute(fruitTransaction.getFruit(), (k, quantity) -> quantity == null
                ? fruitTransaction.getQuantity()
                : quantity + fruitTransaction.getQuantity());
    }
}
