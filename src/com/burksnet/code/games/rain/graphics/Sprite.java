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

	public static Sprite playerBack = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerForward = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerRight = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerLeft = new Sprite(32, 3, 0, SpriteSheet.sprites32);

	// Animation based down here!

	public static Sprite playerBack_0 = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerForward_0 = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerRight_0 = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerLeft_0 = new Sprite(32, 3, 0, SpriteSheet.sprites32);

	public static Sprite playerBack_1 = new Sprite(32, 0, 0, SpriteSheet.sprites32);
	public static Sprite playerForward_1 = new Sprite(32, 1, 0, SpriteSheet.sprites32);
	public static Sprite playerRight_1 = new Sprite(32, 2, 0, SpriteSheet.sprites32);
	public static Sprite playerLeft_1 = new Sprite(32, 3, 0, SpriteSheet.sprites32);

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
