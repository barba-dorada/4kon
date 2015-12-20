import org.json.JSONArray;
import org.json.JSONObject;
import ru.cwl.kon.model.Fact;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vad on 18.12.2015 21:54
 * 4kon
 */
/*
1.брать файл из ресурсов.
2.вывод в JSON
сделал примитивный вывод в json через org.json
GSON,org.json
3.простенький rest сервис.
 */
public class ReadFaktsFromCsv {
    public static void main(String[] args) {
        List<Fact> facts = run();
        for (Fact fact : facts) {
            System.out.printf("%s\n", fact);
        }
        JSONArray jsonArray=new JSONArray();
        for (Fact fact : facts) {
            JSONObject o = new JSONObject(fact);
            jsonArray.put(o);
        }
        System.out.println(jsonArray.toString());
    }

    static public List<Fact> run() {
        // TODO в рескрсы
        String csvFile = "C:\\dev\\proj\\4kon\\src\\main\\resources\\data\\fakts.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int count = 0;
        List<Fact> facts = new ArrayList<Fact>();
        DateFormat df=new SimpleDateFormat("dd.MM.yyyy");

        try {

            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(csvFile), "Cp1251"));
            br.readLine();
            //br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                count++;
                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                if (country[0].equals("")) break;

                Fact fact = new Fact();
                fact.setDate(df.parse(country[0]));
                fact.setId(count);
                fact.setAccount(country[1]);
                fact.setAmount(Double.parseDouble(country[2].replace(",", ".")));
                fact.setDescr1(country[3]);
                fact.setDescr1(country[4]);
                fact.setSubTotal(Double.parseDouble(country[8].replace(",", ".")));

                facts.add(fact);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return facts;
    }

}
