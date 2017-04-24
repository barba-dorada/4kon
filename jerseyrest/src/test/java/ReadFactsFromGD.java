import ru.cwl.kon.model.Fact;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adm on 09.07.2016.
 * вытягивание из инета для сверки работы отчетов сводных
 */
public class ReadFactsFromGD {
    //final static String src="https://docs.google.com/spreadsheets/d/1EmCA5qbW0VnT09QwskgBag-jO4O4rDzYQlHRlHXnMpk/pub?gid=0&single=true&output=tsv";
//    final static String src="https://docs.google.com/spreadsheets/d/1osb5cKizoLCfwQEH9m5upMkl0advMd-aMXYREPBM5Ac/pub?gid=0&single=true&output=tsv";
    final static String src= "https://docs.google.com/spreadsheets/d/1osb5cKizoLCfwQEH9m5upMkl0advMd-aMXYREPBM5Ac/pub?gid=0&single=true&output=tsv";

    public static void main(String[] args) throws IOException {
        URL url = new URL(src);
        URLConnection conn = url.openConnection();
        // open the stream and put it into BufferedReader
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        int i=0;
        List<Fact> facts=new ArrayList<>();
        br.readLine();
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
//            System.out.printf("%s %s %s %s {%s}{%s}{%s}\n", userChar,dateStr,accStr, subc,amount,description,subTotal);

            Fact f = new Fact();
            f.setId(++i);
            f.setUser(userChar);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


            LocalDate localDate = LocalDate.parse(dateStr, formatter);
            f.setDate(localDate);

            f.setAccount(accStr);
            f.setAmount(new BigDecimal(amount));
            f.setDescription(description);
            f.setCategory(subc);
//            f.setInserted(LocalDateTime.now());
            facts.add(f);
        }
        System.out.println("END");
        br.close();
        // Create Jsonb and serialize
        JsonbConfig config
                //.withAdapters(new AnimalAdapter())
                ;
        config = new JsonbConfig().withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);

        String result = jsonb.toJson(facts);
        System.out.println(result);

// Deserialize back
        //dogs = jsonb.fromJson(result, new ArrayList<Dog>(){}.getClass());
        //System.out.println(facts);

    }
}

