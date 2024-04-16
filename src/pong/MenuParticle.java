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
import java.util.Random;

public class MenuParticle extends GameObject {
	private Handler handler;
	Random r = new Random();

	private Color col;
	
//	Constructor
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = (r.nextInt(7 - -7) + -7);
		velY = (r.nextInt(7 - -7) + -7);
		if (velX == 0)
			velX = 1;
		if (velY == 0)
			velY = 1;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, handler));
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x, (int) y, 16, 16);
	}

//	Sets the boundaries
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}