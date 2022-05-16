package lab2.level;

import java.awt.Color;
import java.util.ArrayList;

import java.util.Observable;

public class Level extends Observable {
	Room playerLoc = null;
	ArrayList<Room> rooms;
	boolean placeCheck = false;

	private static boolean overlap(Room newRoom, Room oldRoom) {
		if ((newRoom.xPos + newRoom.width > oldRoom.xPos && oldRoom.xPos > newRoom.xPos)
				|| (oldRoom.xPos + oldRoom.width > newRoom.xPos && newRoom.xPos > oldRoom.xPos)
				|| (newRoom.xPos == oldRoom.xPos)) {
			if ((newRoom.yPos + newRoom.height > oldRoom.yPos && oldRoom.yPos > newRoom.yPos)
					|| (oldRoom.yPos + oldRoom.height > newRoom.yPos && newRoom.yPos > oldRoom.yPos)
					|| (newRoom.yPos == oldRoom.yPos)) {
				return true;
			}
		}
		return false;
	}

	public boolean place(ArrayList<Room> r) {
		if (placeCheck) {
			return false;
		}

		for (int i = 0; i < r.size(); i++) {
			for (int j = i; j < r.size(); j++) {
				if (overlap(r.get(i), r.get(j)) && i != j) {
					return false;
				}
			}
		}
		rooms = r;
		return true;
	}

	public void firstLocation(Room r) {
		playerLoc = r;
		placeCheck = true;
	}

	protected void playerMove(int move) {
		if (playerLoc.doorWay[move] != null) {
			playerLoc = playerLoc.doorWay[move];
			setChanged();
			notifyObservers();
		}

	}
}