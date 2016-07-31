package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.entity.ArrowProjectile;
import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.entity.Projectile;
import com.burksnet.code.games.rain.entity.StrongProjectile;
import com.burksnet.code.games.rain.entity.particle.Particle;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public abstract class Mob extends Entity {

	// Remember this is the difference from the middle to the outer bound
	protected int topOfMobCollisionBound = 15;
	protected int bottomOfMobCollisionBound = 15;

	public Mob(Level level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	protected Sprite sprite;
	protected Direction dir = Direction.NORTH;
	protected boolean moving = false;

	public double speed = 1;



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

		if(xa != 0 && ya != 0){
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
		if (!collision(((int)(x + xb)), ((int)(y + yb))) && !collision((int)(x + xb), (int)(y + yb + bottomOfMobCollisionBound)) && !collision((int)(x + xb), (int)(y + yb - topOfMobCollisionBound))) {
			x += xb;
			y += yb;
		}else{
			//level.add(new Particle((int)x, (int)y, 50, 5000, level));
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
