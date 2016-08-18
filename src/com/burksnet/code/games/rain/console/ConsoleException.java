package com.burksnet.code.games.rain.console;

import com.asomeman11.code.rain.launcher.Log.LogReader;

public class ConsoleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4103268543967902446L;
	private String message;

	public ConsoleException(String message) {
		this.message = message;
		
	}

	public void printExceptionMessage() {
		System.out.println(message);
	}

}
