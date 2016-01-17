package kp.edwd.nekst;

import kp.edwd.nekst.model.Query;
import kp.edwd.nekst.model.ResponseRow;
import kp.edwd.nekst.service.DatabaseService;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String attrs[]) throws IOException, ParseException {
        DatabaseService databaseService = new DatabaseService();
        System.out.println("Zakończono parsowanie pliku");
        for (Query query : databaseService.getQuery().values()) {
            System.out.println("Kliknięte dla zapytania: " + query);
            for (ResponseRow responseRow : query.getClicked()) {
                System.out.println(responseRow);
            }
            System.out.println("-----");
        }
        System.out.println("Zakończono program");
    }
}
