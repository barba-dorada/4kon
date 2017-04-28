package ru.cwl.dao.util;

import ru.cwl.dao.FactDao;
import ru.cwl.kon.model.Fact;
import ru.cwl.kon.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by vad on 24.01.2016 20:58
 * 4kon
 */
public class TestUtils {

    public static void loadFactsFromCsvFile(FactDao dao) {
        String line = "";
        String cvsSplitBy = ";";
        int count = 0;
        InputStream is = TestUtils.class.getResourceAsStream("/data/fakts.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "Cp1251"))) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            br.readLine();
            while ((line = br.readLine()) != null) {
                count++;
                // use comma as separator
                String[] src = line.split(cvsSplitBy);

                if (src[0].equals("")) break;

                Fact fact = new Fact();

                LocalDate localDate = LocalDate.parse(src[0], formatter);
                fact.setDate(localDate);
                fact.setCategory(src[4]);
                fact.setId(0);
                fact.setAccount(src[1]);
                fact.setAmount(new BigDecimal(src[2].replace(",", ".")));
                fact.setDescription(src[3]);
                //fact.setDescr1(src[4]);
                //fact.setSubTotal(Double.parseDouble(src[8].replace(",", ".")));
                fact.setUser("user");

                dao.save(fact);

            }

       /* } catch (FileNotFoundException e) {
            e.printStackTrace();*/
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
