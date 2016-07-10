import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by adm on 09.07.2016.
 * вытягивание из инета для сверки работы отчетов сводных
 */
public class ReadFactsFromGD {
    final static String src="https://docs.google.com/spreadsheets/d/1EmCA5qbW0VnT09QwskgBag-jO4O4rDzYQlHRlHXnMpk/pub?gid=0&single=true&output=tsv";

    public static void main(String[] args) throws IOException {
        URL url = new URL(src);
        URLConnection conn = url.openConnection();
        // open the stream and put it into BufferedReader
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            String[] a = inputLine.split("\\t");

            if("".equals(a[0])){
                break;
            }
            String userChar = a[0];
            String dateStr=a[1];
            String accStr=a[2];
            String subc = a[3];
            String amount=a[4].replaceAll(" ","").replace(',','.');
            String description=a[5];
            String subTotal=a[6].replaceAll(" ","").replace(',','.');
            System.out.printf("%s %s %s %s {%s}{%s}{%s}\n", userChar,dateStr,accStr, subc,amount,description,subTotal);
        }
        System.out.println("END");
        br.close();

    }
}

