package lab4.data;

import java.util.Observable;

public class GameGrid extends Observable {
    private int EMPTY = 0;
    public int ME = 1;
    public int OTHER = 2;
    private static final int INROW = 5;
    private int[][] twoDim;
	
	public GameGrid(int size) {
		twoDim = new int[size][size];
	}

	public int getLocation(int x, int y) {
		return twoDim[y][x];
	}

	public int getSize() {
		return twoDim.length;
	}

	public boolean move(int x, int y, int player) {
        if (twoDim[y][x] == EMPTY) {
            twoDim[y][x] = player;
            change();
            return true;
        }
        return false;
	}

	public void clearGrid() {
        for (int i = 0; i < twoDim.length; i++) {
            for (int j = 0; j < twoDim.length; j++) {
            	twoDim[i][j] = EMPTY;
            }
        }
        change();
	}

    public boolean isWinner(int player) {
        for (int i = 0; i < twoDim.length; i++) {
            for (int j = 0; j < twoDim.length; j++) {
                if (twoDim[i][j] == player) {
                    boolean columsCheck = j + INROW - 1 < twoDim.length;
                    boolean rowCheck = i + INROW - 1 < twoDim.length;
                    boolean negativeColum = j - (INROW - 1) >= 0;

                    if (columsCheck) {
                        if (winnerColum(i,j,player)){
                            return true;
                        }
                    }

                    if (rowCheck) {
                        if(winnerRow(i,j,player)){
                            return true;
                        }

                    }

                    if (rowCheck && columsCheck) {
                        if(winnerDiag(i,j,player)){
                            return true;
                        }
                    }

                    if (negativeColum && rowCheck) {
                        if(winnerNegDiag(i,j,player)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean winnerRow(int i, int j, int player) {
        int winner = 0;
        
        for (int r = i; r < (i + INROW); r++) {
            if (twoDim[r][j] == player) {
                winner++;
            }
        }
        
        if (winner == INROW) {
            return true;
        }
        return false;
    }

    public boolean winnerColum(int i, int j, int player) {
        int winner = 0;
        
        for (int c = j; c < (j + INROW); c++) {
            if (twoDim[i][c] == player) {
                winner++;
            }
        }
        
        if (winner == INROW) {
            return true;
        }
        return false;
    }

    private boolean winnerDiag(int i, int j, int player) {
        int winner = 0;

        for (int r = i; r < (i + INROW); r++) {
            if (twoDim[r][j] == player) {
                winner++;
            }
            j++;
        }

        if (winner == INROW) {
            return true;
        }
        return false;
    }

    private boolean winnerNegDiag(int i, int j, int player) {
        int winner = 0;

        for (int r = i; r < (i + INROW); r++) {
            if (twoDim[r][j] == player) {
                winner++;
            }
            j--;
        }
        
        if (winner == INROW) {
            return true;
        }
        return false;
    }
    
	public void change() {
		setChanged();
		notifyObservers();
	}
}