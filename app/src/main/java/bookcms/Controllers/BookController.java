package bookcms.Controllers;

import bookcms.Templater;
import bookcms.Models.Book;
import bookcms.Repositories.BookRepository;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class BookController {


  public static String guestIndex() {

    Map<String, Object> model = new HashMap<>();
    try {
      model.put("books", BookRepository.all());
    } catch (Exception e) {
      return Templater.error(e);
    }

    return Templater.render(model, "/guest/books/index.ftl");
  }

  public static String guestShow(String slug) {
    Map<String, Object> model = new HashMap<>();
    Book book;
    try {
      book = BookRepository.find(slug);
    } catch (Exception e) {
      return Templater.error(e);
    }

    model.put("book", book);
    return Templater.render(model, "/guest/books/show.ftl");
  }

}
