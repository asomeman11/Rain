package com.burksnet.code.games.rain;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.burksnet.code.games.rain.input.Keyboard;

public class BurkFocusListener implements FocusListener {

	private Game game;

	public BurkFocusListener(Game game) {
		this.game = game;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO game.pause();
		game.getKeyboard().releaseAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		game.getKeyboard().releaseAll();
		game.getKeyboard().paused = true;
		game.pause(true);
	}

}
