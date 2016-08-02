package com.burksnet.code.games.rain.entity.spawner;

import com.burksnet.code.games.rain.entity.mob.HittableEnemy;
import com.burksnet.code.games.rain.level.Level;

public class MobSpawner extends Spawner{

	public MobSpawner(double x, double y, Type type, int amount, Level level) {
		super(x, y, type, amount, level);
		
		for(int i = 0; i < amount; i++){
			level.add(new HittableEnemy((int)x, (int)y, level));
		}
		
	}

	
	
}
