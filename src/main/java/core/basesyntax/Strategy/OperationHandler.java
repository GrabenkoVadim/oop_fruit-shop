package core.basesyntax.Strategy;

import core.basesyntax.Model.FruitTransaction;

public interface OperationHandler {
    void process(FruitTransaction fruitTransaction);
}
