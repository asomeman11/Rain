package com.burksnet.code.games.rain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.level.tile.WaterTile;
import com.burksnet.code.games.rain.sound.Sound;

public class MyProperties {
	public static boolean dev = false;
	public static boolean music;
	public static boolean sound;
	public static int maxFrames;
	public static boolean remove_projectile_on_collision;
	public static double number_of_particles_on_collision_multipler;
	public static boolean console_input;
	public static int particle_color_int;
	public static boolean can_walk_on_water;
	
	public MyProperties(){
		try {
			File file = new File(System.getProperty("user.dir") + "/data/properties.txt");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				
				parse(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void parse(String key, String value){
		
		if(key.equalsIgnoreCase("MUSIC")){
			MyProperties.music = Boolean.parseBoolean(value);
			if(!music) return;
			try{
				if(!(Sound.mainUrl == null)){
					Sound.start();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(key.equalsIgnoreCase("SOUND")){
			MyProperties.sound = Boolean.parseBoolean(value);
		}
		if(key.equalsIgnoreCase("CAN_WALK_ON_WATER")){
			WaterTile.solid = !Boolean.parseBoolean(value);
			MyProperties.can_walk_on_water = Boolean.parseBoolean(value);
		}
		if(key.equalsIgnoreCase("MAX_FRAMES")){
			MyProperties.maxFrames = Integer.parseInt(value);
			if(maxFrames < 0){
				throw new NumberFormatException("max_frames must be >= 0");
			}
		}
		if(key.equalsIgnoreCase("number_of_particles_on_collision_multipler")){
			MyProperties.number_of_particles_on_collision_multipler = Double.parseDouble(value);
			if(number_of_particles_on_collision_multipler < 0){
				throw new NumberFormatException("number_of_particles_on_collision_multipler must be >= 0");
			}
		}
		if(key.equalsIgnoreCase("particle_color_int")){
			if(value.contains("0x")){
				value = value.replace("0x", "");
				System.out.println(value);
				MyProperties.particle_color_int = Integer.parseInt(value, 16);
			}
			else MyProperties.particle_color_int = Integer.parseInt(value);
			
			Sprite.particleNormal = new Sprite(1, particle_color_int);
			
		}
		if(key.equalsIgnoreCase("REMOVE_PROJECTILE_ON_COLLISION")){
			MyProperties.remove_projectile_on_collision = Boolean.parseBoolean(value);
		}
		if(key.equalsIgnoreCase("DEV")){
			MyProperties.dev = Boolean.parseBoolean(value);
		}
		if(key.equalsIgnoreCase("CONSOLE_INPUT")){
			MyProperties.console_input = Boolean.parseBoolean(value);
		}
	}
	 
}
