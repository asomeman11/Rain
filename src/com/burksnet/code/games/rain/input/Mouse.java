package com.burksnet.code.games.rain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener{

	int x, y;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		
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
		System.out.println("TRUE");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("FALSE");
	}

}
