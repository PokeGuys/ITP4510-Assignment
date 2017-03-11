import java.io.*;
import java.util.*;

public class CrossReferenceMap {
  private static boolean startString = false;
  private static final String[] reserved = {
    "abstract", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", " double", "else",
    "extends", "false", "final", "finally", "float", "for", "goto", "if",
    "implements", "import", " instanceof", "int", "interface", "long", "native",
    "new", "null", "operator", "package", "private", " protected", "public", "rest",
    "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
    "throw", "throws", "transient", "true", "try", "void", "volatile", "while"
  };

  public static void main(String [] args) {
    if (args.length != 1) {
      System.out.println("Usage: java CrossMapReference <filename>");
      System.exit(1);
    }
    BinarySearchTree dictionary = new BinarySearchTree();
    BinarySearchTree tree = new BinarySearchTree();
    BufferedReader source;
    String line;
    int currentLine = 1;
    for (String word : reserved) {
      dictionary.insert(word);
    }

    try {
      source = new BufferedReader(new FileReader(args[0]));
      line = source.readLine();
      while (line != null) {
        StringTokenizer st1 = new StringTokenizer(line," .,;()[]{}+-*/!=<>\t");
        while (st1.hasMoreTokens()) {
          String s1 = st1.nextToken();
          if (!dictionary.search(s1) && !isString(s1) && isIdentifier(s1)) {
            if (!tree.search(s1)) {
              tree.insert(s1);
            }
            tree.insertLine(s1, currentLine);
          }
        }
        currentLine++;
        line = source.readLine();
      }
      source.close();
    } catch (IOException iox) {
      System.out.println("Problem encountered in reading file!" );
    }
    System.out.print("Cross Map Reference :- \n");
    tree.inorder();
  }

  public static boolean isString(String word)
  {
    word = word.replace("\\\"", "");
    if (!startString) {
      char firstWord = word.charAt(0);
      int count = 0;
      switch (firstWord) {
        case '"':
          count = word.length() - word.replace("\"", "").length();
          break;
        case '\'':
          count = word.length() - word.replace("'", "").length();
          break;
      }
      startString = (count == 1);
      return (count > 0);
    } else {
      if (word.indexOf("\"") >= 0 || word.indexOf('\'') >= 0) {
        startString = false;
        return true;
      }
    }
    return startString;
  }
  
  public static boolean isIdentifier(String word)
  {
    char firstWord = word.charAt(0);
    return (firstWord == '$' || firstWord == '_' || Character.isLetter(firstWord));
  }
}
