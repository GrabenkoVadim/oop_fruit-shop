package core.basesyntax.Strategy;

import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.db.Storage;

public class PurchaseActivity implements OperationHandler{
    @Override
    public void process(FruitTransaction fruitTransaction) {
        Integer quantity = Storage.fruits.get(fruitTransaction.getFruit());
        if (quantity == null || quantity < fruitTransaction.getQuantity()) {
            throw new RuntimeException("Not enough fruits");
        }
        Storage.fruits.put(fruitTransaction.getFruit(),  quantity - fruitTransaction.getQuantity());
    }
}
