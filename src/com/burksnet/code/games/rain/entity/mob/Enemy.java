package com.burksnet.code.games.rain.entity.mob;

import com.burksnet.code.games.rain.level.Level;

public class Enemy extends Mob {

	protected int heath = 100;
	protected int damage;
	
	public Enemy(Level level) {
		super(level);
		
	}

	public void doDamage(int damage){
		System.out.println("Did damage");
		heath -= damage;
		if(heath <= 0){
			//TODO Kill
		}
	}
	
}
