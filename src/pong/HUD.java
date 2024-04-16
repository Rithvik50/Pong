package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	public int bounds = 0;
	public static float HEALTH = 100;
	private float greenValue = 255;

	private int score = 0, level = 1;

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100 + bounds / 2);
		
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255);

		score++;
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		if (level == 1) {
			bounds = 0;
		}
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200 + bounds, 32);

		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("P to Pause", 15, 96);
		g.drawString("Space for Shop", 15, 112);
	}

//	Updates the score
	public void setScore(int score) {
		this.score = score;
	}

//	Returns the score
	public int getScore() {
		return score;
	}

//	Updates the level
	public void setLevel(int level) {
		this.level = level;
	}

//	Returns the level
	public int getLevel() {
		return level;
	}
	
// Sets bounds
	public void setBounds() {
		this.bounds = 0;
	}

}