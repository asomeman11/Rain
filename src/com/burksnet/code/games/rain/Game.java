package com.burksnet.code.games.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.burksnet.code.games.rain.console.ConsoleManager;
import com.burksnet.code.games.rain.entity.mob.Player;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.ui.MainUI;
import com.burksnet.code.games.rain.graphics.ui.UserInterface;
import com.burksnet.code.games.rain.input.Keyboard;
import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.level.SpawnLevel;
import com.burksnet.code.games.rain.menu.MainMenu;
import com.burksnet.code.games.rain.menu.MenuManager;
import com.burksnet.code.games.rain.sound.Sound;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final double VERSION = 1.0;
	private static String title = "Rain";

	

	private UserInterface ui;
	
	public static int width = 500;
	public static int height = width / 16 * 9;
	public static final int defaultScale = 2;
	private static double currentScale = defaultScale;
	private Dimension size;

	private static final int UPDATES_PER_SECOND = 60;
	private static int maxFramesPerSecond = 0;

	private static ConsoleManager cm;
	public static Game game;

	private Thread gameThread, consoleThread;
	private JFrame frame;
	private Keyboard key;
	public Level level;
	private BurkFocusListener focus;
	public Player player;
	public Sound sound;
	private boolean running = false;
	private boolean started = false;
	
	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Mouse mouse;
	private boolean fadeToBlack;
	private int timeTillBlack;
	private boolean fadeToBlackGo;

	public Game() {

		game = this;
		
		ui = new MainUI();
		size = new Dimension(width * defaultScale, height * defaultScale);
		screen = new Screen(width, height);
		frame = new JFrame();
		mouse = new Mouse();
		key = new Keyboard(mouse);
		focus = new BurkFocusListener(this);
		// ADD THE THING
		level = new SpawnLevel("/maps/spawn_new.png");
		player = new Player(level.getSpawnLocation(), key, level);

		addListeners();

	}

	private void addListeners() {

		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				stop();
			}
		});

		frame.addComponentListener(new ComponentListener() 
		{  
			@Override
			public void componentMoved(ComponentEvent evt) {
				game.pause(true);
				key.releaseAll();
				mouse.releaseAll();
			}

			@Override
			public void componentResized(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		}
				);


		addKeyListener(key);
		addFocusListener(focus);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

	}

	public synchronized void start() {
		MenuManager.addMenu(new MainMenu());
		running = true;

		consoleThread = new Thread(cm, "Console Manager");
		gameThread = new Thread(this, "Game");
		consoleThread.start();
		gameThread.start();
		Sound.playSoundForever("main.wav");
	}

	public void startGame(){
		started = true;
	}
	
	public synchronized void stop() {
		System.out.println("Application Terminated.");
		finalExit();
	}

	private synchronized void finalExit(){
		
		running = false;
		Sound.stop();
		cm.stop();
		System.exit(0);
		
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
		Graphics g = bs.getDrawGraphics();

		screen.init(g);
		
		if(!MenuManager.isActive()){
			screen.clear();
			double xScroll = player.x - screen.width / 2, yScroll = player.y - screen.height / 2;
			level.render((int)xScroll, (int)yScroll, screen);
			player.render(screen);
			ui.render(screen);

			// if(paused)
			// pauseMenu.blur(screen, g);

			

			//g.setColor(Color.BLUE);
			//g.fillRect(0, 0, getWidth(), getHeight());




		}
		
		
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		if(fadeToBlack && fadeToBlackGo){
			for(int i = 0; i < pixels.length; i++){
				Color c = new Color(pixels[i]);
				
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				
				red -= 1/30 * red;
				green-= 1/30 * green;
				blue -= 1/30 * blue;
				
				pixels[i] = new Color(red, green, blue).getRGB();
				fadeToBlackGo = false;
			}
		}
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//TODO Move this up once its ready
		MenuManager.render(screen, g);
		g.dispose();
		bs.show();
	}

	private void update() {

		
		
		if(fadeToBlack){
			if(timeTillBlack == 0){
				finalExit();
			}
			timeTillBlack--;
			fadeToBlackGo = true;
		}
		
		
		
		key.update();
		mouse.update();

		
		updateIgnoreMenu();
		if (!MenuManager.isActive()) {
			updateWithoutMenu();
		}
		mouse.releaseAll();
	}

	//Constant
	private void updateIgnoreMenu() {
		MenuManager.update();
	}

	//No Menu active
	private void updateWithoutMenu() {
		player.update();
		level.update();
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
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		MyProperties p = new MyProperties();

		Game.maxFramesPerSecond = MyProperties.maxFrames;

		Game game = new Game();
		cm = new ConsoleManager("/data/error.txt", game);

		// Sets Necessary Window Settings such as title
		game.setWindowSettings();

		game.start();

	}

	public Keyboard getKeyboard() {
		return key;
	}

	public Mouse getMouse(){
		return mouse;
	}

	public void pause(boolean b) {
		if(!started)
			return;
		key.paused = b;
	}

	public void regenGen() {
		level = new SpawnLevel("/maps/spawn_new.png");
		player = new Player(level.getSpawnLocation(), key, level);
		
	}

}
