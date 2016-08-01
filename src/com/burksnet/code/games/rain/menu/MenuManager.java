package com.burksnet.code.games.rain.menu;

import java.awt.Graphics;

import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.graphics.Screen;

public class MenuManager {

	private static Menu menu;

	private static boolean active;

	private MenuManager() {
	}

	public static boolean isActive() {
		return active;
	}

	public static void render(Screen screen, Graphics g) {
		if (active) {
			menu.render(screen, g);
		}
	}

	public static void update() {
		if (active) {
			menu.update();
		}
	}

	public static void removeMenu() {

		if (menu instanceof SubMenu) {
			menu = ((SubMenu) menu).getCreator();
		} else {
			menu = null;
			active = false;
		}
		Game.game.getKeyboard().releaseAll();
		Game.game.getMouse().releaseAll();
	}

	public static void addMenu(Menu menu) {
		MenuManager.menu = menu;
		active = true;
	}

	public static void addMenu(SubMenu menu) {
		MenuManager.menu = menu;
		active = true;
	}

}
