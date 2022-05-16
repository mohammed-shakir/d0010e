package lab4;

import lab4.client.*;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

public class GomokuMain {
	public static void main(String[] args) {
		GomokuClient client;
        GomokuGameState StateOfGame;
        GomokuGUI GUI;
        
        if (args.length > 0) {
        	client = new GomokuClient(Integer.parseInt(args[0]));
        }
        
        else {
        	client = new GomokuClient(4001);
        }
        
        StateOfGame = new GomokuGameState(client);
        GUI = new GomokuGUI(StateOfGame, client);
        
        GomokuClient client2 = new GomokuClient(4003);
        StateOfGame = new GomokuGameState(client2);
        GUI = new GomokuGUI(StateOfGame, client2);
	}
}