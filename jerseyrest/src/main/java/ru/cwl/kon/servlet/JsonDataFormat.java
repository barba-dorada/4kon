package ru.cwl.kon.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by vad on 07.02.2016 0:16
 * 4kon
 */
public class JsonDataFormat {

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static void main(String[] args) throws ParseException {
        /*
        JSON.stringify({'now': new Date()})
        "{"now":"2016-02-06T22:17:26.166Z"}"
    */

        Date d = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat(JSON_DATE_FORMAT);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(fmt.format(d));
        Date d2 = fmt.parse("2016-02-06T22:17:26.166Z");
        System.out.println(fmt.format(d2));
    }
}
