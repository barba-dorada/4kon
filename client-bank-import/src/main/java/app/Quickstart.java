package app;

/**
 * Created by vadim.tishenko
 * on 10.09.2017 22:58.
 */


import gsimport.GHRow;
import gsimport.GSheetConnector;
import tinkoff.CsvReader;
import tinkoff.Row;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Quickstart {

    public static void main(String[] args) throws IOException {

        GSheetConnector conn = new GSheetConnector();
        String spreadsheetId = "1osb5cKizoLCfwQEH9m5upMkl0advMd-aMXYREPBM5Ac";

        GSheetConnector.Sheet1 s = conn.getSpreadSheet(spreadsheetId);

        List<List<Object>> values = s.readRange("факты!A2:H");//response.getValues();
        List<GHRow> factsList = readAndExtractFacts(values);


/*
        for (GHRow row : factsList) {
            System.out.println(row);
        }*/

        // Лера тинькоф август
        Month month = Month.APRIL;
        List<GHRow> rrr = factsList.stream()
                .filter(r -> r.acc.equalsIgnoreCase("л.тинькоф"))
                .filter(r -> r.date.getMonth() == month)
                .collect(toList());

        for (GHRow row : rrr) {
            System.out.println(row);
        }

        String fileName = "/csv/l/operations Tue Mar 14 15_33_28 MSK 2017-Fri Sep 08 19_18_45 MSK 2017.csv";
        final Reader reader = new InputStreamReader(Quickstart.class.getResourceAsStream(fileName), "windows-1251");

        CsvReader csvReader = new CsvReader();
        List<Row> res = csvReader.read(reader);

        List<Row> res2 = res.stream()
                .filter(r -> r.getOperationDate().getMonth() == month)
                .filter(r->r.getStatus().equals("OK"))
                .collect(toList());

        for (Row re : res2) {
            System.out.println(re);
        }

        ArrayList<GHRow> a = new ArrayList(rrr);
        ArrayList<Row> b = new ArrayList<Row>(res2);


        Iterator<Row> i = b.iterator();
        while(i.hasNext()){
            Row r = i.next();

            Iterator<GHRow> i2 = a.iterator();
            while (i2.hasNext()) {
                GHRow ghRow = i2.next();
                if(r.getSum().compareTo(ghRow.sum)==0){
                    i.remove();
                    i2.remove();
                    break;
                }
            }
        }

        System.out.println("========================");
        BigDecimal c1=BigDecimal.ZERO;
        for (Row row : b) {
            System.out.println(row);
            c1=c1.add(row.getSum());
        }
        System.out.println(c1);


        System.out.println("========================");
        BigDecimal c2=BigDecimal.ZERO;

        for (GHRow row : a) {
            System.out.println(row);
            c2=c2.add(row.sum);

        }
        System.out.println(c2);
        System.out.printf("c1-c2: %s",c1.subtract(c2));


       /* values = s.readRange("'остатки'!B2:C13");

        int n = 2;
        System.out.println("ост");
        for (List row : values) {
            System.out.printf("%s, %s\n", n, row.toString());
            n++;
        }*/
    }

    private static List<GHRow> readAndExtractFacts(List<List<Object>> values) {

        LocalDate z0 = LocalDate.of(1899, 12, 30);

        ArrayList<GHRow> result = new ArrayList<>();
        int count = 2;
        for (List<Object> value : values) {

            try {
                String user = (String) value.get(0);
                if (user.length() != 1) continue;

                GHRow r = new GHRow();
                r.user = user;

                Object rv = value.get(1);
                if (rv != null && rv instanceof BigDecimal) {
                    LocalDate d = z0.plusDays(((BigDecimal) rv).longValue());
                    r.date = d;
                }
                r.acc = (String) value.get(2);
                r.sunc = (String) value.get(3);
                r.sum = (BigDecimal) value.get(4);

                r.description = value.get(5).toString();
                r.sumAfter = (BigDecimal) value.get(6);
                r.sumAfter = r.sumAfter.setScale(2, BigDecimal.ROUND_HALF_UP);
                r.month = value.get(7).toString();

                result.add(r);
            } catch (Exception e) {
                System.out.printf("%s %s", count, value);
                e.printStackTrace();
            }
            count++;

        }
        return result;
    }


}
