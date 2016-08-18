package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.entity.ArrowProjectile;
import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.entity.Projectile;
import com.burksnet.code.games.rain.entity.StrongProjectile;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public abstract class Mob extends Entity {

	// Remember this is the difference from the middle to the outer bound
	protected int topOfMobCollisionBound = 15;
	protected int bottomOfMobCollisionBound = 15;
	protected int size;

	public Mob(Level level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	protected Sprite sprite;
	protected Direction dir = Direction.NORTH;
	protected boolean moving = false;
	public static boolean frezee_mobs;
	public static double speed = 1;

	public void shootNormal(double x, double y, double dir) {

		System.out.println("Angle: " + Math.toDegrees(dir));

		Projectile p = new ArrowProjectile(x - 8, y - 8, dir, level);
		level.add(p);

	}

	public void shootStrong(double x, double y, double dir) {

		System.out.println("Angle: " + Math.toDegrees(dir));

		Projectile p = new StrongProjectile(x - 8, y - 8, dir, level);
		level.add(p);

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
		if (!mobCollision(x, y, xb, yb, 16, 16)) {
			x += xb;
			y += yb;
		} else {
			// level.add(new Particle((int)x, (int)y, 50, 5000, level));
		}

	}

	private boolean mobCollision(double x, double y, double xa, double ya, int width, int height) {
		boolean collision = false;

		double nx = x + xa, ny = y + ya;

		if (level.getTile(nx / 16, ny / 16).isSolid())
			collision = true;
		if (level.getTile((nx + width) / 16, ny / 16).isSolid())
			collision = true;
		if (level.getTile(nx / 16, (ny + height) / 16).isSolid())
			collision = true;
		if (level.getTile((nx + width) / 16, (ny + height) / 16).isSolid())
			collision = true;

		return collision;
	}

	public void update() {

	}

	public void doDamage(int dmg){
		
	}
	
	private boolean collision(int xPix, int yPix) {

		if (level.getTile(xPix / 16, yPix / 16).isSolid() || xPix < 0 || yPix < 0)
			return true;

		return false;
	}

	public void render() {
	}

}
