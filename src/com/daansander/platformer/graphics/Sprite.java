package com.daansander.platformer.graphics;

public class Sprite {

	public final int SIZE;
	public int[] pixels;
	private SpriteSheet sheet;
	private int xTile, yTile;

	public static Sprite blank = new Sprite(0, 0, 8, SpriteSheet.sprites);

	public Sprite(int xTile, int yTile, int size, SpriteSheet sheet) {
		this.SIZE = size;
		this.xTile = xTile * SIZE;
		this.yTile = yTile * SIZE;
		this.sheet = sheet;

		pixels = new int[SIZE * SIZE];

		load();
	}

	public void load() {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(xTile + x) + (yTile + y) * sheet.SIZE];
			}
		}
	}
}