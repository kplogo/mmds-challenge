package kp.edwd.nekst.model;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Krzysztof.Pawlak on 2016-01-17.
 */
public class Query {
    private final int queryId;
    private final String queryText;
    private final List<ResponseRow> responseRowList = new ArrayList<>();
    private int rowNumber = 0;

    public Query(int queryId, String queryText) {

        this.queryId = queryId;
        this.queryText = queryText;
    }

    @Override
    public String toString() {
        return queryText;
    }

    public List<ResponseRow> getResponseRowList() {
        return responseRowList;
    }

    public List<ResponseRow> getClicked() {
        List<ResponseRow> result = new ArrayList<>();
        for (ResponseRow responseRow : responseRowList) {
            if (responseRow.isClicked()) {
                result.add(responseRow);
            }
        }
        return result;
    }

    public int nextRowNumber() {
        return rowNumber++;
    }
}
