package com.burksnet.code.games.rain;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.burksnet.code.games.rain.menu.MenuManager;
import com.burksnet.code.games.rain.menu.PauseMenu;

public class BurkFocusListener implements FocusListener {

	private Game game;

	public BurkFocusListener(Game game) {
		this.game = game;
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		game.getKeyboard().releaseAll();
		game.getMouse().releaseAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		if(!MenuManager.isActive()){
			MenuManager.addMenu(new PauseMenu());
		}
		
		game.getKeyboard().releaseAll();
		game.getKeyboard().paused = true;
		game.pause(true);
	}

}
