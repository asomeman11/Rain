package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.graphics.Sprite;

public abstract class Mob extends Entity {

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
		if (!collision()) {
			x += xa * speed;
			y += ya * speed;
		}

	}

	public void update() {
	}

	private boolean collision() {
		return false;
	}

	public void render() {
	}

}
