package com.daansander.platformer.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.daansander.platformer.Game;
import com.daansander.platformer.graphics.Screen;
import com.daansander.platformer.tile.Tile;

public class Level {

	private Game game;
	private int xScroll = 0;
	private Random random = new Random();
	private List<Tile> tiles = new ArrayList<>();

	public Level(Game game) {
		this.game = game;
		tiles.add(new Tile(0 + 10, Game.HEIGHT - 10, 70));
		generate(20);
	}

	public void generate(int maxTiles) {
		int gap = 120;
		int current = 0;
		int next = 0;
		for(int i = 0; i < maxTiles; i++) {
			next = random.nextInt(gap) + gap / 2;
			current += next;
			tiles.add(new Tile(current, Game.HEIGHT - 60, random.nextInt(50) + 20));
		}
	}
	
	public void tick() {
		if (game.input.left.down) {
			if((getTile(game.player.x, game.player.y) == null))
				xScroll -= 5;
		} else if (game.input.right.down) {
			if((getTile(game.player.x + 5, game.player.y) == null))
				xScroll += 5;
			

		}
	}

	public void render(Screen screen) {
		renderTiles(screen);
	}

	public void renderTiles(Screen screen) {
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).render(screen, xScroll, 0);
		}
	}

	public Tile getTile(int x, int y) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			int xMin = tile.x - xScroll;
			int yMin = tile.y;

			int xMax = tile.x + tile.size - xScroll;
			int yMax = tile.y + tile.size;

			// System.out.println("x " + x + " , y " + y + " \n " + xMin + "," +
			// xMax + " -=- " + yMin + "," + yMax);

			if (x >= xMin && y >= yMin && x <= xMax && y <= yMax) return tile;
		}
		return null;
	}
}