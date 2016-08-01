package com.burksnet.code.games.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.burksnet.code.games.rain.menu.MenuManager;
import com.burksnet.code.games.rain.menu.PauseMenu;

public class Keyboard implements KeyListener {

	private Mouse mouse;

	public Keyboard(Mouse m){
		mouse = m;
	}

	public Mouse getMouse() {
		return mouse;
	}

	private boolean[] keys = new boolean[512];
	public boolean up, down, left, right, paused;

	public void update() {

		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(!MenuManager.isActive()){
				MenuManager.addMenu(new PauseMenu());

			}
			else if(MenuManager.isActive()){
				MenuManager.removeMenu();
			}

		}
		try{
			keys[e.getKeyCode()] = true;
		}catch(ArrayIndexOutOfBoundsException e1){
			System.err.println("Keyboard code " + e.getKeyCode() + " could not be logged.");
		}
		e.consume();
	}

	public void keyReleased(KeyEvent e) {
		try{
			keys[e.getKeyCode()] = false;
		}catch(ArrayIndexOutOfBoundsException e1){
			System.err.println("Keyboard code " + e.getKeyCode() + " could not be logged.");
		}
		
	}

	public void releaseAll() {
		for (int i = 0; i < keys.length; i++)
			keys[i] = false;
	}

}
