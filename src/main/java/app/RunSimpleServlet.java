package app;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by vad on 30.01.2016 6:26
 * 4kon
 */
public class RunSimpleServlet {

    public static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        //create a Tomcat instance to 8080 port
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("./temp");
        tomcat.setPort(PORT);
//adds a new context pointing to current directory as base dir.
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
//registers a servlet with name hello
        Tomcat.addServlet(ctx, "hello", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req,
                                   HttpServletResponse resp) throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write("Hello World");
                w.flush();
            }
        });
//adds a new mapping so any URL executes hello servlet
        ctx.addServletMapping("/*", "hello");
//starts Tomcat instance
        tomcat.start();
        System.out.printf("http://localhost:%d", PORT);
        //waits current thread indefinitely
        tomcat.getServer().await();
    }
}
