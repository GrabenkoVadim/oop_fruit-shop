package core.basesyntax.Report;

import core.basesyntax.Model.Fruit;
import core.basesyntax.db.Storage;
import java.util.Map;

public class ReportMakerImpl implements ReportMaker {
    @Override
    public String makeReport() {
        StringBuilder stringBuilder = new StringBuilder("fruit, quantity");
        for (Map.Entry<Fruit, Integer> entry : Storage.fruits.entrySet()) {
            stringBuilder.append(System.lineSeparator())
                    .append(entry.getKey().getName())
                    .append(", ")
                    .append(entry.getValue().toString());
        }
        return stringBuilder.toString();
    }
}
