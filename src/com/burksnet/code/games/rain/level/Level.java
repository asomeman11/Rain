package com.burksnet.code.games.rain.level;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected String path;

	protected int[] tiles;

	// Rand Gen
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	protected void generateLevel() {
		System.out.println("level gen called");
	}

	// Primary Gen
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	public void update() {
	}

	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		// TODO fix 16 not being compatible with other stuff!
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);

			}
		}

	}

	// Grass = 0xFF00
	// Flower = 0xFFFF00
	// Rock = 0x7f7f00
	// Water = 0x0000ff
	
	//TODO Remake this getTile Method. Seems inefficient. May not be.
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			// System.out.println("void");
			return Tile.voidTile;
		}

		if (tiles[x + y * width] == Tile.col_grass) {
			// System.out.println("grass");
			return Tile.grass;
		}

		if (tiles[x + y * width] == Tile.col_water) {
			// System.out.println("water");
			return Tile.water;
		}
		if (tiles[x + y * width] == Tile.col_rock) {
			// System.out.println("water");
			return Tile.rock;
		}

		return Tile.voidTile;
	}

}
