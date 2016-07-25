package com.burksnet.code.games.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.burksnet.code.games.rain.console.ConsoleManager;
import com.burksnet.code.games.rain.entity.mob.Player;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.input.Keyboard;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.level.SpawnLevel;
import com.burksnet.code.games.rain.menu.Menu;
import com.burksnet.code.games.rain.menu.PauseMenu;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final double VERSION = 0.0;
	private static String title = "Rain";

	private boolean paused = false;
	
	public static int width = 500;
	public static int height = width / 16 * 9;
	public static final int defaultScale = 2;
	private static double currentScale = defaultScale;
	private Dimension size;

	private final double UPDATES_PER_SECOND = 60.0;
	private double maxFramesPerSecond = 0;

	private static ConsoleManager cm;

	private Thread gameThread, consoleThread;
	private JFrame frame;
	private Keyboard key;
	public Level level;
	private BurkFocusListener focus;
	public Player player;
	public Menu pauseMenu;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {

		size = new Dimension(width * defaultScale, height * defaultScale);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		focus = new BurkFocusListener(this);
		// ADD THE THING
		level = new SpawnLevel("/maps/spawn_new.png");
		player = new Player(level.spawnX, level.spawnY, key, level);

		pauseMenu = new PauseMenu(35, 700, 0);
		addKeyListener(key);
		addFocusListener(focus);
	}

	public synchronized void start() {
		running = true;

		consoleThread = new Thread(cm, "Console Manager");
		gameThread = new Thread(this, "Game");
		consoleThread.start();
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;

		cm.stop();

		try {
			frame.dispose();
			gameThread.join();
			consoleThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nsUPS = 1000000000.0 / UPDATES_PER_SECOND;
		double nsFPS = 1000000000.0 / maxFramesPerSecond;
		if (maxFramesPerSecond == 0)
			nsFPS = 0;
		// deltaUPS starts at one to ensure that an update occurs before a
		// render
		double deltaUPS = 1;
		double deltaFPS = 0;
		short frames = 0, updates = 0;
		// Insures that memory for 'now' isnt allocated constantly
		long now;
		requestFocus();
		while (running) {

			now = System.nanoTime();
			deltaUPS += (now - lastTime) / nsUPS;
			deltaFPS += (now - lastTime) / nsFPS;
			lastTime = now;
			while (deltaUPS >= 1) {
				update();
				updates++;
				deltaUPS--;
			}
			if (deltaFPS >= 1) {
				render();
				frames++;
				deltaFPS--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " UPS, " + frames + " FPS");
				frame.setTitle(title + "  ||  " + updates + " UPS " + frames + " FPS");
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

	private void render() {
		// TODO Auto-generated method stub

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2, yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		Graphics g = bs.getDrawGraphics();
		
		//if(paused)
			//pauseMenu.blur(screen, g);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		if(paused){
			pauseMenu.render(screen, g);
		}
		
		g.dispose();
		bs.show();
	}

	private void update() {
		updateIgnorePause();
		if(!paused){
			updateWithPause();
		}
	}

	public static void pause(){
		
	}
	
	private void updateIgnorePause() {
		key.update();
		if(key.paused){
			paused = true;
		}else{
			paused = false;
		}
		if(paused){
			pauseMenu.update();
		}
	}

	private void updateWithPause() {
		player.update();
	}

	public synchronized void resizeWindow(final double scaleChangeFactor) {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		currentScale += scaleChangeFactor;
		if (currentScale < 1)
			currentScale = 1;
		// TODO add check to make sure Dimension size is not bigger than the
		// screen
		// TODO Support might be needed to have large windows on second monitors
		size = new Dimension((int) (width * currentScale), (int) (height * currentScale));
		setWindowSettings();
		gameThread.start();
	}

	private synchronized void setWindowSettings() {
		frame.setVisible(false);
		setPreferredSize(size);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {



		Game game = new Game();
		cm = new ConsoleManager("/data/error.txt", game);
		cm.init(game);

		// Sets Necessary Window Settings such as title
		game.setWindowSettings();

		game.start();
		
	}

	public Keyboard getKeyboard() {
		return key;
	}

	public void pause(boolean b){
		paused = b;
	}
	
}
