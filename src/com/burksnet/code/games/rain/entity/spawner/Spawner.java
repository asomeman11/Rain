package com.burksnet.code.games.rain.entity.spawner;

import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.entity.particle.Particle;
import com.burksnet.code.games.rain.level.Level;

public class Spawner extends Entity{

	public enum Type{
		MOB(), PARTICLE();
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		super(level);
		this.x = x;
		this.y = y;
		this.type = type;
		
		
		
	}
	
	public Spawner(double x, double y, Type type, int amount, Level level) {
		this((int) x, (int) y, type, amount, level);
	}

	public void update(){
		
	}
	
	public void render(){
		
	}
	
}
