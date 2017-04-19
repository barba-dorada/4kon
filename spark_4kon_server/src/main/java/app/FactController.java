package app;

import spark.Request;
import spark.Response;

/**
 * Created by vadim.tishenko
 * on 19.04.2017 22:38.
 */
public class FactController {


    public Object getAll(Request req, Response res) {
        return "ALL";
    }

    public Object getById(Request req, Response res) {
        return "FACT";

    }

    public Object create(Request req, Response res) {
        return "CREATE";

    }

    public Object delete(Request req, Response res) {
        return "DELETE";

    }

    public Object update(Request req, Response res) {
        return "UPDATE";

    }
}
