import java.io.*;
import java.util.*;

public class CrossReferenceMap
{
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
    BufferedReader source;
    String line;

    /**
     * Used to insert existed word into tree for refernce.
     *
     * @var BinarySearchTree
     */
    BinarySearchTree tree = new BinarySearchTree();

    /**
     * Search helpers.
     *
     * @var SearchHelpers
     */
    Helpers helpers = new SearchHelpers();
    
    /**
     * Current line.
     *
     * @var Integer
     */
    int currentLine = 1;

    // Prevent any IOException when reading file
    try {
      source = new BufferedReader(new FileReader(args[0]));
      line = source.readLine();
 
      // While-loop until not nextLine.
      while (line != null) {
        // Tokenize whole line into pieces
        StringTokenizer st1 = new StringTokenizer(line," .,;()[]{}+-*/!=<>\t");
        while (st1.hasMoreTokens()) {
          /**
           * Determine is s1 a identifer by checking
           * 1. NOT a reserved word.
           * 2. NOT in a String.
           * 3. Start with [$_a-zA-Z].
           */
          String s1 = st1.nextToken();
          if (helpers.isQualified(s1)) {
            // Determine is it already in tree, if not insert.
            // Insert currentLine into tree node.
            if (!tree.search(word)) {
              tree.insert(word);
            }
            tree.insertLines(word, currentLine);
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
    tree.inorder();

    // Press any key to continue.
    try {
      System.out.println("Press any key to continue...");
      System.in.read();
    } catch(Exception e) {}
  }
}
