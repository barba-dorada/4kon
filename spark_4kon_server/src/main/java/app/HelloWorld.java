package app;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

// TODO: 19.04.2017 add static content
// TODO: 19.04.2017 log level for jetty -> ERROR
// TODO: 19.04.2017 facts rest api
public class HelloWorld {

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        before("/*", (q, a) -> System.out.println("Received api call"));
        path("/hello",()->{
            get("", hw::getAll);
            get("/:id", HelloWorld::getById);
            post("", (req, res) -> "Hello World(post)");
            delete("/:id", (req, res) -> "Hello World(delete:id)");
            put("/:id", (req, res) -> "Hello World(put:id)");
        });

        System.out.println("http://localhost:4567/hello");
    }

    private static String getById(Request req, Response resp) {
        return "Hello World(get:" + req.params(":id") + ")";
    }

    public String getAll(Request req, Response resp) {
        return "Hello World(get)";
    }
}
