package com.burksnet.code.games.rain.entity;

import java.util.List;

import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public abstract class Projectile extends Entity{

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;

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

	private void collision() {
		List<Entity> l = level.getEntities();






	}

}
