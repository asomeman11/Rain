package com.burksnet.code.games.rain.menu;

import java.awt.Font;
import java.awt.Graphics;

import com.burksnet.code.games.rain.graphics.Screen;

public class Menu {

	private int xOffset, yOffset;
	// private int updatesTillCentered;
	private int xMov, yMov;

	private int finalX = 300, finalY = 220;

	protected final byte SHADOW_OFFSET = 4; 
	
	public Menu() {

		// this.updatesTillCentered = updatesTillCentered;
		//xOffset = xPixelOffset;
		//yOffset = yPixelOffset;
		//xMov = xOffset / updatesTillCentered;
		//yMov = yOffset / updatesTillCentered;

	}

	public void update() {

		if (xOffset > xMov) {
			xOffset -= xMov;
		} else
			xOffset = 0;
		if (yOffset > yMov) {
			yOffset -= yMov;
		} else
			yOffset = 0;

	}

	protected Font f = new Font("Arial", 0, 64);
	
	public void render(Screen screen, Graphics g) {
	}

	public void blur(Screen screen, Graphics g) {

		
		
		int width = screen.width;
		int height = screen.height;

		int oldPix[] = new int[screen.pixels.length];
		int newPix[] = new int[screen.pixels.length];
		
		for(int i = 0; i < screen.pixels.length; i++){
			
			oldPix[i] = screen.pixels[i];
			
		}
		
		//int newPix[] = new int[oldPix.length];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				short indexesUsed = 0;
				int sum = 0;
				try{
				sum += oldPix[(x - 1) + (y - 1) * 3] * 1;
				//System.out.println("Checking pos " + (x - 1) + ", " + (y - 1) + ".");
				sum += oldPix[(x - 0) + (y - 1) * 3] * 1;
				//System.out.println("Checking pos " + (x - 0) + ", " + (y - 1) + ".");
				sum += oldPix[(x + 1) + (y - 1) * 3] * 1;
				//System.out.println("Checking pos " + (x + 1) + ", " + (y - 1) + ".");
				sum += oldPix[(x - 1) + (y - 0) * 3] * 1;
				//System.out.println("Checking pos " + (x - 1) + ", " + (y - 0) + ".");
				sum += oldPix[(x - 0) + (y - 0) * 3] * 1;
				//System.out.println("Checking pos " + (x - 0) + ", " + (y - 0) + ".");
				sum += oldPix[(x + 1) + (y - 0) * 3] * 1;
				//System.out.println("Checking pos " + (x + 1) + ", " + (y - 0) + ".");
				sum += oldPix[(x - 1) + (y + 1) * 3] * 1;
				//System.out.println("Checking pos " + (x - 1) + ", " + (y + 1) + ".");
				sum += oldPix[(x - 0) + (y + 1) * 3] * 1;
				//System.out.println("Checking pos " + (x - 0) + ", " + (y + 1) + ".");
				sum += oldPix[(x + 1) + (y + 1) * 3] * 1;
				//System.out.println("Checking pos " + (x + 1) + ", " + (y + 1) + ".");
				
				//System.out.println("Sum of it is " + sum + "\nAveraged is " + sum / 9);
				
				
				newPix[x + y * width] = sum / 9;
				}catch( ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}
				
				newPix[x + y * width] = sum / 9;
				
				 sum = 0;
				//newPix[x + y * width] = sum / 9;
			}
		}

		//screen.pixels = new int[newPix.length];
		
		for(int i = 0; i < newPix.length; i++){
			screen.pixels[i] = newPix[i];
		}
		
	}

}
