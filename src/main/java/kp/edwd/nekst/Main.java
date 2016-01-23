package kp.edwd.nekst;

import ciir.umass.edu.eval.Evaluator;
import kp.edwd.nekst.service.DatabaseService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Main {
    public static void main(String attrs[]) throws IOException, ParseException {
        DatabaseService databaseService = new DatabaseService();
        System.out.println("Zakończono parsowanie pliku");
//        trainModel(databaseService);
        testModel(databaseService);
        System.out.println("Zakończono program");
    }

    private static void testModel(DatabaseService databaseService) throws IOException, ParseException {
        String query;
        databaseService.saveTestFiles(10, -1, -1);
        query = "-load mymodel.txt" +
//                " -rank test-train.txt" +
                " -rank train-test.txt" +
                " -score myscorefile.txt";
        Evaluator.main(query.split(" "));
        Result result = new Result("myscorefile.txt");
        result.saveToFile("solution.csv");
    }

    private static void trainModel(DatabaseService databaseService) throws FileNotFoundException, UnsupportedEncodingException {
        databaseService.saveTrainFiles(10, 1, 2);
        String query = "-train train-train.txt" +
                " -test train-test.txt" +
                " -kcv 10" +
                " -validate train-validate.txt" +
                " -ranker 6" +
                " -leaf 10" +
                " -metric2t MAP" +
                " -metric2T MAP" +
                " -save mymodel.txt";
        Evaluator.main(query.split(" "));
    }
}
