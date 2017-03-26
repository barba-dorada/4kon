package ru.cwl.kon.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.cwl.kon.dao.FactsDao;
import ru.cwl.kon.dao.FactsDaoHashListImpl;
import ru.cwl.kon.dao.TestUtils;
import ru.cwl.kon.data.ReadFactsFromCsv;
import ru.cwl.kon.model.Fact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    FactsDao dao = new FactsDaoHashListImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        TestUtils.loadFactsFromCsvFile(dao, TestUtils.getUser());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        JSONArray result = getFacts(dao.getAll());
        /*JSONObject obj = new JSONObject();
        obj.put("data", result);*/

        res.getWriter().append(result.toString());
    }

    //todo move to json? package
    JSONArray getFacts(List<Fact> facts) {
        JSONArray jsonArray = new JSONArray();
        for (Fact fact : facts) {
            JSONObject o = new JSONObject(fact);
            jsonArray.put(o);
        }
        return jsonArray;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
