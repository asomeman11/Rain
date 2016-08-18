package com.burksnet.code.games.rain.util;

import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Extfilemanager {
	private static FileWriter filewrite;
	public static Extfilemanager extfilemanager;
	public static void Properties() throws IOException{
		File Properties = new File("/data/properties.txt");
		if (!Properties.exists()){
			Properties.createNewFile();
			filewrite.write("dev: false" +
				"#above slows game if lots of particles get spawned" +
				"can_walk_on_water: true" +
				"console_input: true" +
				"music: true" +
				"sound: true" +
				"max_frames: 0" +
				"particle_color_int: 0x9a2799" +
				"remove_projectile_on_collision: true" +
				"number_of_particles_on_collision_multipler: 10.0" +
				"#use command 'remove' to remove all projectiles");
	}
	}
}
