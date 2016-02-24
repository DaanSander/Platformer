package com.daansander.platformer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.daansander.platformer.entity.Player;
import com.daansander.platformer.graphics.Screen;
import com.daansander.platformer.level.Level;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int SCALE = 1;
	public static final String NAME = "Platformer";

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public InputHandler input;
	public Player player;
	private JFrame frame;
	private Thread thread;
	private Screen screen;
	private Level level;
	
	private boolean running = false;
	
	public Game() {
		Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		
		frame = new JFrame(NAME);
		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
		level = new Level(this);
		player = new Player(20, 20, level, input);
		
		frame.setResizable(false);
		frame.add(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}

	public synchronized void start() {
		if(!running) {
			running = true;
			thread = new Thread(this, "Game");
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if(running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 30.0;

		double delta = 0;

		int frames = 0;
		int ticks = 0;
		while (running) {
			long currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;

			if (delta > 1) {
				ticks++;
				delta--;

				tick();
			}

			frames++;
			render();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				frame.setTitle(NAME + " fps " + frames + ", tps " + ticks);

				ticks = 0;
				frames = 0;
			}
		}
	}
	
	public void tick() {
		player.tick();
		level.tick();
		input.tick();
		
		if(input.r.down) {
			player.x = 20;
			player.y = 20;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();

		level.render(screen);
		screen.render();
		player.render(screen);
		
		
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];
		
		Graphics graphics = bs.getDrawGraphics();
		
		graphics.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		graphics.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
}