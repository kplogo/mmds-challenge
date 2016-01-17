package kp.edwd.nekst.service;

import kp.edwd.nekst.model.Query;
import kp.edwd.nekst.model.ResponseRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseService {
    private Map<Integer, Query> queryMap = new HashMap<>();
    private List<ResponseRow> database = new ArrayList<>();

    public DatabaseService() throws IOException, ParseException {
        readFile("data/nekst-train.csv");

    }

    private void readFile(String fileName) throws IOException, ParseException {

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

                database.add(new ResponseRow(query, "1".equals(split[1]), split[2], split[3], url, title, abstractText));
                line = br.readLine();
            }
        }
    }

    public Map<Integer,Query> getQuery() {
        return queryMap;
    }
}
