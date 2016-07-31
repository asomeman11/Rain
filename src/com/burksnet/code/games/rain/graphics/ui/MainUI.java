package com.burksnet.code.games.rain.graphics.ui;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;

public class MainUI extends UserInterface{

	public static Sprite topLeft = new Sprite(250, 15, 0x45A348);
	
	public void render(Screen screen){
		screen.renderSprite(0, 0, topLeft, false);
		screen.renderText(0, 0, "TESTING");
	}
	
	
	
}
