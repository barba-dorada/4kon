package app;

import ru.cwl.dao.FactDao;
import ru.cwl.dao.simple.SimpleFactDao;
import ru.cwl.kon.model.Fact;
import spark.Request;
import spark.Response;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Created by vadim.tishenko
 * on 19.04.2017 22:38.
 */
public class FactController {

    public FactController(FactDao dao) {
        this.dao = dao;
    }

    FactDao dao;

    public Object getAll(Request req, Response res) {
        return dao.getAll();
    }

    public Object getById(Request req, Response res) {
        Fact o = dao.getById(Integer.parseInt(req.params("id")));
        return o;
    }

    public Object create(Request req, Response res) {
        String json = req.body();
        Jsonb jsonb = JsonbBuilder.create();
        Fact v=jsonb.fromJson(json,Fact.class);
        Fact result = dao.save(v);
        return result;
    }

    public Object delete(Request req, Response res) {
        dao.delete(Integer.parseInt(req.params("id")));
        return "OK";
    }

    public Object update(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        String json = req.body();
        Jsonb jsonb = JsonbBuilder.create();
        Fact v=jsonb.fromJson(json,Fact.class);
        Fact result = dao.update(id,v);
        //res.type("text/xml" );
        return result;
    }
}
