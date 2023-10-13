package bookcms.Controllers;

import bookcms.Templater;
import spark.Request;
import spark.Response;

public class BookController {

  public static String guestIndex(Request res, Response req) {

    return new Templater().render("/books/index.ftl");
  }

  public static String guestShow(Request res, Response req) {
    return new Templater().render("/books/index.ftl");
  }

}
