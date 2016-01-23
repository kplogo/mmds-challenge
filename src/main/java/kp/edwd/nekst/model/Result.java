package kp.edwd.nekst.model;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Result {
    private final Map<Integer, List<ResultItem>> map;
    public Result(String filename) throws IOException, ParseException {
        map = readFile(filename);
        for (Map.Entry<Integer, List<ResultItem>> entry : map.entrySet()) {
            try {
                Collections.sort(entry.getValue());
            } catch (Exception ex) {
                ex.printStackTrace();
                Collections.sort(entry.getValue());
            }
        }
    }

    private Map<Integer, List<ResultItem>> readFile(String fileName) throws IOException, ParseException {
        Map<Integer, List<ResultItem>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();

            while (line != null) {
                String[] split = line.split("\t");
                int queryId = Integer.parseInt(split[0]);
                int position = Integer.parseInt(split[1]);
                double value = Double.parseDouble(split[2]);
                List<ResultItem> list = map.get(queryId);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(queryId, list);
                }
                list.add(new ResultItem(position+1, value));
                line = br.readLine();
            }
        }
        return map;
    }

    public void saveToFile(String solution) throws FileNotFoundException, UnsupportedEncodingException {
        if (map == null || map.isEmpty()) {
            return;
        }
        PrintWriter writer = new PrintWriter(solution, "UTF-8");
        writer.write("Zapytanie,Dokumenty\n");
        for (Map.Entry<Integer, List<ResultItem>> entry : map.entrySet()) {
            writer.write(entry.getKey() + ",");
            for (int i = 0; i < Math.min(5,entry.getValue().size()); i++) {
                writer.print(entry.getValue().get(i));
            }
            writer.println();
        }
        writer.close();
    }
}
