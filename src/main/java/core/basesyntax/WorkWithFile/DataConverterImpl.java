package core.basesyntax.WorkWithFile;

import core.basesyntax.Model.Fruit;
import core.basesyntax.Model.FruitTransaction;

public class DataConverterImpl implements DataConverter {
    private static final int OPERATION = 0;
    private static final int FRUIT_NAME = 1;
    private static final int QUANTITY = 2;


    @Override
    public FruitTransaction Convert(String InputRecord) {
        String[] convertToTransaction = InputRecord.split(",");
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getOperation().equals(convertToTransaction[OPERATION])) {
                return new FruitTransaction(operation,
                        new Fruit(convertToTransaction[FRUIT_NAME]),
                        Integer.parseInt(convertToTransaction[QUANTITY]));
            }
        }
        throw new RuntimeException("Can't convert " + InputRecord + " to FruitTransaction");
    }
}
