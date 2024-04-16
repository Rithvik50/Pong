package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y;
	protected ID id;
	protected float velX, velY;

	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	// tick method for all objects
	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public abstract void tick();

	// render method for all objects
	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public abstract void render(Graphics g);

	// set and get bounds for all objects
	public abstract Rectangle getBounds();

	// getters and setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

}