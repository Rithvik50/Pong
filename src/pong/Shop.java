package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import pong.Game.STATE;

public class Shop extends MouseAdapter {
	Handler handler;
	HUD hud;
	public int healthCost = 1000;
	public int speedCost = 1000;
	public int refillCost = 1000;
	private Image backdrop;

//	Constructor
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		backdrop = (new ImageIcon("wood.jpg")).getImage();
	}
	
//	Draws the shop
	public void render(Graphics g, boolean redrawWithInitialScore) {
		if (redrawWithInitialScore) {
			healthCost = 1000;
			speedCost = 1000;
		}
		Font fnt = new Font("arial", 1, 64);
		Font fnt3 = new Font("arial", 1, 12);
		
		g.drawImage(backdrop, 0, 0, null);

		g.setColor(Color.WHITE);

		g.setFont(fnt);
		g.drawString("SHOP", 225, 80);

		// health upgrade
		g.setFont(fnt3);
		g.drawRect(100, 100, 100, 80);
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost :" + healthCost, 110, 140);

		g.drawRect(250, 100, 100, 80);
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost: " + speedCost, 260, 140);

		g.drawRect(400, 100, 100, 80);
		g.drawString("Refill Health", 410, 120);
		g.drawString("Cost: " + refillCost, 410, 140);

		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH / 2 - 50, 300);
		g.drawString("Space to Return", Game.WIDTH / 2 - 50, 332);
	}

//	Sets the events that occur when a mouse click happens
	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();

		// box 1
		if (Game.gameState == STATE.Shop) {
			if (mX >= 100 && mX <= 200) {
				if (mY >= 100 && mY <= 180) {
					if (hud.getScore() >= healthCost) {
						hud.setScore(hud.getScore() - healthCost);
						healthCost += 1000;
						hud.bounds += 20;
						HUD.HEALTH = (100 + hud.bounds / 2);
					}
				}
			}

			// box 2
			if (mX >= 250 && mX <= 350) {
				if (mY >= 100 && mY <= 180) {
					if (hud.getScore() >= speedCost) {
						hud.setScore(hud.getScore() - speedCost);
						speedCost += 1000;
						handler.speed++;
					}	
				}
			}

			// box 3
			if (mX >= 400 && mX <= 500) {
				if (mY >= 100 && mY <= 180) {
					if (hud.getScore() >= refillCost) {
						hud.setScore(hud.getScore() - refillCost);
						HUD.HEALTH = (100 + hud.bounds / 2);
					}
				}
			}
		}

	}
}