package lab4.data;

import java.util.Observable;

import java.util.Observer;
import lab4.client.GomokuClient;

public class GomokuGameState extends Observable implements Observer {
	private GameGrid gameGrid;
	public int DEFAULT_SIZE = 15;
	private int NOT_STARTED = 0;
    private int MY_TURN = 1;
    private int OTHER_TURN = 2;
    private int FINISHED = 3;
	private int currentState;
	private String message;
    private GomokuClient client;
    
	public GomokuGameState(GomokuClient gc) {
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
		message = "Gomoku";
	}
	
	public String getMessageString() {
		return message;
	}

	public GameGrid getGameGrid() {
		return gameGrid;
	}

	public void move(int x, int y) {
        if (currentState == MY_TURN) {
            if (gameGrid.move(x,y,MY_TURN)) {
                client.sendMoveMessage(x,y);
                message = "It is the other player's turn.";

                if (gameGrid.isWinner(MY_TURN)) {
                    currentState = FINISHED;
                    message = "You won!";
                    change();
                    return;
                }
                currentState = OTHER_TURN;
                change();
            }
            
            else {
                message = "Square is already taken.";
            }
        }
        
        else if (currentState == NOT_STARTED) {
            message = "The game has not started yet.";
            change();
            return;
        }
        
        else if (currentState == FINISHED) {
            message = "The game is over.";
            change();
            return;
        }
        
        else {
            message = "It is the other player's turn.";
            change();
            return;
        }
	}

	public void newGame() {
        gameGrid.clearGrid();
        currentState = MY_TURN;
        message = "Game started! It is your turn!";
        client.sendNewGameMessage();
        change();
	}

	public void receivedNewGame() {
        gameGrid.clearGrid();
        message = "Game started! It is the other player's turn.";
        currentState = OTHER_TURN;
        change();
	}

	public void otherGuyLeft() {
        gameGrid.clearGrid();
        message = "The other player disconnected";
        change();
	}

	public void disconnect() {
        gameGrid.clearGrid();
        message = "Disconnected";
        change();
        client.disconnect();
	}

	public void receivedMove(int x, int y) {
        gameGrid.move(x,y,OTHER_TURN);
        message = "It is your turn.";

        if (gameGrid.isWinner(OTHER_TURN)) {
            message = "The other player won.";
            currentState = FINISHED;
            change();
            return;
        }
        currentState = MY_TURN;
        change();
	}
	
	public void update(Observable o, Object arg) {
		if (client.getConnectionStatus() == 1) {
			currentState = MY_TURN;
			change();
		}
		
		else {
			currentState = OTHER_TURN;
			change();
		}
	}
	
	public void change() {
		setChanged();
		notifyObservers();
	}
}