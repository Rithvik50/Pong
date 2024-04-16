package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 6691247796639148462L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;

	// 0 = normal, 1 = hard
	public int diff = 0;

	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	public enum STATE {
		Menu, Select, Help, Game, Shop, End
	}

	public static STATE gameState = STATE.Menu;

//	Implements all the game components and creates the game and the window
	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);

		new Window(WIDTH, HEIGHT, "Pong (Better!)", this);

		r = new Random();
		spawner = new Spawn(handler, hud, this);

		if (gameState == STATE.Menu) {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	}

//	Starts the game
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

//	Stops the game
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The run method / game loop. This method uses ticks and nanoseconds, with an
	 * option to print out FPS.
	 */
	@Override
	public void run() {
		this.requestFocus();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps, delta = 0;
		long now, lastTime = System.nanoTime();
		// long timer = 0;
		// int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			// timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				//ticks++;
				delta--;
			}

			/* if (timer >= 1000000000) {
				System.out.println("Ticks/Frames: " + ticks);
				ticks = 0;
				timer = 0;
			} */
		}

		stop();
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	private void tick() {
		if (gameState == STATE.Game) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();

				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearObjects();
					// Upon losing the game, the health and speed costs are reset to 1000
					BufferStrategy bs = this.getBufferStrategy();
					if (bs == null) {
						this.createBufferStrategy(3);
						return;
					}
					Graphics g = bs.getDrawGraphics();
					shop.render(g, true);
					for (int i = 0; i < 10; i++) {
						handler.addObject(
								new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
			menu.tick();
			handler.tick();
		}
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (paused) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 100, 96);
		}

		if (gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		} else if (gameState == STATE.Shop) {
			shop.render(g, false);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select) {
			menu.render(g);
			handler.render(g);
		}

		g.dispose();
		bs.show();
	}

	// sets maximum and minimum to selected variable
	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	// main method, creates new game
	public static void main(String[] args) {
		new Game();
	}

}