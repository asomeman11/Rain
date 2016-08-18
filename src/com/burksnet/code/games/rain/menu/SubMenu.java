package com.burksnet.code.games.rain.menu;

public class SubMenu extends Menu {

	protected Menu creator;
	
	public SubMenu(Menu creator){
		this.creator = creator;
	}

	public Menu getCreator(){
		return creator;
	}

}
