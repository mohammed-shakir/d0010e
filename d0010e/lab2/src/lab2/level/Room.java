package lab2.level;

import java.awt.Color;

public class Room {
	protected Room[] doorWay = { null, null, null, null };
	protected int xPos, yPos, width, height;
	protected Color _color;

	public Room(int dx, int dy, int x, int y, Color color) {
		System.out.printf("dx: %d, dy: %d, color: %s, \n", dx, dy, color);

		xPos = x;
		yPos = y;
		width = dx;
		height = dy;
		_color = color;
	}

	public void connectNorthTo(Room r) {
		doorWay[0] = r;
	}

	public void connectEastTo(Room r) {
		doorWay[1] = r;
	}

	public void connectSouthTo(Room r) {
		doorWay[2] = r;
	}

	public void connectWestTo(Room r) {
		doorWay[3] = r;
	}
}