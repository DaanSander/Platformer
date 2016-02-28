package com.daansander.platformer.tile;

import com.daansander.platformer.graphics.Screen;

public class Tile {

	public final int COLOR;
	public int x, y;
	public int xSize, ySize;
	
	public Tile(int x, int y, int xSize, int ySize, int color) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.COLOR = color;
	}
	
	public void render(Screen screen, int xScroll, int yScroll) {
		screen.fillRectangle(x - xScroll, y - yScroll, xSize, ySize, COLOR);
	}
}