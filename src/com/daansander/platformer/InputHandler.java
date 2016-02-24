package com.daansander.platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	
	public class Key {
		public boolean pressed = false, down = false;
		private int polled = 0;
		
		public Key() {
			keys.add(this);
		}
		
		public void tick() {
			if(polled >= 1 && pressed)
				pressed = false;
			
			if(down)
				polled++;
			else
				polled = 0;
		}
		
		public void toggle(boolean pressed) {
			down = pressed;
			this.pressed = pressed;
		}
	}
	
	private List<Key> keys = new ArrayList<>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key r = new Key();
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e.getKeyCode(), false);
	}
	
	public void toggle(int key, boolean pressed) {
		if(key == KeyEvent.VK_UP) up.toggle(pressed);
		if(key == KeyEvent.VK_DOWN) down.toggle(pressed);
		if(key == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if(key == KeyEvent.VK_LEFT) left.toggle(pressed);
		if(key == KeyEvent.VK_R) r.toggle(pressed);
		
	}
	
	public void tick() {
		for(int i = 0; i < keys.size(); i++)
			keys.get(i).tick();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}