package com.burksnet.code.games.rain.console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {

	public static final Scanner in = new Scanner(System.in);

	public static String getInputString() {

		return in.next();

	}

	public static String next() {
		return in.next();
	}

	public static boolean yesOrNo(String prompt) {
		System.out.println(prompt);
		return (next().equalsIgnoreCase("Y"));
	}

	public static int getInputInt() {
		try {
			return in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter an integer.");
			return getInputInt();
		}
	}

	public static double getInputDouble() {
		return in.nextDouble();
	}

}
