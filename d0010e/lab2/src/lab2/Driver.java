package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;
import java.util.ArrayList;

public class Driver {
	public void run() {
		levelSelection(1);
	}

	private static void levelSelection(int val) {
		switch (val) {
		case 1:
			Level level1 = new Level();
			ArrayList<Room> rooms = new ArrayList<Room>();
			rooms.add(new Room(100, 100, 0, 0, Color.blue));
			rooms.add(new Room(100, 200, 0, 100, Color.red));
			rooms.add(new Room(100, 300, 0, 300, Color.green));
			rooms.add(new Room(100, 100, 0, 600, Color.yellow));
			rooms.add(new Room(400, 100, 100, 600, Color.gray));
			rooms.add(new Room(100, 100, 500, 600, Color.pink));
			rooms.add(new Room(100, 200, 500, 700, Color.orange));
			rooms.add(new Room(100, 100, 500, 900, Color.cyan));
			rooms.add(new Room(200, 100, 600, 900, Color.white));
			rooms.add(new Room(100, 100, 800, 900, Color.darkGray));
			rooms.add(new Room(100, 500, 800, 400, Color.LIGHT_GRAY));
			rooms.add(new Room(100, 100, 800, 300, Color.BLUE));
			rooms.add(new Room(300, 100, 500, 300, Color.CYAN));
			rooms.add(new Room(100, 100, 400, 300, Color.YELLOW));
			rooms.add(new Room(100, 200, 400, 100, Color.ORANGE));
			rooms.add(new Room(100, 100, 400, 0, Color.WHITE));
			rooms.add(new Room(300, 100, 100, 0, Color.RED));

			if (!level1.place(rooms)) {
				System.out.println("Rooms are overlapping!");
			} else {
				rooms.get(0).connectSouthTo(rooms.get(1));
				rooms.get(1).connectNorthTo(rooms.get(0));
				rooms.get(1).connectSouthTo(rooms.get(2));
				rooms.get(2).connectNorthTo(rooms.get(1));
				rooms.get(2).connectSouthTo(rooms.get(3));
				rooms.get(3).connectNorthTo(rooms.get(2));
				rooms.get(3).connectEastTo(rooms.get(4));
				rooms.get(4).connectWestTo(rooms.get(3));
				rooms.get(4).connectEastTo(rooms.get(5));
				rooms.get(5).connectWestTo(rooms.get(4));
				rooms.get(5).connectSouthTo(rooms.get(6));
				rooms.get(6).connectSouthTo(rooms.get(7));
				rooms.get(6).connectNorthTo(rooms.get(5));
				rooms.get(7).connectNorthTo(rooms.get(6));
				rooms.get(7).connectEastTo(rooms.get(8));
				rooms.get(8).connectWestTo(rooms.get(7));
				rooms.get(8).connectEastTo(rooms.get(9));
				rooms.get(9).connectWestTo(rooms.get(8));
				rooms.get(9).connectNorthTo(rooms.get(10));
				rooms.get(10).connectSouthTo(rooms.get(9));
				rooms.get(10).connectNorthTo(rooms.get(11));
				rooms.get(11).connectSouthTo(rooms.get(10));
				rooms.get(11).connectWestTo(rooms.get(12));
				rooms.get(12).connectEastTo(rooms.get(11));
				rooms.get(12).connectWestTo(rooms.get(13));
				rooms.get(13).connectEastTo(rooms.get(12));
				rooms.get(13).connectNorthTo(rooms.get(14));
				rooms.get(14).connectSouthTo(rooms.get(13));
				rooms.get(14).connectNorthTo(rooms.get(15));
				rooms.get(15).connectSouthTo(rooms.get(14));
				rooms.get(15).connectWestTo(rooms.get(16));
				rooms.get(16).connectEastTo(rooms.get(15));
				rooms.get(16).connectWestTo(rooms.get(0));
				rooms.get(0).connectEastTo(rooms.get(16));
				
				level1.firstLocation(rooms.get(0));

				LevelGUI lvlGUI = new LevelGUI(level1, "Labb");

				level1.addObserver(lvlGUI);
			}

			break;
		case 2:
			Level level2 = new Level();
			ArrayList<Room> roomsTwo = new ArrayList<Room>();
			roomsTwo.add(new Room(100, 100, 0, 0, Color.blue));
			roomsTwo.add(new Room(200, 100, 100, 0, Color.red));
			roomsTwo.add(new Room(200, 100, 300, 0, Color.green));
			roomsTwo.add(new Room(200, 100, 500, 0, Color.yellow));

			level2.firstLocation(roomsTwo.get(0));

			if (!level2.place(roomsTwo)) {
				System.out.println("Rooms are overlapping!");
			} else {
				roomsTwo.get(0).connectEastTo(roomsTwo.get(1));
				roomsTwo.get(1).connectWestTo(roomsTwo.get(0));
				roomsTwo.get(1).connectEastTo(roomsTwo.get(2));
				roomsTwo.get(2).connectEastTo(roomsTwo.get(3));
				roomsTwo.get(3).connectWestTo(roomsTwo.get(2));
				
				level2.firstLocation(roomsTwo.get(0));

				LevelGUI lvlGUI = new LevelGUI(level2, "Labb");

				level2.addObserver(lvlGUI);
			}

			break;
		default:
			break;
		}
	}
}