package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Color;
//import javax.sound.sampled.*;
//import java.io.*;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;

//	Constructor
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		x += velX;
		y += velY;

		y = Game.clamp(y, 2, Game.HEIGHT - 103);

		collision();
	}

	// method for when boundaries intersect
	private void collision() {
		
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, 22, 72);
	}

//	Sets boundaries
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 22, 72);
	}
	
}