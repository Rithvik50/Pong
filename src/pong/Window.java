package pong;

/**
 * Author: Rithvik, Eyal, and Liad
 * Date: 5/27/19
 * Version 2.0
 * Version Notes:
 * v.2.0: Added this cool header here
 */

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	private static final long serialVersionUID = -4810618286807932601L;

//	Constructor
	public Window(int w, int h, String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);

		game.start();
	}

}