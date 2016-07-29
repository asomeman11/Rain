package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.entity.Location;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.input.Keyboard;
import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.level.tile.Tile;
import com.burksnet.code.games.rain.sound.Sound;

public class Player extends Mob {

	// Duplicate @see Mob.moving
	private boolean walking = false;
	private Keyboard input;
	public Sprite sprite = Sprite.playerSouth;
	private int anim = 0;
	private Mouse mouse;

	public Player(Keyboard input, Level level) {
		this(0, 0, input, level);
	}

	public Player(int x, int y, Keyboard input, Level level) {
		super(level);
		mouse = input.getMouse();

		super.bottomOfMobCollisionBound = 15;
		super.topOfMobCollisionBound = 0;

		this.x = x;
		this.y = y;
		this.input = input;
	}

	public Player(Location location, Keyboard input, Level level) {
		this(location.getX(), location.getY(), input, level);
	}

	public void update() {
		if (anim >= 10000)
			anim = 0;
		anim++;
		mouseUpdate();
		if (keyboardUpdate()) {
			walking = true;
			directionUpdate();
		} else {
			walking = false;
		}

	}

	private void mouseUpdate() {
		if (mouse.left) {
			System.out.println("Left");
			
			Sound.playSoundOnce("click.wav");
			
			double dx = (Mouse.getX() - Game.width * Game.defaultScale/2) - 12;
			double dy = (Mouse.getY() - Game.height * Game.defaultScale/2) - 8;
			
			
			
			double dir = Math.atan2(dy, dx);
			for(int i = 0; i < 1; i ++){
				shoot(x, y, dir);
			}
		}
		if (mouse.middle) {
			System.out.println("Middle");
		}
		if (mouse.right) {
			System.out.println("Right");
		}
	}

	public Player respawn() {
		return new Player(level.getSpawnLocation(), input, level);
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

		double xx = x - sprite.SIZE / 2;
		double yy = y - sprite.SIZE / 2;

		screen.renderPlayer((int)xx, (int)yy, sprite);
	}

	public void setLocation(Location location) {
		setLocation(location.getX(), location.getY());
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
