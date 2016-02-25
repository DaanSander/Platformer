package com.daansander.platformer.entity;

import com.daansander.platformer.InputHandler;
import com.daansander.platformer.graphics.Screen;
import com.daansander.platformer.level.Level;
import com.daansander.platformer.tile.Tile;

public class Player extends Entity {

	//TODO: Make size final
	public int size = 10;
	private Level level;
	private int yVelocity = 0;
	private boolean grounded = false;
	private InputHandler input;

	public Player(int x, int y, Level level, InputHandler input) {
		super(x, y);
		this.level = level;
		this.input = input;
	}

	@Override
	public void tick() {
		grounded = hasCollided(x, y + size + yVelocity) || hasCollided(x + size, y + size + yVelocity);

		if(hasCollided(x, y) || hasCollided (x + size, y)) {
			yVelocity = 0;
			return;
		}
		
		if (grounded)
			yVelocity = 0;
		else
			yVelocity += 1;

		if (input.up.down && grounded) {
			yVelocity -= 10;
		}

		if (yVelocity > 10) yVelocity = 10;

		y += yVelocity;

	}

	public boolean hasCollided(int x, int y) {
		return level.getTile(x, y) != null;
	}

	@Override
	public void render(Screen screen) {
		screen.fillRectangle(x, y, size, size, 0xff000f);
		screen.fillRectangle(x, y, 1, 1, 0xffffff);

	}

}
