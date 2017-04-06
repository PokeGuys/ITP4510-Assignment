package testPackage;

import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

class Test {
	public static void main(String[] args) {
		byte b = 100;
		short s = 10000;
		int i = 100000;
		int hexVal = 0x1a;
		int binVal = 0b11010;
		double d1 = 123.4;
		double d2 = 1.234e2;
		float f1  = 123.4f;
		long creditCardNumber = 1234_5678_9012_3456L;
		long socialSecurityNumber = 999_99_9999L;
		float pi =  3.14_15F;
		long hexBytes = 0xFF_EC_DE_5E;
		long hexWords = 0xCAFE_BABE;
		long maxLong = 0x7fff_ffff_ffff_ffffL;
		byte nybbles = 0b0010_0101;
		long bytes = 0b11010010_01101001_10010100_10010010;
		String a = "test";
		char a = 't';
		String a = "test + test = testtest?";
		char a = '\u0108';
		String hello = "Hello", lo = "lo";
		String a = "test\"\"test";
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
