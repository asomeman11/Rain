package com.burksnet.code.games.rain.level.tile;

import com.burksnet.code.games.rain.graphics.Sprite;

public class WallTile extends Tile {

	public WallTile(Sprite sprite) {
		super(sprite);
	}

	public boolean isSolid() {
		return true;
	}

	public boolean breakable() {
		return true;
	}
	
}
