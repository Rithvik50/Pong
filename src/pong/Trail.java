package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	private float alpha = 1, life;
	private Handler handler;
	private Color color;
	private int w, h;

//	Constructor
	public Trail(float x, float y, ID id, Color color, int w, int h, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.w = w;
		this.h = h;
		this.life = life; // 0.001 - 0.1
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.0001f);
		} else
			handler.removeObject(this);
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(color);
		g.fillRect((int) x, (int) y, w, h);

		g2d.setComposite(makeTransparent(1));
	}

	// makes rectangles gradually transparent, creating trail effect
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	// has no boundaries, so player can't interact
	public Rectangle getBounds() {
		return null;
	}

}