package lab2.level;

import java.awt.BasicStroke;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {
	private Level lv;
	private Display d;

	public LevelGUI(Level level, String name) {
		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: You should change 200 to a value
		// depending on the size of the level
		// change window size depending on box size
		d = new Display(lv, 1000, 1000);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {
		public Display(Level fp, int x, int y) {
			addKeyListener(new Listener());
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(x + 20, y + 20));
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			for (int i = 0; i < lv.rooms.size(); i++) {
				Room room = lv.rooms.get(i);

				g2.setColor(room._color);
				g2.fillRect(room.xPos, room.yPos, room.width, room.height);

				if (lv.playerLoc == room) {
					BasicStroke bs = new BasicStroke(5);
					g2.setColor(Color.magenta);
					g2.setStroke(bs);
					g2.drawRect(room.xPos, room.yPos, room.width, room.height);
				}

				if (room.doorWay[0] != null) {
					g.setColor(room.doorWay[0]._color);
					g.fillRect((room.xPos + (room.width / 2) - (15 / 2)), room.yPos, 15, 15);
				}

				if (room.doorWay[1] != null) {
					g.setColor(room.doorWay[1]._color);
					g.fillRect(room.xPos + room.width - 15, room.yPos + (room.height / 2) - (15 / 2), 15, 15);
				}

				if (room.doorWay[2] != null) {
					g.setColor(room.doorWay[2]._color);
					g.fillRect(room.xPos + (room.width / 2) - (15 / 2), room.yPos + room.height - 15, 15, 15);
				}

				if (room.doorWay[3] != null) {
					g.setColor(room.doorWay[3]._color);
					g.fillRect(room.xPos, room.yPos + (room.height / 2) - (15 / 2), 15, 15);
				}

			}
		}

		private class Listener implements KeyListener {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 87) {
					// W
					lv.playerMove(0);
				}

				else if (arg0.getKeyCode() == 68) {
					// D
					lv.playerMove(1);
				}

				else if (arg0.getKeyCode() == 83) {
					// S
					lv.playerMove(2);
				}

				else if (arg0.getKeyCode() == 65) {
					// A
					lv.playerMove(3);
				}
			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyTyped(KeyEvent event) {

			}
		}
	}
}