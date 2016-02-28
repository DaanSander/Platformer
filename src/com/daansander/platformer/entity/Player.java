package com.daansander.platformer.entity;

import java.util.ArrayList;
import java.util.List;

import com.daansander.platformer.Game;
import com.daansander.platformer.InputHandler;
import com.daansander.platformer.graphics.Font;
import com.daansander.platformer.graphics.Screen;
import com.daansander.platformer.graphics.SpriteSheet;
import com.daansander.platformer.level.Level;
import com.daansander.platformer.sound.Sound;
import com.daansander.platformer.tile.Tile;

public class Player extends Entity {

	// TODO: Make size final
	public boolean grounded = false;
	public int size = 16;
	private Level level;
	private InputHandler input;
	private int score = 0;
	private int dir = 0;
	private int numSteps = 0;
	private int yVelocity = 0;
	private List<Tile> tiles = new ArrayList<>();

	public Player(int x, int y, Level level, InputHandler input) {
		super(x, y);
		this.level = level;
		this.input = input;
	}

	@Override
	public void tick() {
		if (y > 720) {
			Sound.deathSound.play();
			x = 20;
			y = 20;
			level.reset();
			score = 0;
			tiles.clear();
		}

		grounded = hasCollided(x, y + size + yVelocity) || hasCollided(x + size, y + size + yVelocity);

		// if (hasCollided(x, y) || hasCollided(x + size, y)) {
		// yVelocity = -1;
		// return;
		// }

		if (grounded) {
			Tile tile = level.getTile(x, y + size + yVelocity);
			if (tile == null) tile = level.getTile(x + size, y + size + yVelocity);

			if (!tiles.contains(level.getTile(x, y + size + yVelocity))) {
				tiles.add(level.getTile(x, y + size + yVelocity));
				score++;
				System.out.println(score);
			}

			yVelocity = 0;
		} else
			yVelocity += 1;

		if (input.up.down && grounded)
			yVelocity -= 10;

		if (yVelocity > 10) yVelocity = 10;

		y += yVelocity;

		if (input.left.down) {
			dir = 0;
			numSteps++;
			level.move(-5);
		} else if (input.right.down) {
			dir = 1;
			numSteps++;
			level.move(5 * ((input.shift.down) ? 2 : 1));
		}
	}

	public boolean hasCollided(int x, int y) {
		return level.getTile(x, y) != null;
	}

	@Override
	public void render(Screen screen) {
		screen.render(x, y, 160 + ((numSteps >> 4) & 1), 16, (dir == 0), false, SpriteSheet.sprites);

		Font.display(Game.WIDTH / 2, 20, "Score " + score, 0xffffff, screen);
	}

}
