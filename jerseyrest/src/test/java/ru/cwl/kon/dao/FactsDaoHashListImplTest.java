package ru.cwl.kon.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.cwl.kon.model.Fact;
import ru.cwl.kon.model.User;

import static org.junit.Assert.*;

/**
 * Created by vad on 24.01.2016 16:48
 * 4kon
 */
public class FactsDaoHashListImplTest {
    FactsDaoHashListImpl dao;
    User user;

    @Before
    public void setUp() throws Exception {
        dao = new FactsDaoHashListImpl();
        user = new User(1, "vad", "12345", "vadim");

        TestUtils.loadFactsFromCsvFile(dao, user);

    }



    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        Fact f = dao.get(1);
        assertEquals("кошелек", f.getAccount());
        assertEquals(Double.valueOf(-400.0), f.getAmount());
        assertEquals(user,f.getUser());
    }

    @Test
    public void testSave() throws Exception {


    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testGetNextId() throws Exception {

    }
}