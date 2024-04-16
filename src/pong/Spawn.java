package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;

	private int scoreKeep = 0;

//	Constructor
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	// spawns enemies (hard mode)
	/*
	 * Called 60 times every second. Each frame corresponds with this method being
	 * called once. Method for actions of this class in-game.
	 */
	public void tick() {
		scoreKeep++;

//Spawns entities based off of game level
		if (scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			if (game.diff == 1) {
				if (hud.getLevel() % 5 == 0) {
					handler.addObject(
							new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				}
			} 
		}
	}
}