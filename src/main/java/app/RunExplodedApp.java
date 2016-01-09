package app;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Created by vad on 03.01.2016 20:36
 * 4kon
 */
public class RunExplodedApp {
    public static void main(String[] args) throws LifecycleException, InterruptedException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        // Create a context
        File docBase = new File("C:\\dev\\proj\\4kon\\classes\\artifacts\\web1_war_exploded");
        tomcat.addWebapp(null, "", docBase.getAbsolutePath());
        // Start the instance
        tomcat.start();
        // Loop to serve requests
        while (true) {
            Thread.sleep(5000);
        }
    }
}
