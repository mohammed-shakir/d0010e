package lab4.gui;

import java.awt.*;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import lab4.data.GameGrid;

public class GamePanel extends JPanel implements Observer {	
	private int UNIT_SIZE = 20;
	private GameGrid grid;
    Dimension d;

	public GamePanel (GameGrid grid) {
		this.grid = grid;
		grid.addObserver(this);
		d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setPreferredSize(d);
	}

	public int[] getGridPosition(int x, int y){
        int colum = x / UNIT_SIZE;
        int row = y / UNIT_SIZE;
        int[] returnArray = {row,colum};
        return returnArray;
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        int row;
        int colum;
        Graphics2D g2 = (Graphics2D) g;
        Stroke standardStroke = g2.getStroke();

        for (int i = 0; i < grid.getSize(); i++){
            for (int j = 0; j < grid.getSize(); j++){
                row = i * UNIT_SIZE;
                colum = j * UNIT_SIZE;
                g.setColor(Color.BLACK);
                g.drawRect(row,colum,UNIT_SIZE,UNIT_SIZE);
                
                if (grid.getLocation(j,i) == grid.ME){
                	g.setColor(Color.BLUE);
                    g.fillOval(colum + 1,row + 1,UNIT_SIZE - 2,UNIT_SIZE - 2);
                    
                }
                
                if (grid.getLocation(j,i) == grid.OTHER){
                	g.setColor(Color.RED);
                    g.fillOval(colum + 1,row + 1,UNIT_SIZE - 2,UNIT_SIZE - 2);
                }
            }
        }
	}
}