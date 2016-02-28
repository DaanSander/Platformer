package com.daansander.platformer.level;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.daansander.platformer.Game;
import com.daansander.platformer.entity.Entity;
import com.daansander.platformer.graphics.Screen;
import com.daansander.platformer.tile.Tile;

public class Level {

	private Game game;
	private int xScroll = 0;
	private Random random = new Random();
	private List<Entity> entities = new ArrayList<>();
	private List<Tile> tiles = new ArrayList<>();

	public Level(Game game) {
		this.game = game;
		tiles.add(new Tile(0 + 10, Game.HEIGHT - 10, 70, 80, 0xffffff));
		generate(0xff);
	}

	public void generate(int maxTiles) {
		int gap = 120;
		int current = 0;
		int next = 0;
		Color color = null;
		String hexString = "";
		for (int i = 0; i < maxTiles; i++) {
			next = random.nextInt(gap) + gap / 2;
			current += next;
			color = new Color(random.nextInt(155) + 100, random.nextInt(155) + 100,random.nextInt(155) + 100);
			hexString = Integer.toHexString(color.getRGB() & 0xffffff);
			tiles.add(new Tile(current, Game.HEIGHT - 60, random.nextInt(50) + 20, random.nextInt(50) + 20, (int)Long.parseLong(hexString, 16)));
		}
	}

	public void tick() {
		tickEntities();
	}

	public void tickEntities() {
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
	}
	
	public void move(int scroll) {
		if ((scroll < 0 && (getTile(game.player.x + scroll, game.player.y) == null)
				|| (scroll > 0 && (getTile(game.player.x + game.player.size + scroll, game.player.y) == null))))
			xScroll += scroll;

	}

	public void render(Screen screen) {
		renderTiles(screen);
		renderEntities(screen);
	}

	public void renderEntities(Screen screen) {
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).render(screen);
	}
	
	public void renderTiles(Screen screen) {
		for (int i = 0; i < tiles.size(); i++)
			tiles.get(i).render(screen, xScroll, 0);
	}

	public Tile getTile(int x, int y) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			int xMin = tile.x - xScroll;
			int yMin = tile.y;

			int xMax = tile.x + tile.xSize - xScroll;
			int yMax = tile.y + tile.ySize;
			
			if (x >= xMin && y >= yMin && x <= xMax && y <= yMax) return tile;
		}
		return null;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	//TODO new level?
	public void reset() {
		xScroll = 0;
	}
}