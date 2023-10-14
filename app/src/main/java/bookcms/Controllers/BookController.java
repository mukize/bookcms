package bookcms.Controllers;

import bookcms.Templater;
import spark.Request;
import spark.Response;

public class BookController {


  // "/books"
  public static String guestIndex(Request res, Response req) {
    return Templater.render("/guest/books/index.ftl");
  }

  // "/books/:id"
  public static String guestShow(Request res, Response req) {
    return Templater.render("/guest/books/show.ftl");
  }

}
