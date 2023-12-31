package bookcms;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
public class Templater {

  private static Configuration configuration;

  static {
    configuration = freemarkerConfig();
  }

  public static String render(Map<String, Object> model, String templatePath) {
    return new FreeMarkerEngine(configuration)
        .render(new ModelAndView(model, templatePath));
  }

  public static String render(String templatePath) {
    return new FreeMarkerEngine(configuration)
        .render(new ModelAndView(null, templatePath));
  }

  public static String error(Exception e) {
    App.logger.atInfo().log(e.getMessage());
    return new FreeMarkerEngine(configuration)
        .render(new ModelAndView(new HashMap<String, Object>() {
          {
            put("error", e);
          }
        }, "error.ftl"));
  }

  private static Configuration freemarkerConfig() {
    Configuration fltConfig = new Configuration(Configuration.VERSION_2_3_26);
    try {
      fltConfig.setDirectoryForTemplateLoading(
          new File(System.getProperty("user.dir") + "/src/main/resources/templates"));
    } catch (IOException e) {
      System.out.println("Unable to set directory '" + System.getProperty("user.dir")
          + "/src/main/resources/templates" + "for Freemarker.");
    }
    fltConfig.setTemplateExceptionHandler(new FTLExceptionHandler());
    fltConfig.addAutoImport("c", "/libs/components.ftl");
    fltConfig.addAutoImport("layout", "/libs/layouts.ftl");
    fltConfig.setLazyAutoImports(true);
    fltConfig.setShowErrorTips(false);
    return fltConfig;
  }

}


class FTLExceptionHandler implements TemplateExceptionHandler {
  public void handleTemplateException(TemplateException te, Environment env, java.io.Writer out)
      throws TemplateException {
    try {
      out.write(
          "[ERROR: " + te.getMessageWithoutStackTop() + "<br> Cause: " + te.getCause() + " ]");
    } catch (IOException e) {
      throw new TemplateException("Failed to print error message. Cause: " + e, env);
    }
  }
}
