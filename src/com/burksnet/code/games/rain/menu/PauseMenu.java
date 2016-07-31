package com.burksnet.code.games.rain.menu;

import java.awt.Graphics;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.sound.Sound;

public class PauseMenu extends Menu{

	public PauseMenu(int updatesTillCentered, int xPixelOffset, int yPixelOffset){
		super(updatesTillCentered, xPixelOffset, yPixelOffset, new BlurMenuGraphic());
	}
	
	public void update(){
		//400 - 700
		if(Mouse.left){
			if(Mouse.getY() > 230 && Mouse.getY() <= 280){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					if(MyProperties.music){
						Sound.stop();
					}
					else Sound.start();
				}
			}
			
			if(Mouse.getY() > 310 && Mouse.getY() <= 360){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					MyProperties.sound = !MyProperties.sound;
				}
			}
			
			if(Mouse.getY() > 390 && Mouse.getY() <= 440){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					Game.game.stop();
				}
			}
			
		}
	}

	public void render(Screen screen, Graphics g) {
		
		//System.out.println("Caleld");
		
		//screen.renderSprite(0, 0, Sprite.wood, false);
		super.render(screen, g);
		
		
	}

}
