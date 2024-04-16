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
import java.awt.Rectangle;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ball extends GameObject {
	Handler handler;

//	Constructor for the ball
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 5.0f;
		velY = 5.0f;
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0 || x >= Game.WIDTH - 20) {
			velX *= -1.005;
		}
		if (y <= 0 || y >= Game.HEIGHT - 40) {
			velY *= -1.005;
		}
		if (x >= Game.WIDTH - 20) {
			HUD.HEALTH -= 20;
		}

		collision();
	}

	// method for when boundaries intersect
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code
					playSound();
					velX = velX * -1;
				}
			}
		}
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval((int) x, (int) y, 20, 20);
	}

//	Sets the boundaries of the ball
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 20, 20);
	}
	
//	Plays sound every time the ball hits the player
	public static void playSound() {
		Clip clip;
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("pongblip.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}