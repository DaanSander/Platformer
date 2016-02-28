package com.daansander.platformer.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.daansander.platformer.Game;

public class Background {

	public final String PATH;
	private int width, height;
	private int[] pixels;

	public static Background sunny = new Background("/Sunny BackGround.png");
	public static Background mountain = new Background("/Mountain Background.png");
	
	public Background(String path) {
		this.PATH = path;
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(Background.class.getResource(PATH));

			width = image.getWidth();
			height = image.getHeight();

			if (width != Game.WIDTH || height != Game.HEIGHT)
				System.err.println("Background " + PATH + " has not the correct size!");

			pixels = new int[width * height];

			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void render(Screen screen) {
		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= screen.HEIGHT) continue;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x >= screen.WIDTH) continue;
				screen.pixels[x + y * screen.WIDTH] = pixels[x + y * width];
			}
		}
	}
}