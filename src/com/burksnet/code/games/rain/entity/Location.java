package com.burksnet.code.games.rain.entity;

public class Location {

	private int x, y;
	
	public Location(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int[] getXY(){
		return new int[]{
			x,y
		};
	}
	 
}
