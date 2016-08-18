package com.burksnet.code.games.rain.graphics;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.text.JTextComponent;

import com.burksnet.code.games.rain.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;

	private final int mapSize = 128;
	private final int mapSizeMask = mapSize - 1;

	private Graphics graphics;
	
	public int xOffset, yOffset;
	
	public JTextComponent Chat;

	public int[] tiles = new int[mapSize * mapSize]; // 4096

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		// Map Gen
		for (int i = 0; i < mapSize * mapSize; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}

		tiles[0] = 0x000000;

	}

	public Screen(int width, int height, Graphics g){
		this(width, height);
		graphics = g;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Sprite sprite) {
		yp -= yOffset;
		xp -= xOffset;
		for (int y = 0; y < sprite.SIZE; y++) { 
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xffff00ff)
					pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}

	}

	public void renderText(int xp, int yp, String s){
		
		//Maniplulate pixels array;

	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		renderTile(xp, yp, tile.sprite);
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}

	}

	public void renderSprite(double xp, double yp, Sprite sprite, boolean fixed){
		renderSprite((int) xp, (int) yp, sprite, fixed);
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		yp -= yOffset;
		xp -= xOffset;
		int size = sprite.SIZE;
		for (int y = 0; y < size; y++) {
			int ya = y + yp;
			for (int x = 0; x < size; x++) {
				int xa = x + xp;
				if (xa < -size || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[x + y * size];
				if (col != 0xffff00ff)
					pixels[xa + ya * width] = sprite.pixels[x + y * size];
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void init(Graphics g) {
		graphics = g;
		
	}


}
