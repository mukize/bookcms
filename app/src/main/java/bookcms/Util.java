package bookcms;

public class Util {

  public static String toSlug(String text) {
    return text
        .trim()
        .replaceAll("[^\\w\\s\\d-]", "")
        .replaceAll("\\s", "-")
        .toLowerCase();
  }

}
