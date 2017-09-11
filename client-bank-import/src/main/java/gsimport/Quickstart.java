package gsimport;

/**
 * Created by vadim.tishenko
 * on 10.09.2017 22:58.
 */


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Quickstart {

    public static void main(String[] args) throws IOException {

        GSheetConnector conn = new GSheetConnector();
        String spreadsheetId = "1osb5cKizoLCfwQEH9m5upMkl0advMd-aMXYREPBM5Ac";

        GSheetConnector.Sheet1 s = conn.getSpreadSheet(spreadsheetId);

        List<List<Object>> values = s.readRange("факты!A2:H");//response.getValues();
        List<GHRow> factsList = readAndExtractFacts(values);

        for (GHRow row : factsList) {
            System.out.println(row);
        }

        values=s.readRange("'остатки'!B2:C13");

        int n = 2;
        System.out.println("ост");
        for (List row : values) {
            System.out.printf("%s, %s\n", n, row.toString());
            n++;
        }
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

                r.description=  value.get(5).toString();
                r.sumAfter=(BigDecimal) value.get(6);
                r.sumAfter=r.sumAfter.setScale(2,BigDecimal.ROUND_HALF_UP);
                r.month=value.get(7).toString();

                result.add(r);
            } catch (Exception e) {
                System.out.printf("%s %s",count,value);
                e.printStackTrace();
            }
            count++;

        }
        return result;
    }


}
