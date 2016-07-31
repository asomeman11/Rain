package com.burksnet.code.games.rain.entity.particle;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public class Particle extends Entity {

	private Sprite sprite;
	private int life;

	protected double xa, ya;

	public Particle(int x, int y, int life, Level level) {
		super(level);
		if(MyProperties.dev)
			System.out.println("Particle Life: " + life);
		
		sprite = Sprite.particleNormal;
		this.x = x;
		this.y = y;
		this.life = life;

		this.xa = random.nextGaussian()/2;
		this.ya = random.nextGaussian() /2;

	}

	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, true);
	}

	public void update() {
		this.x += xa;
		this.y += ya;
		if(life <= 0){
			level.remove(this);
		}
		life--;
	}

}
