import java.io.*;
import java.util.*;

public class CrossReferenceMap {
	private static final String[] RESERVED = {
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
		Helpers helpers = new SearchHelpers();
		BinarySearchTree dictionary = new BinarySearchTree();
		BinarySearchTree tree = new BinarySearchTree();
		BufferedReader source;
		int current = 1;
		String line;
		for (String word : RESERVED) {
			dictionary.insert(word);
		}

		try {
			source = new BufferedReader(new FileReader(args[0]));
			line = source.readLine();

			while ( line != null) {
				StringTokenizer st1 = new StringTokenizer(line," .,;()[]{}+-*/!=<>\t");
				while (st1.hasMoreTokens()) {
					String s1 = st1.nextToken();
					if (!dictionary.search(s1) && !helpers.isString(s1) && helpers.isIdentifier(s1)) {
						if (!tree.search(s1)) {
							tree.insert(s1);
						}
						tree.insertLines(s1, current);
					}
				}
				current++;
				line = source.readLine();
			}
			source.close();
		} catch (IOException iox) {
			System.out.println("Problem encountered in reading file!" );
		}
		System.out.print("Cross Map Reference :- \n");
		tree.inorder();

		System.out.println("Press any key to continue...");
		System.in.read();
		System.exit(1);
	}
}
