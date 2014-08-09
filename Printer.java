// Written by Jasmine Vickery

import java.util.Scanner;

/*
 * This is basically a long-winded way of calling System.out.println(), 
 * but since the game might be displayed through a gui later, it prevents numerous println() statements having to be changed later 
 * 
 * Print is made a static method so any class can print without having to instantiate this class.
 */

public class Printer {

	private static Scanner s;

	private Printer() {

	}

	public static void print(String message) {
		System.out.println(message);
	}

	// don't quite know if the best thing to be doing is creating a new scanner
	// each time something needs to print, but it can do for now
	public static String getInput() {
		s = new Scanner(System.in);
		String input = s.nextLine();
		return input;
	}

}
