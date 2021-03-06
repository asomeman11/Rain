package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.entity.ArrowProjectile;
import com.burksnet.code.games.rain.entity.Location;
import com.burksnet.code.games.rain.entity.Projectile;
import com.burksnet.code.games.rain.entity.StrongProjectile;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.input.Keyboard;
import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.sound.Sound;

public class Player extends Mob {

	// Duplicate @see Mob.moving
	private boolean walking = false;
	private Keyboard input;
	public Sprite sprite = Sprite.playerSouth;
	private int anim = 0;
	private Mouse mouse;

	Projectile p;
	private int fireRate = 0;

	public Player(Keyboard input, Level level) {
		this(0, 0, input, level);
	}

	public Player(int x, int y, Keyboard input, Level level) {
		super(level);
		size = 32;
		

		super.bottomOfMobCollisionBound = 15;
		super.topOfMobCollisionBound = 0;

		this.x = x;
		this.y = y;
		this.input = Game.game.getKeyboard();
		mouse = input.getMouse();
	}

	public Player(Location location, Keyboard input, Level level) {
		this(location.getX(), location.getY(), input, level);
	}

	public void update() {

		if(fireRate > 0) fireRate--;

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

		clear();

	}

	public void move(int xa, int ya) {

		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (xa > 0)
			dir = Direction.EAST;
		if (xa < 0)
			dir = Direction.WEST;
		if (ya > 0)
			dir = Direction.SOUTH;
		if (ya < 0)
			dir = Direction.NORTH;
		double xb = xa * speed, yb = ya * speed;
		if (!playerCollision(x, y, xb, yb, 16, 16)) {
			x += xb;
			y += yb;
		} else {
			// level.add(new Particle((int)x, (int)y, 50, 5000, level));
		}

	}
	
	private boolean playerCollision(double x, double y, double xb, double yb, int width, int height) {
		boolean collision = false;
		
		int nx = (int) (x + xb), ny = (int) (y + yb);
		
		width /= 2;
		height /= 2;
		
		if (level.getTile((nx - width) / 16, ny / 16).isSolid()){
			collision = true;
		}
		if (level.getTile((nx + width) / 16, ny / 16).isSolid())
			collision = true;
		if (level.getTile(nx / 16, (ny + height + 7) / 16).isSolid())
			collision = true;
		if (level.getTile(nx / 16, (ny - height + 8) / 16).isSolid())
			collision = true;
		if (level.getTile((nx + width) / 16, (ny + height) / 16).isSolid())
			collision = true;
		if (level.getTile((nx - width) / 16, (ny - height) / 16).isSolid())
			collision = true;
		if (level.getTile((nx - width) / 16, (ny + height) / 16).isSolid())
			collision = true;
		if (level.getTile((nx + width) / 16, (ny - height) / 16).isSolid())
			collision = true;
		
		return collision;
	}

	private void clear() {
		for(int i = 0; i < level.getProjectiles().size(); i ++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()){
				level.getProjectiles().remove(i);
			}
		}

	}

	private void mouseUpdate() {
		if (mouse.left && fireRate <= 0) {
			System.out.println("Left");

			Sound.playSoundOnce("click.wav");

			double dx = (Mouse.getX() - Game.width * Game.defaultScale/2) - 0;
			double dy = (Mouse.getY() - Game.height * Game.defaultScale/2) - 0;



			double dir = Math.atan2(dy, dx);
			for(int i = 0; i < 1; i ++){
				shootNormal(x, y, dir);
				fireRate = ArrowProjectile.RATE_OF_FIRE;
			}
		}
		if (mouse.middle) {
			System.out.println("Middle");
		}
		if (mouse.right && fireRate <= 0) {
			Sound.playSoundOnce("click.wav");

			double dx = (Mouse.getX() - Game.width * Game.defaultScale/2) - 0;
			double dy = (Mouse.getY() - Game.height * Game.defaultScale/2) - 0;



			double dir = Math.atan2(dy, dx);
			for(int i = 0; i < 1; i ++){
				shootStrong(x, y, dir);
				fireRate = StrongProjectile.RATE_OF_FIRE;
			}
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
