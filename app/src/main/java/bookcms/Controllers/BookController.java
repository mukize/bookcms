package bookcms.Controllers;

import bookcms.Templater;
import bookcms.Repositories.BookRepository;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class BookController {


  public static String guestIndex(Request res, Response req) {

    Map<String, Object> model = new HashMap<>();
    try {
      model.put("books", BookRepository.all());
    } catch (Exception e) {
      return Templater.error(e);
    }

    return Templater.render(model, "/guest/books/index.ftl");
  }

  public static String guestShow(Request res, Response req) {
    return Templater.render("/guest/books/show.ftl");
  }

}
