import java.io.*;
import java.util.*;

public class CrossReferenceMap {
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
    int current_line = 0;
		for (String word : reserved) {
			dictionary.insert(word);
		}

    try {
      source = new BufferedReader(new FileReader(args[0]));
      line = source.readLine();
      while (line != null) {
        StringTokenizer st1 = new StringTokenizer(line," _.,;()[]{}+-*/!=<>\t");
        while (st1.hasMoreTokens()) {
          String s1 = st1.nextToken();
          if (!dictionary.search(s1)) {
            if (!isString(s1) && !isInteger(s1)) {
  						if (!tree.search(s1)) {
              	tree.insert(s1);
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
      if (!Character.isDigit(word.charAt(0)))
        return false;
      char lastWord = word.charAt(word.length() - 1);
      if (containLiteral(lastWord))
        word = word.substring(0, word.length() - 2);
      if (word.indexOf('_') >= 0)
        word = word.replace("_", "");
      if (word.indexOf('e') >= 0) {
        Double.valueOf(word);
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
    return (word.indexOf('"') >= 0 || word.indexOf("'") >= 0);
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
}
