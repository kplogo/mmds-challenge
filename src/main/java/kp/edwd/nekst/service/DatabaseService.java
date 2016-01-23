package kp.edwd.nekst.service;

import kp.edwd.nekst.model.Query;
import kp.edwd.nekst.model.ResponseRow;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseService {
    private Map<Integer, Query> trainQueryMap = new HashMap<>();
    private Map<Integer, Query> testQueryMap = new HashMap<>();

    public DatabaseService() throws IOException, ParseException {
        readFile("data/train.csv", trainQueryMap);
        readFile("data/test.csv", testQueryMap);
    }

    private void readFile(String fileName, Map<Integer, Query> queryMap) throws IOException, ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            while (line != null) {
                String[] split = line.split("::::");
                int queryId = Integer.parseInt(split[0]);
                Query query = queryMap.get(queryId);
                if (query == null) {
                    query = new Query(queryId, split[4]);
                    queryMap.put(queryId, query);
                }
                String abstractText = null;
                String title = null;
                String url = null;
                if (split.length > 6) {
                    title = split[6];
                }

                if (split.length > 7) {
                    abstractText = split[7];
                }
                if (split.length > 5) {
                    url = split[5];
                }
                query.getResponseRowList().add(new ResponseRow(query, "1".equals(split[1]), split[2], split[3], url, title, abstractText));
                line = br.readLine();
            }
        }
    }

    public Map<Integer, Query> getQuery() {
        return trainQueryMap;
    }

    public void saveTrainFiles(int partsCount, int validationPart, int testPart) throws FileNotFoundException, UnsupportedEncodingException {
        saveFiles(trainQueryMap, "train", partsCount, validationPart, testPart);
    }

    public void saveTestFiles(int partsCount, int validationPart, int testPart) throws FileNotFoundException, UnsupportedEncodingException {
        saveFiles(testQueryMap, "test", partsCount, validationPart, testPart);
    }

    private void saveFiles(Map<Integer, Query> queryMap, String prefix, int partsCount, int validationPart, int testPart) throws FileNotFoundException, UnsupportedEncodingException {
        int size = queryMap.size() / partsCount;
        List<Query> train = new ArrayList<>();
        List<Query> test = new ArrayList<>();
        List<Query> validate = new ArrayList<>();
        int i = 0;
        for (Query query : queryMap.values()) {
            if (i > validationPart * size && i < (validationPart + 1) * size) {
                validate.add(query);
            } else if (i > testPart * size && i < (testPart + 1) * size) {
                test.add(query);
            } else {
                train.add(query);
            }
            i++;
        }
        saveToFile(prefix + "-train.txt", train);
        saveToFile(prefix + "-validate.txt", validate);
        saveToFile(prefix + "-test.txt", test);
    }

    private void saveToFile(String filename, List<Query> queries) throws FileNotFoundException, UnsupportedEncodingException {
        if (queries == null || queries.isEmpty()) {
            return;
        }
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Query query : queries) {
            writer.print(query.toFileExport());
        }
        writer.close();
    }
}
