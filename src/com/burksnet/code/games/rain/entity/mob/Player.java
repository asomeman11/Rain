package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.input.Keyboard;
import com.burksnet.code.games.rain.level.Level;

public class Player extends Mob {

	// Duplicate @see Mob.moving
	private boolean walking = false;
	private Keyboard input;
	public Sprite sprite = Sprite.playerSouth;
	private int anim = 0;

	public Player(Keyboard input) {

		this.speed = 20;
		this.input = input;
	}

	public Player(int x, int y, Keyboard input) {
		super.bottomOfPlayerCollisionBound = 15;
		super.topOfPlayerCollisionBound = 9;
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void update() {
		if (anim >= 10000)
			anim = 0;
		anim++;
		if (keyboardUpdate()) {
			walking = true;
			directionUpdate();
		} else {
			walking = false;
		}

	}

	public Player respawn() {
		return new Player(input);
	}

	private void directionUpdate() {

		// Make the mod number bigger for slower anim, lower = faster

		if (dir == Direction.NORTH) {
			sprite = Sprite.playerNorth;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerNorth_0;
				} else {
					sprite = Sprite.playerNorth_1;
				}
			}
		} else if (dir == Direction.EAST) {
			sprite = Sprite.playerEast;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerEast_0;
				} else {
					sprite = Sprite.playerEast_1;
				}
			}
		} else if (dir == Direction.SOUTH) {
			sprite = Sprite.playerSouth;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerSouth_0;
				} else {
					sprite = Sprite.playerSouth_1;
				}
			}
		} else if (dir == Direction.WEST) {
			sprite = Sprite.playerWest;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerWest_0;
				} else {
					sprite = Sprite.playerWest_1;
				}
			}
		} else if (dir == Direction.NORTH_EAST) {
			sprite = Sprite.playerEast;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerEast_0;
				} else {
					sprite = Sprite.playerEast_1;
				}
			}
		} else if (dir == Direction.NORTH_WEST) {
			sprite = Sprite.playerEast;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerEast_0;
				} else {
					sprite = Sprite.playerEast_1;
				}
			}
		} else if (dir == Direction.SOUTH_EAST) {
			sprite = Sprite.playerEast;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerEast_0;
				} else {
					sprite = Sprite.playerEast_1;
				}
			}
		} else if (dir == Direction.SOUTH_WEST) {
			sprite = Sprite.playerEast;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerEast_0;
				} else {
					sprite = Sprite.playerEast_1;
				}
			}
		}

	}

	private boolean keyboardUpdate() {
		int xa = 0, ya = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			return true;
		}
		return false;
	}

	public void render(Screen screen) {

		int xx = x - sprite.SIZE / 2;
		int yy = y - sprite.SIZE / 2;

		screen.renderPlayer(xx, yy, sprite);
	}
}
