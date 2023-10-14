package bookcms;

import java.io.File;
import java.util.HashMap;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Database {

  public static Sql2o sql;

  static {
    Sql2o sql = new Sql2o("jdbc:sqlite:" + App.resourceDir + "/database/bookcms.db", null, null);
    sql.setDefaultColumnMappings(
        new HashMap<>() {
          {
            put("created_at", "createdAt");
          }
        });
  }

}
