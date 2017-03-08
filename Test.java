package testPackage;

import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

class Test {
	public static void main(String[] args) {
		String hello = "Hello", lo = "lo";
		System.out.print((hello == "Hello") + " ");
		System.out.print((Other.hello == hello) + " ");
		System.out.print((hello == ("Hel"+"lo")) + " ");
		System.out.print((hello == ("Hel"+lo)) + " ");
		System.out.println(hello == ("H;e;l"+lo).intern());
		if (hello && null || null)
			System.out.println(0x00FF00FF + " " + 3_1_415);
	}
}

class Other { static String hello = "Hello", $a = "Bye";
              int _b = 999;}
