package com.daansander.platformer.graphics;

public class Screen {

	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		
		pixels = new int[WIDTH * HEIGHT];
	}

	public void clear() {
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void fillRectangle(int x, int y, int xSize, int ySize, int color) {
		for(int yp = y; yp < (y + ySize); yp++) {
			if(yp < 0 || yp >= HEIGHT) continue;
			for(int xp = x; xp < (x + xSize); xp++) {
				if(xp < 0 || xp >= WIDTH) continue;
				pixels[xp + yp * WIDTH] = color;
			}
		}
	}
	
	public void render() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
//				pixels[x + y * WIDTH] = 0xff00ff;
			}
		}
	}
	
}