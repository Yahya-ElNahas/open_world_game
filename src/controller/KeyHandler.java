package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean UP, DOWN, RIGHT, LEFT;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: UP = true;break;
		case KeyEvent.VK_S: DOWN = true;break;
		case KeyEvent.VK_A: LEFT = true;break;
		case KeyEvent.VK_D: RIGHT = true;break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: UP = false;break;
		case KeyEvent.VK_S: DOWN = false;break;
		case KeyEvent.VK_A: LEFT = false;break;
		case KeyEvent.VK_D: RIGHT = false;break;
		}
	}

}
