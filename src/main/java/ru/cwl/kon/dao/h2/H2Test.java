package ru.cwl.kon.dao.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vad on 06.02.2016 17:07
 * 4kon
 */
public class H2Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:~/test","sa","");
        conn.close();
    }
}
