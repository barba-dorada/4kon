package ru.cwl.kon.dao;

import ru.cwl.kon.model.User;

/**
 * Created by vad on 24.01.2016 16:35
 * 4kon
 */
public class DaoTest {
    public static void main(String[] args) {
        FactsDao dao = new FactsDaoHashListImpl();
        User user = new User(1, "vad", "12345", "vadim");
        TestUtils.loadFactsFromCsvFile(dao, user);
        System.out.println(dao.getAll().toString());
    }
}
