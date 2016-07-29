package com.burksnet.code.games.rain.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles16);
	public static Sprite water = new Sprite(16, 1, 0, SpriteSheet.tiles16);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles16);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite spawn = new Sprite(16, 4, 0, SpriteSheet.tiles16);
	public static Sprite wood = new Sprite(16, 5, 0, SpriteSheet.tiles16);
	public static Sprite arrow = new Sprite(16, 6, 0, SpriteSheet.tiles16);

	public static Sprite playerSouth = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerNorth = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerEast = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerWest = new Sprite(32, 3, 0, SpriteSheet.sprites32);

	// Animation based down here!

	public static Sprite playerSouth_0 = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerNorth_0 = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerEast_0 = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerWest_0 = new Sprite(32, 3, 0, SpriteSheet.sprites32);

	public static Sprite playerSouth_1 = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerNorth_1 = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerEast_1 = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerWest_1 = new Sprite(32, 3, 0, SpriteSheet.sprites32);

	// Weird Direction Sprites

	public static Sprite playerSouthEast = new Sprite(32, 6, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthEast = new Sprite(32, 4, 0, SpriteSheet.sprites32);
	public static Sprite playerSouthWest = new Sprite(32, 7, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthWest = new Sprite(32, 5, 0, SpriteSheet.sprites32);

	public static Sprite playerSouthEast_0 = new Sprite(32, 6, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthEast_0 = new Sprite(32, 4, 0, SpriteSheet.sprites32);
	public static Sprite playerSouthWest_0 = new Sprite(32, 7, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthWest_0 = new Sprite(32, 5, 0, SpriteSheet.sprites32);
	
	public static Sprite playerSouthEast_1 = new Sprite(32, 6, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthEast_1 = new Sprite(32, 4, 0, SpriteSheet.sprites32);
	public static Sprite playerSouthWest_1 = new Sprite(32, 7, 0, SpriteSheet.sprites32);
	public static Sprite playerNorthWest_1 = new Sprite(32, 5, 0, SpriteSheet.sprites32);
	

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	private void setColour(int colour) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}

	}

}
