package core.basesyntax;

import core.basesyntax.Model.FruitTransaction;
import core.basesyntax.Strategy.*;
import core.basesyntax.WorkWithFile.*;
import core.basesyntax.Report.ReportMaker;
import core.basesyntax.Report.ReportMakerImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static String INPUT_FILE_NAME = "src/main/resources/input.csv";;
    public static String OUTPUT_FILE_NAME = "src/main/resources/output.csv";;

    public static void main(String[] args) {
        FileReader fileReader = new FileReaderImpl();
        List<String> inputRecord = fileReader.readFile(INPUT_FILE_NAME);
        DataConverter dataConverter = new DataConverterImpl();
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceActivity());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyActivity());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnActivity());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseActivity());
        for (String transaction : inputRecord) {
            FruitTransaction fruitTransaction = dataConverter.Convert(transaction);
            operationHandlers.get(fruitTransaction.getOperation()).process(fruitTransaction);
        }
        ReportMaker reportMaker = new ReportMakerImpl();
        String resultingReport = reportMaker.makeReport();

        FileWriter fileWriter = new FileWriterImpl();
        fileWriter.writeFile(OUTPUT_FILE_NAME, resultingReport);
    }
}