package com.burksnet.code.games.rain.entity;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public class ArrowProjectile extends Projectile {

	public ArrowProjectile(double x, double y, double dir, Level level) {
		super(x, y, dir, level);
		range = 500;
		speed = 5;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.arrow;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
	}

	public void update(){
		move();
	}
	
	public void render(Screen screen){
		screen.renderTile((int)x, (int)y, sprite);
	}
	
}
