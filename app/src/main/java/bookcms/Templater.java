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

public class Templater {

  public String render(Map<String, Object> model, String templatePath) {
    return new FreeMarkerEngine(freemarkerConfig())
        .render(new ModelAndView(model, templatePath));
  }

  public String render(String templatePath) {
    return new FreeMarkerEngine(freemarkerConfig())
        .render(new ModelAndView(null, templatePath));
  }

  private Configuration freemarkerConfig() {
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
      out.write("[ERROR: " + te.getMessageWithoutStackTop() + "]");
    } catch (IOException e) {
      throw new TemplateException("Failed to print error message. Cause: " + e, env);
    }
  }
}
