package bookcms;

import static spark.Spark.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bookcms.Controllers.BookController;

public class Routes {

  public static Templater templater = new Templater();
  public static Logger logger = LoggerFactory.getLogger(Routes.class);

  public void register() {
    initDebugRoute();
    initGuestRoutes();
  }

  private void initDebugRoute() {
    get("/refresh", (req, res) -> {
      System.out.println("refreshing servers");
      logger.info("Refreshing server.");
      stop();
      return "restarted!";
    });
  }

  private void initGuestRoutes() {
    get("/", (req, res) -> templater.render("guest/home.ftl"));
    get("/contact", (req, res) -> templater.render("guest/contact.ftl"));
    get("/about", (req, res) -> templater.render("guest/about.ftl"));

    get("/books", (req, res) -> BookController.guestIndex(req, res));
    get("/books/:id", (req, res) -> BookController.guestShow(req, res));
  }

}
