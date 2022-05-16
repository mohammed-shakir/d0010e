package lab4.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import lab4.client.*;
import lab4.data.*;
import javax.swing.*;

public class GomokuGUI implements Observer{
	private GomokuClient client;
	private GomokuGameState gamestate;
    private GamePanel gameGridPanel;
    private JButton connectButton;
    private JButton newGameButton;
    private JButton disconnectButton;
    private JLabel messageLabel;

	public GomokuGUI(GomokuGameState ggs, GomokuClient gc) {
		this.client = gc;
		this.gamestate = ggs;
		client.addObserver(this);
		gamestate.addObserver(this);
        SpringLayout layout = new SpringLayout();

        JFrame frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameGridPanel = new GamePanel(gamestate.getGameGrid());
        Container container = frame.getContentPane();
        container.setLayout(layout);
        Dimension d = new Dimension(26*gamestate.DEFAULT_SIZE, 26*gamestate.DEFAULT_SIZE);
        container.setPreferredSize(d);

        connectButton = new JButton("Connect");
        newGameButton = new JButton("Start");
        disconnectButton = new JButton("Disconnect");
        messageLabel = new JLabel(gamestate.getMessageString());

        container.add(gameGridPanel);
        container.add(connectButton);
        container.add(newGameButton);
        container.add(disconnectButton);
        container.add(messageLabel);

        layout.putConstraint(SpringLayout.NORTH, gameGridPanel, 5, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameGridPanel, 0, SpringLayout.HORIZONTAL_CENTER, container);

        layout.putConstraint(SpringLayout.NORTH, connectButton, 20, SpringLayout.SOUTH, gameGridPanel);
        layout.putConstraint(SpringLayout.EAST, connectButton, -10, SpringLayout.WEST, newGameButton);

        layout.putConstraint(SpringLayout.NORTH, disconnectButton, 20, SpringLayout.SOUTH, gameGridPanel);
        layout.putConstraint(SpringLayout.WEST, disconnectButton, 10, SpringLayout.EAST, newGameButton);

        layout.putConstraint(SpringLayout.NORTH, newGameButton, 20, SpringLayout.SOUTH, gameGridPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER, gameGridPanel);

        layout.putConstraint(SpringLayout.NORTH, messageLabel, 10, SpringLayout.SOUTH, newGameButton);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageLabel, 0,SpringLayout.HORIZONTAL_CENTER,gameGridPanel);

        frame.setContentPane(container);
        frame.pack();
        frame.setVisible(true);
                
        connectButton.setEnabled(true);
        newGameButton.setEnabled(false);
        disconnectButton.setEnabled(false);
        
        gameGridPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int[] square = gameGridPanel.getGridPosition(e.getX(),e.getY());
                gamestate.move(square[1],square[0]);
            }
        });

        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectionWindow connectionWindow = new ConnectionWindow(client);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamestate.newGame();
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamestate.disconnect();
            }
        });
	}
	
	public void update(Observable arg0, Object arg1) {
        if (arg0 == client) {
            if (client.getConnectionStatus() == GomokuClient.UNCONNECTED) {
                connectButton.setEnabled(true);
                newGameButton.setEnabled(false);
                disconnectButton.setEnabled(false);
            }
            
            else {
                connectButton.setEnabled(false);
                newGameButton.setEnabled(true);
                disconnectButton.setEnabled(true);
            }
        }

        if (arg0 == gamestate) {
            messageLabel.setText(gamestate.getMessageString());
        }
	}
}