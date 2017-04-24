package app;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

// * 19.04.2017 add static content
// TODO: 19.04.2017 log level for jetty -> ERROR
// TODO: 19.04.2017 facts rest api
public class HelloWorld {

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        if (true) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/ui_static_web";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
        //staticFiles.externalLocation("C:\\dev\\projects\\4kon\\ui_static_web");
        before("/*", (q, a) -> System.out.println("Received api call"));
        path("/hello", () -> {
            get("", hw::getAll);
            get("/:id", HelloWorld::getById);
            post("", (req, res) -> "Hello World(post)");
            delete("/:id", (req, res) -> "Hello World(delete:id)");
            put("/:id", (req, res) -> "Hello World(put:id)");
        });
        FactController fc = new FactController();
        path("/api/fact", () -> {
            get("", fc::getAll);
            get("/:id", fc::getById);
            post("", fc::create);
            delete("/:id", fc::delete);
            put("/:id", fc::update);
        });

/*        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            System.out.printf("%s:%s\n",entry.getKey(),entry.getValue());
        }*/

        System.out.println("http://localhost:4567/hello");
    }

    private static String getById(Request req, Response resp) {
        return "Hello World(get:" + req.params(":id") + ")";
    }

    public String getAll(Request req, Response resp) {
        return "Hello World(get)";
    }
}
