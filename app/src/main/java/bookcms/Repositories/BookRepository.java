package bookcms.Repositories;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import bookcms.App;
import bookcms.Models.Book;
import java.util.List;

public class BookRepository {

  private static Sql2o db = App.db;

  public static List<Book> all() throws Exception {
    try (Connection con = db.open()) {
      return con.createQuery("select * from books").executeAndFetch(Book.class);
    } catch (Exception e) {
      throw e;
    }
  }

  public static Book find(String slug) throws Exception {
    try (Connection con = db.open()) {
      return con.createQuery("select * from books where slug = :slug")
          .addParameter("slug", slug)
          .executeAndFetchFirst(Book.class);
    } catch (Exception e) {
      throw e;
    }
  }

}
