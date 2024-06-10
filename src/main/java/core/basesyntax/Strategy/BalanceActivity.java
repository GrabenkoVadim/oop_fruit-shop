package core.basesyntax.Strategy;

import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;

public class BalanceActivity implements OperationHandler{

    @Override
    public void process(FruitTransaction fruitTransaction) {
        Storage.fruits.compute(fruitTransaction.getFruit(), (k, quantity) -> quantity == null
                ? fruitTransaction.getQuantity() :
                fruitTransaction.getQuantity() + quantity);
    }
}
