package com.daansander.platformer.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public final int SIZE;
	public final String PATH;
	public int[] pixels;

	public static SpriteSheet sprites = new SpriteSheet("/Spritesheet.png", 256);
	
	public SpriteSheet(String path, int size) {
		this.PATH = path;
		this.SIZE = size;
		
		pixels = new int[SIZE * SIZE];
		
		load();
	}
	
	public void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(PATH));
			
			int width = image.getWidth();
			int height = image.getHeight();
			
			image.getRGB(0, 0, width, height, pixels, 0, SIZE);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}