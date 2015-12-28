package ru.cwl.kon.servlet;

import org.json.JSONArray;
import ru.cwl.kon.data.ReadFaktsFromCsv;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vad on 26.12.2015 20:42
 * 4kon
 */
/*
create POST
read GET
update PUT
delete DELETE
list GET
list period GET

get by id
get all
get by filter

 */
public class FactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        JSONArray result = ReadFaktsFromCsv.getFacts();

        res.getWriter()
                .append(result.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
