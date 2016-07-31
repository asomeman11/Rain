package com.burksnet.code.games.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.entity.Entity;
import com.burksnet.code.games.rain.entity.Location;
import com.burksnet.code.games.rain.entity.Projectile;
import com.burksnet.code.games.rain.entity.particle.Particle;
import com.burksnet.code.games.rain.entity.spawner.Spawner;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected String path;

	private Location spawnLocation = new Location(0, 0);

	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}

	// Rand Gen
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
		findSpawn(Tile.col_spawn);
	}

	protected void generateLevel() {
		System.out.println("level gen called");
	}

	// Primary Gen
	public Level(String path) {
		loadLevel(path);
		generateLevel();
		findSpawn(Tile.col_spawn);
		
		add(new Spawner(16 * 16, 62 * 16, Spawner.Type.PARTICLE, 50, this));
		
	}

	// Relies on a Tile being 16*16d
	protected void findSpawn(int col) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tiles[x + y * width] == Tile.col_spawn) {
					spawnLocation = new Location((x * 16) + 8, (y * 16));
					// spawnX = x*16;
					// spawnY = y*16;
					// In future I might make it so multiple SpawnTiles can be
					// found. At the moment that isn't the case.
					return;
				}
			}
		}

	}

	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++){
			double xt = (((int)x + (int)xa) + c % 2 * size  + 3) / 16;
			double yt = (((int)y + (int)ya) + c / 2 * size + 3D) / 16;
			if(getTile((int)xt, (int)yt).isSolid()) solid = true;
		}
		return solid;
		
	}

	protected void loadLevel(String path) {
	}

	public void update() {

		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}

		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}

		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		
	}

	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		// TODO fix 16 not being compatible with other stuff!
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
			int y1 = (yScroll + screen.height + 16) >> 4;

			for (int y = y0; y < y1; y++) {
				for (int x = x0; x < x1; x++) {
					getTile(x, y).render(x, y, screen);

				}
			}

			for(int i = 0; i < entities.size(); i++){
				entities.get(i).render(screen);
			}

			for(int i = 0; i < projectiles.size(); i++){
				projectiles.get(i).render(screen);
			}

			for(int i = 0; i < particles.size(); i++){
				particles.get(i).render(screen);
			}
			
	}

	// Grass = 0xFF00
	// Flower = 0xFFFF00
	// Rock = 0x7f7f00
	// Water = 0x0000ff

	// TODO Remake this getTile Method. Seems inefficient. May not be.

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		}
		if (tiles[x + y * width] == Tile.col_grass) {
			return Tile.grass;
		}
		if (tiles[x + y * width] == Tile.col_water) {
			return Tile.water;
		}
		if (tiles[x + y * width] == Tile.col_rock) {
			return Tile.rock;
		}
		if (tiles[x + y * width] == Tile.col_spawn) {
			return Tile.spawn;
		}
		if (tiles[x + y * width] == Tile.col_wood) {
			return Tile.wood;
		}

		return Tile.voidTile;
	}

	public void add(Entity e){
		entities.add(e);	
	}

	public void add(Particle e){
		particles.add(e);	
	}
	
	public void add(Projectile p){
		projectiles.add(p);
	}

	public void remove(Entity e) {
		entities.remove(entities.indexOf(e));
		if(MyProperties.dev)
			System.out.println("Entity " + e + " was removed");
	}

	public void remove(Projectile p){
		projectiles.remove(projectiles.indexOf(p));
		if(MyProperties.dev)
			System.out.println("Projectile " + p + " was removed");
	}

	public void remove(Particle e) {
		particles.remove(particles.indexOf(e));
		if(MyProperties.dev)
			System.out.println("Particle " + e + " was removed");
	}
	
	public List<Entity> getEntities() {

		return entities;
	}

	public List<Projectile> getProjectiles(){
		return projectiles;
	}

	public void removeAllEntities(){
		entities = new ArrayList<Entity>();
	}
	public void removeAllProjectiles(){
		projectiles = new ArrayList<Projectile>();
	}
	
}
