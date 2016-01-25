package ru.cwl.kon.data;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.cwl.kon.dao.FactsDao;
import ru.cwl.kon.dao.FactsDaoHashListImpl;
import ru.cwl.kon.dao.TestUtils;
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
public class ReadFactsFromCsv {
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

    static public JSONArray getFacts(){
        List<Fact> facts = run();

        JSONArray jsonArray=new JSONArray();
        for (Fact fact : facts) {
            JSONObject o = new JSONObject(fact);
            jsonArray.put(o);
        }
        return jsonArray;
    }
    static public List<Fact> run() {
        FactsDao dao=new FactsDaoHashListImpl();
        TestUtils.loadFactsFromCsvFile(dao,null);
        return dao.getAll();
    }

}
