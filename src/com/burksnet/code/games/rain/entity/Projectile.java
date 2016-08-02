package com.burksnet.code.games.rain.entity;

import com.burksnet.code.games.rain.entity.mob.Mob;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public abstract class Projectile extends Entity{

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	public static double projectileSpeed = 1;
	public static double strong_projectile_rof = 1;
	protected boolean spawnedParticle = false;

	public Projectile(double x, double y, double dir, Level level) {
		super(level);
		xOrigin = x;
		yOrigin = y;
		this.x = x; 
		this.y = y;
		angle = dir;
	}

	protected void move(){
		x += nx;
		y += ny;

		if(Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)))>range)
			//if(Math.abs(xOrigin - x) + Math.abs(yOrigin - y) > range)
			level.remove(this);

		//collision();

	}

	protected Mob collision(double x, double y, double xa, double ya){
		
		Entity e = level.getEntity(x + xa, y + ya);
		
		if(e instanceof Mob){
			return (Mob) e;
		}
		
		return null;
	}

}
