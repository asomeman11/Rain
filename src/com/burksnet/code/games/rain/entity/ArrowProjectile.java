package com.burksnet.code.games.rain.entity;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.entity.mob.Mob;
import com.burksnet.code.games.rain.entity.spawner.ParticleSpawner;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;

public class ArrowProjectile extends Projectile {

	public static final int RATE_OF_FIRE = 10;

	public ArrowProjectile(double x, double y, double dir, Level level) {
		super(x, y, dir, level);
		range = 20 * 16;
		speed = 5 * projectileSpeed;
		damage = 20;
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

	protected void move(){
		if(level.tileCollision(x, y, nx, ny, 8)){

			if(!spawnedParticle){

				level.add(new ParticleSpawner(x + 8, y + 8, 5, (int) (10 * MyProperties.number_of_particles_on_collision_multipler), level));
				spawnedParticle = true;
			}
			if(MyProperties.remove_projectile_on_collision){
				level.remove(this);
			}
			return;
		}

		Mob m = collision(x, y, nx, ny);

		if(m != null){
			m.doDamage((int) this.damage);
			level.remove(this);
		}

		super.move();
	}

}
