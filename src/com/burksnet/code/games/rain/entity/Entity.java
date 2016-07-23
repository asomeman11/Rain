package com.burksnet.code.games.rain.entity;

import java.util.Random;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.level.Level;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		// Remove Entity From Level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
