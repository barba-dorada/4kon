package tinkoff;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by vadim.tishenko
 * on 10.09.2017 14:08.
 */
public class ReadCSV {
    public static void main(String[] args) throws IOException {
        String fileName2 ="/csv/v/operations Fri Dec 09 00_00_00 MSK 2016-Sat Sep 09 22_00_51 MSK 2017.csv";

        String fileName = "/csv/v/operations Thu Aug 10 12_36_54 MSK 2017-Sat Sep 09 22_00_51 MSK 2017.csv";

        dump(fileName);
        dump(fileName2);

        String f1 = "/csv/l/operations Thu Aug 10 15_55_48 MSK 2017-Fri Sep 08 19_18_45 MSK 2017.csv";
        String f2 = "/csv/l/operations Tue Mar 14 15_33_28 MSK 2017-Fri Sep 08 19_18_45 MSK 2017.csv";
        dump(f1);
        dump(f2);

    }

    private static void dump(String fileName) throws IOException {
        final Reader reader = new InputStreamReader(ReadCSV.class.getResourceAsStream(fileName), "windows-1251");

        CsvReader csvReader = new CsvReader();
        List<Row> res = csvReader.read(reader);

        for (Row re : res) {
            System.out.println(re);
        }
    }
}
