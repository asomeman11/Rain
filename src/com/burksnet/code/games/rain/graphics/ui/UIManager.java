package com.burksnet.code.games.rain.graphics.ui;

import java.util.ArrayList;
import java.util.List;

import com.burksnet.code.games.rain.graphics.Screen;

public class UIManager {

	private List<UserInterface> ui = new ArrayList<UserInterface>();
	private List<Boolean> active = new ArrayList<Boolean>();
	
	Boolean b = new Boolean(true);
	
	public void update(){
		for(int i = 0; i < ui.size(); i++){
			if(active.get(i).booleanValue()){
				ui.get(i).update();
			}
		}
	}
	
	public void render(Screen screen){
		for(int i = 0; i < ui.size(); i++){
			if(active.get(i).booleanValue()){
				ui.get(i).render(screen);
			}
		}
	}
	
	
}
