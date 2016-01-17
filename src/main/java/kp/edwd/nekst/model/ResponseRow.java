package kp.edwd.nekst.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseRow {
    private final Query query;
    private final boolean clicked;
    private final int rowNumber;
    private Map<ParameterName, Double> params;
    private List<ClickInfo> clickedParams;
    private final String url;
    private final String title;
    private final String abstractText;

    public ResponseRow(Query query, boolean clicked, String params, String clickedParams, String url, String title, String abstractText) throws ParseException {
        this.query = query;
        this.clicked = clicked;
        rowNumber = query.nextRowNumber();
        parseItemParameters(params);
        parseClickedParams(clickedParams);
        this.url = url;
        this.title = title;
        this.abstractText = abstractText;
        query.getResponseRowList().add(this);
    }

    private void parseClickedParams(String clickedParams) throws ParseException {
        this.clickedParams = new ArrayList<>();
        if (clickedParams == null || clickedParams.isEmpty()) {
            return;
        }
        String[] clickedList = clickedParams.split(";");
        for (String clickItem : clickedList) {
            this.clickedParams.add(new ClickInfo(clickItem));
        }
    }

    public void parseItemParameters(String paramsString) {
        params = new HashMap<>();
        paramsString = paramsString.substring(1, paramsString.length() - 1);
        String[] split = paramsString.split(",");
        ParameterName[] parameterNames = ParameterName.values();
        for (int i = 0; i < split.length; i++) {
            double value = Double.parseDouble(split[i]);
            params.put(parameterNames[i], value);
        }

    }

    public boolean isClicked() {
        return clicked;
    }

    public int getClickCount() {
        return clickedParams.size();
    }

    @Override
    public String toString() {
        return "Na miejsu " + rowNumber + " kliknięto " + getClickCount() + " razy: " + url + " --- " + title;
    }
}
