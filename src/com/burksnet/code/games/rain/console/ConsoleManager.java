package com.burksnet.code.games.rain.console;

import java.io.PrintStream;

import com.burksnet.code.games.rain.Game;

public class ConsoleManager implements Runnable {

	private boolean running = false;
	public PrintStream out;

	public ConsoleManager(String errorFile, Game game) {
		this.game = game;
		System.err.println("Error File Mechanics Not Implemented.");
	}

	private Game game;

	@Override
	public void run() {

		boolean running = true;
		while (running) {

			String in = ConsoleInput.in.nextLine();
			processCommand(in);

		}

	}

	public synchronized void stop() {
		running = false;
	}

	public void init(Game game) {
		this.game = game;
	}

	private void processCommand(String cmd) {

		String[] array = cmd.split(" ");

		if (cmd.equalsIgnoreCase("QUIT")) {
			System.out.println("Application Terminated.");
			game.stop();
		}
		if (cmd.equalsIgnoreCase("RESPAWN")) {
			System.out.println("User Respawned.");
			game.player = game.player.respawn();
		}
		if (array[0].equalsIgnoreCase("TP")) {
			try {
				if (array.length > 3) {
					throw new ArrayIndexOutOfBoundsException(array.length - 1);
				}
				int local1 = Integer.parseInt(array[1]);
				int local2 = Integer.parseInt(array[2]);
				System.out.println("User Teleported.");
				game.player.x = local1;
				game.player.y = local2;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("\nInvalid Cordinate Variable. Cordinate was not an Integer.");
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("\nInvalid number of paramaters. Two Paramaters Required.");
			}

		}
		if (array[0].equalsIgnoreCase("SPEED")) {
			try {
				if (array.length > 2) {
					throw new ArrayIndexOutOfBoundsException(array.length - 1);
				}
				int local1 = Integer.parseInt(array[1]);
				System.out.println("User Speed changed.");
				game.player.speed = local1;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("\nInvalid Speed Variable. Speed was not an Double.");
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("\nInvalid number of paramaters. Two Paramaters Required.");
			}
		}
		if (array[0].equalsIgnoreCase("LEVEL")) {
			System.err.println("System not yet implemented.");
		}
	}
}
