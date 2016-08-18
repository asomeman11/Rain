package com.burksnet.code.games.rain.console;

import java.io.PrintStream;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.entity.Projectile;
import com.burksnet.code.games.rain.entity.mob.HittableEnemy;
import com.burksnet.code.games.rain.entity.spawner.MobSpawner;
import com.burksnet.code.games.rain.sound.Sound;

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
			if(MyProperties.console_input)
				processCommand(in);
			else
				System.out.println("Console Input Disabled.");
		}
 
	}

	public synchronized void stop() {
		running = false;
	}

	

	private void processCommand(String cmd) {

		String[] array = cmd.split(" ");

		if (array[0].equalsIgnoreCase("PROPERTY")) {
			try{
			if (array.length != 3) {
				throw new ArrayIndexOutOfBoundsException("Property command must have 2 arguments.");
			}
			MyProperties.parse(array[1], array[2]);
			}catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				System.err.println("Property command must have 2 arguments.");
			}
			
		}
		if (cmd.equalsIgnoreCase("QUIT")) {
			game.stop();
		}
		if (cmd.equalsIgnoreCase("ENDSOUND")) {
			System.out.println("Sound Stoped");
			Sound.stop();
		}
		if (cmd.equalsIgnoreCase("CORDS")) {
			System.out.println("(" + game.player.x + ", " + game.player.y + ")");
		}
		if (cmd.equalsIgnoreCase("RESPAWN")) {
			System.out.println("User Respawned.");
			game.player = game.player.respawn();
		}
		if (cmd.equalsIgnoreCase("REMOVE")) {
			game.level.removeAllProjectiles();
		}
		if (cmd.equalsIgnoreCase("KILLMOBS")) {
			game.level.removeAllEntities();
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
		if (array[0].equalsIgnoreCase("SPAWN")) {
			try {
				if (array.length > 4) {
					throw new ArrayIndexOutOfBoundsException(array.length - 1);
				}
				int local1 = Integer.parseInt(array[1]);
				int local2 = Integer.parseInt(array[2]);
				int local3 = Integer.parseInt(array[3]);
				new MobSpawner(local1, local2, null, local3, Game.game.level);
				new HittableEnemy(local1, local2, game.level);
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
				double local1 = Double.parseDouble(array[1]);
				System.out.println("User Speed changed.");
				game.player.speed = local1;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("\nInvalid Speed Variable. Speed was not an Double.");
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("\nInvalid number of paramaters. Three Paramaters Required.");
			}
		}
		if (array[0].equalsIgnoreCase("LEVEL")) {
			System.err.println("System not yet implemented.");
		}
		if (array[0].equalsIgnoreCase("SPEED_PROJECTILE")) {
			try {
				if (array.length > 2) {
					throw new ArrayIndexOutOfBoundsException(array.length - 1);
				}
				double local1 = Double.parseDouble(array[1]);
				System.out.println("Projectile Speed changed.");
				Projectile.projectileSpeed = local1;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("\nInvalid Speed Variable. Speed was not an Double.");
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("\nInvalid number of paramaters. One Paramaters Required.");
			}

	}	
	}
}
