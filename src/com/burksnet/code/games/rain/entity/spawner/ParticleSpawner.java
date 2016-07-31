package com.burksnet.code.games.rain.entity.spawner;

import com.burksnet.code.games.rain.entity.particle.Particle;
import com.burksnet.code.games.rain.level.Level;

public class ParticleSpawner extends Spawner{

	private int life;
	
	public ParticleSpawner(double x, double y, int life, int amount, Level level) {
		
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		
		for(int i = 0; i < amount; i++){
			level.add(new Particle((int)x,(int) y, life + random.nextInt(25), level));
			
		}
		
	}

}
