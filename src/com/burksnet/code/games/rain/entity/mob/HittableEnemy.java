package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public class HittableEnemy extends Enemy {

	public HittableEnemy(int x, int y, Level level) {
		super(level);
		this.x = x;
		this.y = y;
		level.add(this);
	}

	public void doDamage(int damage){
		heath -= damage;
		if(heath <= 0){
			//TODO Kill
		}
	}
	
	public void update(){
		move((int)Math.round(Math.random()), (int)Math.round(Math.random()));
	}
	
	public void render(Screen screen){
		screen.renderTile((int)x, (int)y, Sprite.voidSprite);
	}
	
}
