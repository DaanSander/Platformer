package com.daansander.platformer.entity;

import com.daansander.platformer.graphics.Screen;

public abstract class Entity {

	public int x, y;
	
	protected Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen);
}