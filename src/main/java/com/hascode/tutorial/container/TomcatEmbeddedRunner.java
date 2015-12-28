package com.hascode.tutorial.container;

import java.io.File;

import ru.cwl.kon.servlet.FactServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.hascode.tutorial.servlet.DatePrintServlet;

public class TomcatEmbeddedRunner {
    public void startServer() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        File base = new File(System.getProperty("java.io.tmpdir"));
        Context rootCtx = tomcat.addContext("/app", base.getAbsolutePath());
        Tomcat.addServlet(rootCtx, "dateServlet", new DatePrintServlet());
        Tomcat.addServlet(rootCtx, "factServlet", new FactServlet());

        rootCtx.addServletMapping("/date", "dateServlet");
        rootCtx.addServletMapping("/fact", "factServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}
