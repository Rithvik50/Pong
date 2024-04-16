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

public class Enemy extends GameObject {
	Handler handler;

//	Constructor
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 6.0f;
		velY = 4.0f;
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

		collision();
	}

	// method for when boundaries intersect
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code
					velX = velX * -1;
					HUD.HEALTH -= HUD.HEALTH;
				}
			}
		}
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, 10, 10);
	}

//	Creates the boundaries for this to interact with other objects
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 10, 10);
	}

}