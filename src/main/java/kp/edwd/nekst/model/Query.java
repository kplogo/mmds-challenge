package kp.edwd.nekst.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public String toFileExport() {
        StringBuilder sb = new StringBuilder();
        for (ResponseRow responseRow : responseRowList) {
            sb.append(responseRow.isClicked() ? 1 : 0).append(" ");
            sb.append("qid:").append(queryId).append(" ");
            for (ParameterName parameterName : ParameterName.values()) {
                sb.append(parameterName.ordinal() + 1)
                        .append(":")
                        .append(String.format(Locale.ENGLISH, "%.4f", responseRow.getParams().get(parameterName)))
                        .append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
