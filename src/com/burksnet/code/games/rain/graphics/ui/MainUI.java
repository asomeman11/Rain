package com.burksnet.code.games.rain.graphics.ui;

import com.burksnet.code.games.rain.entity.Location;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;

public class MainUI extends UserInterface{

	public static Sprite topLeft = new Sprite(250, 15, 0x45A348);
	public static Location topLeftLoc = new Location(0,0);
	
	Sprite[] sprites = new Sprite[]{
		topLeft
	};
	
	Location[] spriteLocation = new Location[]{
		topLeftLoc
	};
	
	public void render(Screen screen){
		for(int i = 0; i < sprites.length; i++){
			
			screen.renderSprite(spriteLocation[i].getX(), spriteLocation[i].getY(), sprites[i], false);
			
		}
	}
	
	public void update(){
		
		//UPDATE STUFF
		
		System.out.println("Update called");
		
	}
	
	
	
}
