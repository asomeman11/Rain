package com.burksnet.code.games.rain.level.tile;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;

public abstract class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile flower = new GrassTile(Sprite.grass);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile spawn = new SpawnTile(Sprite.spawn);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	// Grass = 0xFF00
	// Flower = 0xFFFF00
	// Rock = 0x7f7f00
	// Water = 0x0000ff
	// SpawnTile 0xffabcedf
	public static final int col_grass = 0xff00ff00;
	public static final int col_flower = 0xffffff00;
	public static final int col_rock = 0xff7f7f00;
	public static final int col_water = 0xff0000ff;
	public static final int col_spawn = 0xffabcdef;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public boolean isSolid() {
		return false;
	}

}
