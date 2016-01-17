package kp.edwd.nekst.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ClickInfo {
    private Date date;
    private String userId;

    public ClickInfo(String clickItem) throws ParseException {
        String[] clickInfo = clickItem.split("\\|");
        date = DateFormat.getDateTimeInstance().parse(clickInfo[0]);
        userId = clickInfo[1];
    }
}
