package core.basesyntax.WorkWithFile;

import core.basesyntax.Model.FruitTransaction;

public interface DataConverter {
    FruitTransaction Convert(String StringName);
}
