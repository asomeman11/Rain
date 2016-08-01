package com.burksnet.code.games.rain.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.graphics.Screen;
import com.burksnet.code.games.rain.graphics.Sprite;
import com.burksnet.code.games.rain.input.Mouse;
import com.burksnet.code.games.rain.sound.Sound;

public class OptionsMenu extends SubMenu {
	
	private float soundBarPercent = (Sound.getVolume() + 35) * 2;
	
	public OptionsMenu(Menu creator){
		super(creator);
	}

	public void update(){
		if(Mouse.left){
			
			if(Mouse.getY() > 160 && Mouse.getY() <= 210){
				if(Mouse.getX() > 400 && Mouse.getX() <= 700){
					MenuManager.removeMenu();
				}
			}
			
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
			if(Mouse.getY() > 400 && Mouse.getY() <= 420){
				if(Mouse.getX() > 400 && Mouse.getX() <= 720){
					int t = Mouse.getX() - 400;
					soundBarPercent = t / 4;
					Sound.changeVolume((soundBarPercent / 2 - 35), MyProperties.music);
				}
			}
			
		}
	}

	public void render(Screen screen, Graphics g){
		g.setFont(f);
		g.setColor(Color.BLACK);
		
		g.drawString("Back", 400 + SHADOW_OFFSET, 210 + SHADOW_OFFSET);
		if(MyProperties.music)
			g.drawString("Music On", 400 + SHADOW_OFFSET, 280 + SHADOW_OFFSET);
		else g.drawString("Music Off", 400 + SHADOW_OFFSET, 280 + SHADOW_OFFSET);
		if(MyProperties.sound)
			g.drawString("Sound On", 400 + SHADOW_OFFSET, 360 + SHADOW_OFFSET);
		else g.drawString("Sound Off", 400 + SHADOW_OFFSET, 360 + SHADOW_OFFSET);
		
		g.setColor(Color.WHITE);
		
		g.drawString("Back", 400, 210);
		if(MyProperties.music)
			g.drawString("Music On", 400, 280);
		else g.drawString("Music Off", 400, 280);
		if(MyProperties.sound)
			g.drawString("Sound On", 400, 360);
		else g.drawString("Sound Off", 400, 360);
		
		screen.renderSprite(400/2, 400/2, new Sprite(160, 10, Color.WHITE.getRGB()), false);
		screen.renderSprite(400/2, 400/2, new Sprite((int)soundBarPercent * 2, 10, Color.BLACK.getRGB()), false);
	}

}
