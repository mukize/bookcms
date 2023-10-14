/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bookcms;

import static spark.Spark.*;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

public class App {

    public static String resourceDir;
    public static Logger logger;
    public static Sql2o db;

    static {
        resourceDir = System.getProperty("user.dir") + "/src/main/resources";
        logger = LoggerFactory.getLogger(App.class);
        db = new Sql2o("jdbc:sqlite:" + resourceDir + "/database/bookcms.db", null, null);
        db.setDefaultColumnMappings(
                new HashMap<>() {
                    {
                        put("created_at", "createdAt");
                    }
                });
    }

    public static void main(String[] args) {
        staticFiles.externalLocation(resourceDir + "/public");
        port(8080);
        new Routes().register();
    }

}
