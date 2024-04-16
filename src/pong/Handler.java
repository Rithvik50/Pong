package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	public int speed = 5;
	public ArrayList<GameObject> object = new ArrayList<GameObject>();

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for actions of this class in-game.
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

	/*
	 * Called 60 times every second. Each frame corresponds with this method being called once.
	 * Method for drawing this class onto the screen.
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

//	Gets rid of all objects (enemies, healers, player, ball)
	public void clearObjects() {
		for (int i = object.size() - 1; i >= 0; i--) {
				object.remove(i);
		}

	}

//	Adds objects
	public void addObject(GameObject object) {
		this.object.add(object);
	}

//	Removes specific objects
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}