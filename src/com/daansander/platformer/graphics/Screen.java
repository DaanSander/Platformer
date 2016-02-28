package com.daansander.platformer.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Screen {

	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public Screen(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;

		pixels = new int[WIDTH * HEIGHT];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void fillRectangle(int x, int y, int xSize, int ySize, int color) {
		for (int yp = y; yp < (y + ySize); yp++) {
			if (yp < 0 || yp >= HEIGHT) continue;
			for (int xp = x; xp < (x + xSize); xp++) {
				if (xp < 0 || xp >= WIDTH) continue;
				pixels[xp + yp * WIDTH] = color;
			}
		}
	}

	public void renderSprite(int x, int y, boolean flipX, boolean flipY, Sprite sprite) {
		for (int yp = 0; yp < sprite.SIZE; yp++) {
			for (int xp = 0; xp < sprite.SIZE; xp++) {
				if (sprite.pixels[((flipX) ? (sprite.SIZE - 1) - xp : xp)
						+ ((flipY) ? (sprite.SIZE - 1) - yp : yp) * sprite.SIZE] != 0xffff00ff)
					pixels[(x + xp) + (y + yp) * WIDTH] = sprite.pixels[((flipX) ? (sprite.SIZE - 1) - xp : xp)
							+ ((flipY) ? (sprite.SIZE - 1) - yp : yp) * sprite.SIZE];
			}
		}
	}

	public void render(int x, int y, int tile, int size, boolean flipX, boolean flipY, SpriteSheet sheet) {

		int tileX = (tile % 32) * size;
		int tileY = (tile / 32) * size;

		for (int yp = 0; yp < size; yp++) {
			int yc = yp + y;
			if(yc < 0 || yc >= HEIGHT) continue;
			for (int xp = 0; xp < size; xp++) {
				int xc = xp + x;
				if(xc < 0 || xc >= WIDTH) return;
				if(sheet.pixels[(tileX + ((flipX) ? size - xp : xp))
						+ (tileY + ((flipY) ? size - yp : yp)) * sheet.SIZE] != 0xffff00ff)
				pixels[xc + yc * WIDTH] = sheet.pixels[(tileX + ((flipX) ? size - xp : xp))
						+ (tileY + ((flipY) ? size - yp : yp)) * sheet.SIZE];
			}
		}
	}

	public void render() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				// pixels[x + y * WIDTH] = 0xff00ff;
			}
		}
	}

}