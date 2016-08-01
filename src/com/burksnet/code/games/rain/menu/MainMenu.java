package com.burksnet.code.games.rain.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.input.Mouse;

public class MainMenu extends Menu{

	Random random = new Random();
	
	private int color;
	
	public MainMenu(){
		color = random.nextInt(0xdfdfdf);
		
		Game.game.regenGen();
		
	}
	
	public void render(Screen screen, Graphics g){
		screen.renderSprite(0, 0, new Sprite(Game.width, Game.height, color), false);
		
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString("Play.", 400 + 3, 200 + 3);
		g.drawString("Options", 400 + 3, 300 + 3);
		g.drawString("Quit", 400 + 3, 400 + 3);
		
		g.setColor(Color.WHITE);
		g.drawString("Play.", 400, 200);
		g.drawString("Options", 400, 300);
		g.drawString("Quit", 400, 400);
		
	}
	/*This works! (it took me 30 minutes of aggravation to realize I was
	running from the wrong project so none of my changes took effect XD)*/
	public void update(){
		if(Mouse.left){
			if(Mouse.getY() > 140 && Mouse.getY() <= 200){
				if(Mouse.getX() > 400 && Mouse.getX() <= 520){
					System.out.println("Play");
					MenuManager.removeMenu();
					Game.game.startGame();
					Game.game.getMouse().releaseAll();
				}
			}
			if(Mouse.getY() > 250 && Mouse.getY() <= 300){
				if(Mouse.getX() > 400 && Mouse.getX() <= 620){
					System.out.println("Options");
					MenuManager.addMenu(new OptionsMenu(this));
				}
			}
			if(Mouse.getY() > 340 && Mouse.getY() <= 400){
				if(Mouse.getX() > 400 && Mouse.getX() <= 520){
					System.out.println("Bye Bye");
					Game.game.stop();
				}
			}
			
		}
	}
	
}
