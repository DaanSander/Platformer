package com.daansander.platformer.graphics;

public class Font {

	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
	
	public static void display(int x, int y, String message, int color, Screen screen) {
		message = message.toUpperCase();
		for(int i = 0; i < message.length(); i++) {
			int c = chars.indexOf(message.charAt(i));
			
			screen.render(x, y, (26 * 32) + c, 8, false, false, SpriteSheet.sprites);
			x+=8;
		}
	}
	
}