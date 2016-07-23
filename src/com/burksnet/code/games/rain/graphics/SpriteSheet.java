package com.burksnet.code.games.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;

	// ADD THE THING
	public static SpriteSheet tiles16 = new SpriteSheet("/textures/spritesheet16.png", 256);
	public static SpriteSheet sprites32 = new SpriteSheet("/textures/spritesheet32.png", 256);
	public static SpriteSheet sprites64 = new SpriteSheet("/textures/spritesheet64.png", 256);

	public SpriteSheet(String path, int size) {

		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();

	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
