package com.burksnet.code.games.rain.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.input.Mouse;

public class PauseMenu extends Menu{
	
	public void update(){
		//400 - 700
		if(Mouse.left){
			
			if(Mouse.getY() > 50 && Mouse.getY() <= 100){
				if(Mouse.getX() > 400 && Mouse.getX() <= 620){
					MenuManager.removeMenu();
				}
			}
			if(Mouse.getY() > 150 && Mouse.getY() <= 200){
				if(Mouse.getX() > 400 && Mouse.getX() <= 620){
					System.out.println("Options");
					MenuManager.addMenu(new OptionsMenu(this));
				}
			}
			if(Mouse.getY() > 250 && Mouse.getY() <= 300) {
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					System.out.println("Shop");
				}
			}
			if(Mouse.getY() > 350 && Mouse.getY() <= 400){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					MenuManager.addMenu(new MainMenu());
				}
			}
			
		}
	}

	public void render(Screen screen, Graphics g) {
		
		g.setFont(f);
		
		g.setColor(Color.BLACK);
		
		g.drawString("Resume", 370 + SHADOW_OFFSET, 100 + SHADOW_OFFSET);
		g.drawString("Options", 350 + SHADOW_OFFSET, 200 + SHADOW_OFFSET);
		g.drawString("Shop", 400 + SHADOW_OFFSET, 300 + SHADOW_OFFSET);
		g.drawString("Quit To Main Menu", 200 + SHADOW_OFFSET, 400 + SHADOW_OFFSET);
		
		g.setColor(Color.WHITE);
		g.drawString("Resume", 370, 100);
		g.drawString("Options", 350, 200);
		g.drawString("Shop", 400, 300);
		g.drawString("Quit To Main Menu", 200, 400);
		
		
	}

}
