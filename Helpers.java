public interface Helpers
{
  /**
   * Reserved word used to avoid inserting into tree.
   *
   * @var String array
   */
  protected final String[] RESERVED = {
    "abstract", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", " double", "else",
    "extends", "false", "final", "finally", "float", "for", "goto", "if",
    "implements", "import", " instanceof", "int", "interface", "long", "native",
    "new", "null", "operator", "package", "private", " protected", "public", "rest",
    "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
    "throw", "throws", "transient", "true", "try", "void", "volatile", "while"
  };

  /**
   * Dictionary storing reserved word.
   *
   * @var BinarySearchTree
   */
  protected BinarySearchTree dictionary = new BinarySearchTree();

  public boolean isQualified(String word);
  public boolean isReserved(String word);
  public boolean isIdentifier(String item);
  public boolean isString(String item);
}
