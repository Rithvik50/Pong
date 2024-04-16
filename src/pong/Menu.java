package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pong.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	static Clip clip;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		playSound();
	}

	// mouse clicked actions
	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();

		// for menu screen
		if (Game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mX, mY, 220, 128, 200, 64)) {
				Game.gameState = STATE.Select;
				return;
			}

			// help button
			if (mouseOver(mX, mY, 220, 224, 200, 64)) {
				Game.gameState = STATE.Help;
			}

			// quit button
			if (mouseOver(mX, mY, 220, 320, 200, 64)) {
				System.exit(1);
			}
		}

		// for help screen
		if (Game.gameState == STATE.Help) {
			handler.clearObjects();
			// back button
			if (mouseOver(mX, mY, 220, 320, 200, 64)) {
				Game.gameState = STATE.Menu;
				for (int i = 0; i < 10; i++) {
					handler.addObject(
							new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
				}
				return;
			}
		}

		// for end screen
		if (Game.gameState == STATE.End) {
			// restart button
			if (mouseOver(mX, mY, 220, 320, 200, 64)) {
				handler.clearObjects();
				Game.gameState = STATE.Menu;
				for (int i = 0; i < 10; i++) {
					handler.addObject(
							new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
				}
				hud.setLevel(1);
				hud.setScore(0);
//				TODO fix bug where health bar doesn't reset size
			}
		}

		// for select screen
		if (Game.gameState == STATE.Select) {
			// normal button
			if (mouseOver(mX, mY, 220, 128, 200, 64)) {
				handler.clearObjects();
				Game.gameState = STATE.Game;
				clip.stop();
				handler.addObject(new Ball(40, 40, ID.Ball, handler));
				handler.addObject(new Player(Game.WIDTH - 12, Game.HEIGHT / 2 - 36, ID.Player, handler));
				
				game.diff = 0;
			}

			// hard button
			if (mouseOver(mX, mY, 220, 224, 200, 64)) {
				handler.clearObjects();
				Game.gameState = STATE.Game;
				clip.stop();
				handler.addObject(new Ball(40, 40, ID.Ball, handler));
				handler.addObject(new Player(Game.WIDTH - 12, Game.HEIGHT / 2 - 36, ID.Player, handler));
				handler.addObject(
						new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));

				game.diff = 1;
			}

			// back button
			if (mouseOver(mX, mY, 220, 320, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
	}

	// mouse released actions
	public void mouseReleased(MouseEvent e) {

	}

	// is mouse over box / icon
	private boolean mouseOver(int mX, int mY, int x, int y, int w, int h) {
		if (mX > x && mX < x + w) {
			if (mY > y && mY < y + h)
				return true;
			else
				return false;
		} else
			return false;
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being
	 * called once. Method for actions of this class in-game.
	 */
	public void tick() {

	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being
	 * called once. Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		if (Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 64);
			Font fnt2 = new Font("arial", 1, 32);

			g.setColor(Color.WHITE);

			g.setFont(fnt);
			g.drawString("PONG 2.0", 170, 80);

			g.setFont(fnt2);
			g.drawRect(220, 128, 200, 64);
			g.drawString("PLAY", 270, 175);

			g.drawRect(220, 224, 200, 64);
			g.drawString("HELP", 270, 271);

			g.drawRect(220, 320, 200, 64);
			g.drawString("QUIT", 270, 367);
		} else if (Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 64);
			Font fnt2 = new Font("arial", 1, 32);
			Font fnt3 = new Font("arial", 1, 20);

			g.setColor(Color.WHITE);

			g.setFont(fnt);
			g.drawString("HELP", 225, 80);

			g.setFont(fnt3);
			g.drawString("Press W to move up, and S to move down.", 20, 144);
			g.drawString("Press P to pause and spacebar to enter the shop", 20, 164);
			g.drawString("Don't let the ball touch your wall!", 20, 184);
			g.setColor(Color.CYAN);
			g.drawString("Only for hard mode!!!", 20, 204);
			g.setColor(Color.RED);
			g.drawString("The red squares kill you when you touch them.", 20, 224);
			g.fillRect(500, 214, 10, 10);
			g.setColor(Color.WHITE);

			g.setFont(fnt2);
			g.drawRect(220, 320, 200, 64);
			g.drawString("BACK", 270, 367);
		} else if (Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 64);
			Font fnt2 = new Font("arial", 1, 32);
			Font fnt3 = new Font("arial", 1, 20);

			g.setColor(Color.WHITE);

			g.setFont(fnt);
			g.drawString("GAME OVER!", 87, 80);

			g.setFont(fnt3);
			g.drawString("You lost with a level of " + hud.getLevel() + "!", 130, 204);

			g.setFont(fnt2);
			g.drawRect(220, 320, 200, 64);
			g.drawString("RESTART", 238, 367);
		} else if (Game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 48);
			Font fnt2 = new Font("arial", 1, 32);

			g.setColor(Color.WHITE);

			g.setFont(fnt);
			g.drawString("SELECT DIFFICULTY", 75, 80);

			g.setFont(fnt2);
			g.drawRect(220, 128, 200, 64);
			g.drawString("NORMAL", 243, 175);

			g.drawRect(220, 224, 200, 64);
			g.drawString("HARD", 270, 271);

			g.drawRect(220, 320, 200, 64);
			g.drawString("BACK", 270, 367);
		}

	}

//	Background music for the menu
	public static void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("avengers_background.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}