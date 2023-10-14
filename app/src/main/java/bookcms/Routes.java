package bookcms;

import static spark.Spark.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bookcms.Controllers.BookController;

public class Routes {

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
    get("/", (req, res) -> Templater.render("guest/home.ftl"));
    get("/contact", (req, res) -> Templater.render("guest/contact.ftl"));
    get("/about", (req, res) -> Templater.render("guest/about.ftl"));
    get("/session", (req, res) -> req.session().id());
    get("/books", (req, res) -> BookController.guestIndex(req, res));
    get("/books/:id", (req, res) -> BookController.guestShow(req, res));
  }

}
