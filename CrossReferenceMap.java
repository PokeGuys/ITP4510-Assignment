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
    int current_line = 1;
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
          if (!dictionary.search(s1)) {
            if (!isString(s1)) {
              if (!isInteger(s1) && !isOperator(s1)) {
                if (!tree.search(s1)) {
                	tree.insert(s1);
                }
                tree.insertLine(s1, current_line);
              }
            }
          }
        }
        current_line++;
        line = source.readLine();
      }
      source.close();
    } catch (IOException iox) {
      System.out.println("Problem encountered in reading file!" );
    }
    System.out.print("Cross Map Reference :- \n");
    tree.inorder();
  }

  public static boolean isInteger(String word) {
    try {
      word = word.replace("_", "");
      if (!Character.isDigit(word.charAt(0)))
        return false;
      char lastWord = word.charAt(word.length() - 1);
      if (containLiteral(lastWord))
        word = word.substring(0, word.length() - 1);
      if (word.indexOf('e') >= 0) {
        Double.valueOf(word);
      } else if (word.indexOf("0b") == 0) {
        Long.parseLong(word.substring(2), 2);
      } else {
        Long.decode(word);
      }
    } catch (NumberFormatException e) {
      return false;
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  public static boolean isString(String word) {
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

  public static boolean containLiteral(char word) {
    switch (Character.toLowerCase(word)) {
      case 'l':
      case 'd':
      case 'f':
        return true;
    }
    return false;
  }

  protected static boolean isOperator(String op) {
    return (op.equals("~") || op.equals("%") || op.equals("instanceof") ||
      op.equals("&") || op.equals("^") || op.equals("|") || op.equals("&&") ||
      op.equals("||") || op.equals("?") || op.equals(":"));
  }
}
