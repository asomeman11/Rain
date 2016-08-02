package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public class HittableEnemy extends Enemy {

	int difx, dify;
	
	public HittableEnemy(int x, int y, Level level) {
		super(level);
		size = 16;
		this.x = x;
		this.y = y;
		level.add(this);
	}

	public void doDamage(int damage){
	
		heath -= damage;
		System.out.println("Did " + damage  + " damage. Enemy has " + heath + " HP left.");
		if(heath <= 0){
			level.remove(this);
		}
	}
	
	public void update(){

		//double difx = x - Game.game.player.x;
		//double dify = (y - Game.game.player.y);
		
		if(heath <= 0){
			level.remove(this);
		}
		
		if(difx == 0){
			difx = (int) (random.nextGaussian() * 120);
			//System.out.println(((int) (random.nextGaussian() * 30)));
		}
		if(dify == 0){
			//System.out.println(((int) (random.nextGaussian() * 30)));
			dify = (int) (random.nextGaussian() * 120);
			
		}
		
		//System.out.println("y " + dify + " x " + difx);
		
		if(difx < 0){
			difx ++;
		}else{
			difx --;
		}
		if(dify < 0){
			dify ++;
		}
		else dify --;
		
		int tmpX, tmpY;
		
		if(difx < 0){
			tmpX = 1;
		}
		else if(difx > 0){
			tmpX = -1;
		}else tmpX = 0;
		
		if(dify < 0){
			tmpY = 1;
		}
		else if(dify > 0){
			tmpY = -1;
		}else tmpY = 0;
		
		move(tmpX, tmpY);
		
	}
	
	public void render(Screen screen){
		screen.renderTile((int)x, (int)y, Sprite.rock);
	}
	
}
