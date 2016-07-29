package com.burksnet.code.games.rain.input;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseMotionListener, MouseListener{

	static int x;

	static int y;
	
	boolean[] keys = new boolean[MouseInfo.getNumberOfButtons()];
	
	public static boolean left;

	public static boolean middle;

	public static boolean right;
	
	public void update(){
		left = keys[1];
		middle = keys[2];
		right = keys[3];
	}
	
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		
	}

	public void releaseAll(){
		for(int key = 0; key < keys.length; key++){
			keys[key] = false;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		keys[arg0.getButton()] = true;
		System.out.println(x + ", " + y);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
