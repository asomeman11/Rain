package com.burksnet.code.games.rain.entity;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.entity.mob.Mob;
import com.burksnet.code.games.rain.entity.spawner.ParticleSpawner;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.Level;
import com.burksnet.code.games.rain.level.tile.Tile;

public class StrongProjectile extends Projectile {

	public static final int RATE_OF_FIRE = 60;
	
	public StrongProjectile(double x, double y, double dir, Level level) {
		super(x, y, dir, level);
		range = 25 * 16;
		speed = 3 * projectileSpeed;
		damage = 100;
		sprite = Sprite.bomb;
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
		if(level.tileCollision(x, y, nx, ny, 10)){

			int xa = (int) (x + nx);
			int ya = (int) (y + ny);
			
			System.out.println(xa + ", " + ya);
			
			
			
			
			
			if(level.getTile((x + nx) / 16, (y + ny) / 16).breakable()){
				level.setTile(xa / 16, ya / 16, Tile.col_grass);
			}
			
			if(level.getTile((x + nx + 16) / 16, (y + ny) / 16).breakable()){
				level.setTile((xa + 16) / 16, ya / 16, Tile.col_grass);
			}
			
			if(level.getTile((x + nx) / 16, (y + ny + 16) / 16).breakable()){
				level.setTile(xa / 16, (ya + 16) / 16, Tile.col_grass);
			}
			
			if(level.getTile((x + nx + 16) / 16, (y + ny + 16) / 16).breakable()){
				level.setTile((xa + 16) / 16, (ya + 16) / 16, Tile.col_grass);
			}
			
			if(!spawnedParticle){
				
				level.add(new ParticleSpawner(x + 8, y + 8, 5, (int) (100 * MyProperties.number_of_particles_on_collision_multipler), level));
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
