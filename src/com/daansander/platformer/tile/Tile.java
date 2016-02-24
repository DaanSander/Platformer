package com.daansander.platformer.tile;

import com.daansander.platformer.graphics.Screen;

public class Tile {

	public int x, y;
	public int size;
	
	public Tile(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void render(Screen screen, int xScroll, int yScroll) {
		screen.fillRectangle(x - xScroll, y - yScroll, size, size, 0xffffff);
	}
}