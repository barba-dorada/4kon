package ru.cwl.kon.dao;

import ru.cwl.kon.model.Fact;
import ru.cwl.kon.model.User;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by vad on 24.01.2016 20:58
 * 4kon
 */
public class TestUtils {
    static User user = new User(1, "vad", "12345", "vadim");


    public static void loadFactsFromCsvFile(FactsDao dao, User user) {
        //TODO from resource! relative path!
        String fileName = "C:\\dev\\proj\\4kon\\src\\main\\resources\\data\\fakts.csv";
        String line = "";
        String cvsSplitBy = ";";
        int count = 0;

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "Cp1251"));) {


            br.readLine();
            while ((line = br.readLine()) != null) {
                count++;
                // use comma as separator
                String[] src = line.split(cvsSplitBy);

                if (src[0].equals("")) break;

                Fact fact = new Fact();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


                LocalDate localDate = LocalDate.parse(src[0], formatter);
                fact.setDate(localDate);
                fact.setId(0);
                fact.setAccount(src[1]);
                fact.setAmount(new BigDecimal(src[2].replace(",", ".")));
                fact.setDescription(src[3]);
                //fact.setDescr1(src[4]);
                //fact.setSubTotal(Double.parseDouble(src[8].replace(",", ".")));
                fact.setUser("user");

                dao.save(fact);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static User getUser() {
        return user;
    }
}
