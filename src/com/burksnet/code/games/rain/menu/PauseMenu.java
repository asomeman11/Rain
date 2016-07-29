package com.burksnet.code.games.rain.menu;

import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.sound.Sound;

public class PauseMenu extends Menu{

	public PauseMenu(int updatesTillCentered, int xPixelOffset, int yPixelOffset){
		super(updatesTillCentered, xPixelOffset, yPixelOffset, new BlurMenuGraphic());
	}
	
	public void update(){
		//400 - 700
		if(Mouse.left){
			if(Mouse.getY() > 250 && Mouse.getY() <= 300){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					if(Sound.running){
						Sound.stop();
					}
					else Sound.start();
				}
			}
		}
	}

	public void render(){
		
	}

}
