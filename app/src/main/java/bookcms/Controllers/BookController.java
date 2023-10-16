package bookcms.Controllers;

import bookcms.Templater;
import bookcms.Models.Book;
import bookcms.Repositories.BookRepository;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class BookController {


  public static String guestIndex(Request req, Response res) {

    Map<String, Object> model = new HashMap<>();
    try {
      model.put("books", BookRepository.all());
    } catch (Exception e) {
      return Templater.error(e);
    }

    return Templater.render(model, "/guest/books/index.ftl");
  }

  public static String guestShow(Request req, Response res) {
    String requestId = req.params(":name");
    Map<String, Object> model = new HashMap<>();

    try {
      int id = Integer.getInteger(requestId);
      Book book = BookRepository.find(id);
      model.put("book", BookRepository.find(id));
    } catch (Exception e) {
      return Templater.error(e);
    }
    return Templater.render(model, "/guest/books/show.ftl");
  }

}
