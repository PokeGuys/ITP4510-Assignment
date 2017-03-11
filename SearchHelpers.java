public class SearchHelpers implements Helpers {
  private boolean startString = false;

  public boolean isString(String word) {
    word = word.replace("\\\"", "");
    if (!startString) {
      char first = word.charAt(0);
      if (first == '\'') {
        return true;
      } else if (first == '"') {
        int count = word.length() - word.replace("\"", "").length();
        startString = (count == 1);
        return (count > 0);
      }
    } else {
      if (word.indexOf("\"") >= 0 || word.indexOf('\'') >= 0) {
        startString = false;
        return true;
      }
    }
    return startString;
  }

  public boolean isIdentifier(String word) {
    char first = word.charAt(0);
    return (first == '$' || first == '_' || Character.isLetter(first));
  }
}
