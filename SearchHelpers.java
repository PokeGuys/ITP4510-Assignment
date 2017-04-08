public class SearchHelpers implements Helpers
{
  /**
   * Indicates whether it is in the splitted String.
   *
   * @var boolean
   */
  private boolean startString = false;

  /**
   * Store reserved word into BinaryTree.
   *
   * @return void
   */
  public SearchHelpers()
  {
    for (String word : RESERVED) {
      dictionary.insert(word);
    }
  }

  /**
   * Indicates whether word is a String.
   *
   * @param String word
   *
   * @return boolean
   */
  public boolean isString(String word)
  {
    // Clean all escape string \" inside the word
    word = word.replace("\\\"", "");
    // if there is a splitted string and not yet closed
    if (startString) {
      // if word contains symbol about end of String
      // Set boolean startString as false
      if (word.contains("\"")) {
        startString = false;
        return true;
      }
    } else {
      // Check is it start with ' or "
      // Determine which datatype it is.
      // If it start with ', Exit.
      char first = word.charAt(0);
      if (first == '\'') {
        return true;
      } else if (first == '"') {
        // Check how many " contained
        // If there is one only, means the string is splitted.
        // Else, end of string.
        int count = word.length() - word.replace("\"", "").length();
        startString = (count == 1);
        return (count > 0);
      }
    }
    return startString;
  }

  /**
   * Indicates whether word is an Identifier.
   * By checking first char is [$_a-zA-Z]
   * And the following char is [$_a-zA-Z0-9]
   * 
   * @param String word
   * 
   * @return boolean
   */
  public boolean isIdentifier(String word)
  {
    if (word == null) return false;
    if (word.equals("")) return false;

    char first = word.charAt(0);
    if (!Character.isLetter(first) && first != '$' && first != '_') {
      return false;
    }
    for (int i = 1; i < word.length(); i++) {
      if (!Character.isLetterOrDigit(word.charAt(i)) && first != '$' && first != '_') {
        return false;
      }
    }
    return true;
  }
  
  public boolean isReserved(String word)
  {
    reutnr dictionary.search(word);
  }
  
  public boolean isQualified(String word)
  {
    return (!isReserved(word) && !isString(word) && isIdentifier(word));
  }
}
