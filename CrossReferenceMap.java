import java.io.*;
import java.util.*;

public class CrossReferenceMap
{
  /**
   * Reserved word used to avoid inserting into tree.
   *
   * @var String array
   */
  private final String[] RESERVED = {
    "abstract", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", " double", "else",
    "extends", "false", "final", "finally", "float", "for", "goto", "if",
    "implements", "import", " instanceof", "int", "interface", "long", "native",
    "new", "null", "operator", "package", "private", " protected", "public", "rest",
    "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
    "throw", "throws", "transient", "true", "try", "void", "volatile", "while"
  };

  /**
   * Used to insert existed word into tree for refernce.
   *
   * @var BinarySearchTree
   */
  private BinarySearchTree tree = new BinarySearchTree();

  /**
   * Dictionary storing reserved word.
   *
   * @var BinarySearchTree
   */
  private BinarySearchTree dictionary = new BinarySearchTree();

  /**
   * Search helpers.
   *
   * @var SearchHelpers
   */
  private Helpers helpers = new SearchHelpers();

  /**
   * Store reserved word into BinaryTree.
   *
   * @return void
   */
  public CrossReferenceMap()
  {
    for (String word : RESERVED) {
      dictionary.insert(word);
    }
  }

  /**
   * Determine is it identifer by checking
   * 1. NOT a reserved word.
   * 2. NOT in a String.
   * 3. Start with [$_a-zA-Z].
   *
   * @param String word
   *
   * @return boolean
   */
  public boolean isIdentifier(String word)
  {
    return (!dictionary.search(word) && !helpers.isString(word) && helpers.isIdentifier(word));
  }


  /**
   * Determine is it already in tree, if not insert.
   * Insert currentLine into tree node.
   *
   * @param String word
   * @param int line
   *
   * @return void
   */
  public void insertTree(String word, int line)
  {
    if (!tree.search(word)) {
      tree.insert(word);
    }
    tree.insertLines(word, line);
  }

  /**
   * Press any key to continue
   *
   * @return String
   */
  public void pressAnyKey() {
    try {
      System.out.println("Press any key to continue...");
      System.in.read();
    } catch(Exception e) {}
  }

  /**
   * Print out CrossReferenceMap
   *
   * @return void
   */
  public void printResult()
  {
    tree.inorder();
  }

  /**
   * CrossMapReference Main Program.
   * Check starting arguments, if length not equal 1, exit.
   * Read the file and parse it into Tokens.
   * Use loop to find is it match requirements, if so, insert.
   * Else, skip.
   *
   * After looping, print out all the result with ascending and alphabet order
   *
   * @param String[] args
   *
   * @return void
   */
  public static void main(String [] args)
  {
    // Check args length it is included filename.
    // If not exit.
    if (args.length != 1) {
      System.out.println("Usage: java CrossReferenceMap <filename>");
      System.exit(1);
    }
    // Initialize dictionary for the reserved word.
    CrossReferenceMap map = new CrossReferenceMap();
    BufferedReader source;
    String line;

    /**
     * Current line.
     *
     * @var Integer
     */
    int current = 1;

    // Prevent any IOException when reading file
    try {
      source = new BufferedReader(new FileReader(args[0]));
      line = source.readLine();
 
      // While-loop until not nextLine.
      while (line != null) {
        // Tokenize whole line into pieces
        StringTokenizer st1 = new StringTokenizer(line," .,;()[]{}+-*/!=<>\t");
        while (st1.hasMoreTokens()) {
          // Determine is s1 a identifier
          String s1 = st1.nextToken();
          if (map.isIdentifier(s1)) {
            map.insertTree(s1, current);
          }
        }
        // Add 1 to currentLine whcih means this line is already finished.
        // Read next line.
        current++;
        line = source.readLine();
      }
      // Release file source
      source.close();
    } catch (IOException iox) {
      System.out.println("Problem encountered in reading file!" );
    }

    // Print out the Cross Map Reference
    System.out.print("Cross Map Reference :- \n");
    map.printResult();

    // Press any key to continue.
    map.pressAnyKey();
  }
}
