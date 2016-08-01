package com.burksnet.code.games.rain.graphics.ui;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;

public class UserInterface {

	private boolean active;
	
	public static UserInterface mainUI = new MainUI();
	
	Sprite[] sprites = new Sprite[]{
			
		};
	
	public UserInterface(){
		
		
		
	}
	
	public void render(Screen screen){
		
	}
	
	public void update(){
		
	}
	
	public boolean isActive(){
		return active;
	}
	
}
