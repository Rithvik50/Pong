package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import pong.Game.STATE;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		for (int i = 0; i < keyDown.length; i++) {
			keyDown[i] = false;
		}
	}

	// called when key is pressed, moves player
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-handler.speed);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(handler.speed);
					keyDown[2] = true;
				}
			}
		}
		
		// pause on p key
		if (key == KeyEvent.VK_P) {
			if (Game.gameState == STATE.Game) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}
		}

		// exit on esc key
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		// shop on space key
		if (key == KeyEvent.VK_SPACE) {
			if (Game.gameState == STATE.Game)
				Game.gameState = STATE.Shop;
			else if (Game.gameState == STATE.Shop)
				Game.gameState = STATE.Game;
		}
	}

	// called when key is released, stops player
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if (key == KeyEvent.VK_A) {
					keyDown[1] = false;
				}
				if (key == KeyEvent.VK_S) {
					keyDown[2] = false;
				}
				if (key == KeyEvent.VK_D) {
					keyDown[3] = false;
				}
				// helps the character smoothly
				if (!keyDown[0] && !keyDown[2])
					tempObject.setVelY(0);
				if (!keyDown[1] && !keyDown[3])
					tempObject.setVelX(0);
			}

		}
	}

}