package com.burksnet.code.games.rain.console;

public class ConsoleException extends Exception {

	private String message;

	public ConsoleException(String message) {
		this.message = message;
	}

	public void printExceptionMessage() {
		System.out.println(message);
	}

}
