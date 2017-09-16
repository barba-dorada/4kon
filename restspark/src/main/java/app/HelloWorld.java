package app;

import ru.cwl.dao.FactDao;
import ru.cwl.dao.simple.SimpleFactDao;
import ru.cwl.dao.util.TestUtils;
import spark.Filter;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static spark.Spark.*;

// * 19.04.2017 add static content
// TODO: 19.04.2017 log level for jetty -> ERROR
// * 19.04.2017 facts rest api 2.5.2017
public class HelloWorld {

    public static void main(String[] args) {
        // add static content
        if (true) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/ui_static_web";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
        before("/*", (q, a) -> System.out.println("api call: "+q.requestMethod()+" "+q.pathInfo()));

        FactDao fd = new SimpleFactDao();
        TestUtils.loadFactsFromCsvFile(fd);
        FactController fc = new FactController(fd);
        Jsonb jsonb = JsonbBuilder.create();
        Filter addContTypeJson = (request, response) -> {
            response.type("application/json;charset=UTF-8");
        };

        /*
        Code Description
400 Bad Request.
401 Unauthorized.
403 Forbidden.
404 Not Found.
         */
        path("/api/fact", () -> {
            //todo можно обойтись одним фильтром?
            before("", addContTypeJson);
            before("/*", addContTypeJson);

            get("", fc::getAll, jsonb::toJson);
            post("", fc::create, jsonb::toJson);

            get("/:id", fc::getById, jsonb::toJson);
            delete("/:id", fc::delete);
            put("/:id", fc::update, jsonb::toJson);
        });

        System.out.println("http://localhost:4567/api/fact");
    }
}
