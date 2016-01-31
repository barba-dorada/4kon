package app.jersey;

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
 * Created by vad on 30.01.2016 7:54
 * 4kon
 */
public class Run {
    public static final int PORT = 8080;
    /*
    <servlet>
  <servlet-name>Jersey REST Service</servlet-name>
<servlet-class>
  com.sun.jersey.spi.container.servlet.ServletContainer
</servlet-class>
  <init-param>
    <param-name>com.sun.jersey.config.property.packages</param-name>
    <param-value>sample.hello.resources</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
  <servlet-name>Jersey REST Service</servlet-name>
  <url-pattern>/rest/*</url-pattern>
</servlet-mapping>
     */

    public static void main(String[] args) throws LifecycleException {
        //create a Tomcat instance to 8080 port
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("./temp");
        tomcat.setPort(PORT);
//adds a new context pointing to current directory as base dir.
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
//registers a servlet with name hello
      /*  Tomcat.addServlet(ctx, "hello", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req,
                                   HttpServletResponse resp) throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write("Hello World");
                w.flush();
            }
        });
        ctx.addServletMapping("/hello", "hello");*/

        Tomcat.addServlet(ctx,"restServlet",new com.sun.jersey.spi.container.servlet.ServletContainer())
                .addInitParameter("com.sun.jersey.config.property.packages","app.jersey.resources");

        ctx.addServletMapping("/rest/*","restServlet");

//adds a new mapping so any URL executes hello servlet

//starts Tomcat instance
        tomcat.start();
        System.out.printf("http://localhost:%d/hello\n", PORT);
        //waits current thread indefinitely
        tomcat.getServer().await();
    }
}
