package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.level.tile.Tile;

public abstract class Mob extends Entity {

	protected int topOfPlayerCollisionBound = 15;
	protected int bottomOfPlayerCollisionBound = 15;
	protected Sprite sprite;
	protected Direction dir = Direction.NORTH;
	protected boolean moving = false;
	
	public double speed = 1;

	public void move(int xa, int ya) {

		if (xa > 0)
			dir = Direction.EAST;
		if (xa < 0)
			dir = Direction.WEST;
		if (ya > 0)
			dir = Direction.SOUTH;
		if (ya < 0)
			dir = Direction.NORTH;
		int xb = xa * speed, yb = ya * speed; 
		if (!collision((x + xb), (y + yb)) && !collision((x + xb), (y + yb + bottomOfPlayerCollisionBound)) && !collision((x + xb), (y + yb - topOfPlayerCollisionBound))) {
			x += xb;
			y += yb;
		}

	}

	public void update() {
	}

	private boolean collision(int xPix, int yPix) {
		
		if(level.getTile(xPix / 16, yPix / 16).isSolid() || xPix < 0 || yPix < 0)
			return true;
		
		return false;
	}

	public void render() {
	}

}
