package bookcms.Controllers;

import bookcms.Templater;
import bookcms.Models.Book;
import bookcms.Repositories.BookRepository;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {


  // "/books"
  public static String guestIndex(Request res, Response req) {

    Map<String, Object> model = new HashMap<>();
    try {
      model.put("books", BookRepository.all());
    } catch (Exception e) {
      return Templater.error(e);
    }

    return Templater.render(model, "/guest/books/index.ftl");
  }

  // "/books/:id"
  public static String guestShow(Request res, Response req) {
    return Templater.render("/guest/books/show.ftl");
  }

}
