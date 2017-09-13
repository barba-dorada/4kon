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

        //testLera(factsList);
        testVad(factsList, Month.AUGUST);


    }

    private static void testVad(List<GHRow> factsList, Month month) throws IOException {

        List<GHRow> rrr = factsList.stream()
                .filter(r -> r.acc.equalsIgnoreCase("в.тинькоф"))
                .filter(r -> r.date.getMonth() == month)
                .collect(toList());

        List<Row> res = getRowsFromCsv("/csv/v/operations Fri Dec 09 00_00_00 MSK 2016-Sat Sep 09 22_00_51 MSK 2017.csv");

        List<Row> res2 = res.stream()
                .filter(r -> r.getOperationDate().getMonth() == month)
                .filter(r->r.getStatus().equals("OK"))
                .filter(r->r.getCardNumber().equals("*9819") || r.getCardNumber().equals(""))
                .collect(toList());

        ArrayList<GHRow> a = new ArrayList<GHRow>(rrr);
        ArrayList<Row> b = new ArrayList<Row>(res2);

        merge(a, b);

        BigDecimal c1 = print(b);
        BigDecimal c2 = print2(a);
        System.out.printf("c1-c2: %s",c1.subtract(c2));

    }

    private static BigDecimal print2(ArrayList<GHRow> a) {
        // TODO: 13.09.2017 добавить печать заготовков
        // TODO: 13.09.2017 Заменить 2 процедуры одной
        System.out.println("========================");
        BigDecimal c2=BigDecimal.ZERO;

        for (GHRow row : a) {
            System.out.println(row);
            c2=c2.add(row.sum);

        }
        System.out.println(c2);
        return c2;
    }

    private static BigDecimal print(ArrayList<Row> b) {
        System.out.println("========================");
        BigDecimal c1=BigDecimal.ZERO;
        for (Row row : b) {
            System.out.println(row);
            c1=c1.add(row.getSum());
        }
        System.out.println(c1);
        return c1;
    }

    private static void merge(ArrayList<GHRow> a, ArrayList<Row> b) {



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


 //       "обед","Рестораны","J HOUSE LTD";
//       "перевод" "Наличные"


    }

    private static List<Row> getRowsFromCsv(String fileName) throws IOException {
        final Reader reader = new InputStreamReader(Quickstart.class.getResourceAsStream(fileName), "windows-1251");

        CsvReader csvReader = new CsvReader();
        return csvReader.read(reader);
    }

    private static void testLera(List<GHRow> factsList) throws IOException {
    /*
            for (GHRow row : factsList) {
                System.out.println(row);
            }*/

        // Лера тинькоф август
        Month month = Month.AUGUST;
        testVad(factsList, month);


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
                if(value.get(4) instanceof BigDecimal) {
                    r.sum = (BigDecimal) value.get(4);
                }
                r.description = value.get(5).toString();
                r.sumAfter = (BigDecimal) value.get(6);
                r.sumAfter = r.sumAfter.setScale(2, BigDecimal.ROUND_HALF_UP);
                r.month = value.get(7).toString();

                result.add(r);
            } catch (Exception e) {
                System.err.printf("%s %s", count, value);
                e.printStackTrace();
            }
            count++;

        }
        return result;
    }


}
