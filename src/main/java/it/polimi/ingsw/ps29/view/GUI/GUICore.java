package it.polimi.ingsw.ps29.view.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUICore {
	
	private JFrame frame;
	
	public GUICore () {
		frame = new JFrame ();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		setFrame (frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		
	}
	
	//function where i create GUI
	void setFrame (JFrame frame) {
		frame.setLayout(new BorderLayout());
		
		//1.status bar
		JTextArea statusBar = new JTextArea();
		statusBar.setText("ID PARTITA - GIOCATORE nome");
		statusBar.setEditable(false);
		//statusBar.setPreferredSize(new Dimension (0, 24));
		
		//2.game board
		ImageToStamp board = new ImageToStamp("gameboard.png");
		board.setPreferredSize(new Dimension(460, 0));
		
		//3.panel of the game
		JPanel gamePanel = new JPanel ();
		setGamePanel (gamePanel);
		
		frame.add(statusBar, BorderLayout.PAGE_START);
		frame.add(board,  BorderLayout.LINE_START);
		frame.add(gamePanel, BorderLayout.CENTER);
	}
	
	//function where i create center-right side of the GUI
	public void setGamePanel (JPanel panel) {
		panel.setLayout(new GridLayout(3, 1));
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		createNorthPanel(p1);
		createCenterPanel(p2);
		createSouthPanel(p3);
		
		panel.add(p1, BorderLayout.PAGE_START);
		panel.add(p2, BorderLayout.CENTER);
		panel.add(p3, BorderLayout.PAGE_END);
	}
	
	//panel for tile, board and buttons...
	public void createNorthPanel (JPanel panel) {
		panel.setLayout(new BorderLayout());
		
		//1.bonus tile
		ImageToStamp tile = new ImageToStamp("bonustile.png");
		//tile.setPreferredSize(new Dimension(50, 0));
		
		//2.personal board
		ImageToStamp personal = new ImageToStamp("personalboard.jpg");
		
		//3.buttons
		JPanel buttonsPanel = new JPanel ();
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
			
			//venture button
			JButton showVentures = new JButton("Show Venture Cards");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			buttonsPanel.add(showVentures, c);
			
			//character button
			JButton showCharacters = new JButton("Show Character Cards");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			buttonsPanel.add(showCharacters, c);
			
			//prev button
			JButton prevBoard = new JButton("Previous Personal Board");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			buttonsPanel.add(prevBoard, c);
			
			//next button
			JButton nextBoard = new JButton("Next Personal Board");
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			buttonsPanel.add(nextBoard, c);
			
		panel.add(tile, BorderLayout.LINE_START);
		panel.add(personal, BorderLayout.CENTER);
		panel.add(buttonsPanel, BorderLayout.LINE_END);
		
	}
	
	//panel for points, privilege, family, servants and perform action
	public void createCenterPanel (JPanel panel) {
		panel.setLayout(new GridLayout(1, 3));
		
		//1.panel for points and privileges
		JPanel pointsPanel = new JPanel ();
		pointsPanel.setLayout(new GridLayout(2, 2));
		
		JTextArea militar = new JTextArea ("Militar Points: ");
		militar.setEditable(false);
		pointsPanel.add(militar);
		
		JTextArea faith = new JTextArea ("Faith Points: ");
		faith.setEditable(false);
		pointsPanel.add(faith);
		
		JTextArea victory = new JTextArea ("Victory Points: ");
		victory.setEditable(false);
		pointsPanel.add(victory);
		
		JTextArea privilege = new JTextArea ("Privileges: ");
		privilege.setEditable(false);
		pointsPanel.add(privilege);
		
		//2.panel for family
		JPanel familyPanel = new JPanel ();
		familyPanel.setLayout(new GridLayout(3, 2));
		
		JLabel familyLabel = new JLabel("Family");
		familyPanel.add(familyLabel);
		familyPanel.add(new JLabel(""));
		
		JRadioButton black = new JRadioButton("Black");
		JRadioButton white = new JRadioButton("White");
		JRadioButton orange = new JRadioButton("Orange");
		JRadioButton neutral = new JRadioButton("Neutral");
		
		ButtonGroup familiar = new ButtonGroup();
		familiar.add(black);
		familiar.add(white);
		familiar.add(orange);
		familiar.add(neutral);
		
		familyPanel.add(black);
		familyPanel.add(white);
		familyPanel.add(orange);
		familyPanel.add(neutral);
		
		//3.servants and confirm
		JPanel buttonsPanel = new JPanel ();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		
		JTextField servants = new JTextField();
		servants.setText("Insert number of servants you want to use..");
		buttonsPanel.add(servants);
		
		JButton confirm = new JButton("Do Action!");
		buttonsPanel.add(confirm);
		
		panel.add(pointsPanel);
		panel.add(familyPanel);
		panel.add(buttonsPanel);
		
	}
	
	//panel for leader, excommunications and console
	public void createSouthPanel (JPanel panel) {
		panel.setLayout(new GridLayout(1, 5));
		
		//1.leader button
		ImageToStamp leader = new ImageToStamp("leader.jpg");
		panel.add(leader);
		
		//2.excommunication cards
		ImageToStamp excommunication1 = new ImageToStamp("excomm_back_1.png");
		panel.add(excommunication1);
		
		ImageToStamp excommunication2 = new ImageToStamp("excomm_back_2.png");
		panel.add(excommunication2);
		
		ImageToStamp excommunication3 = new ImageToStamp("excomm_back_3.png");
		panel.add(excommunication3);
		
		//3.console
		JTextArea console = new JTextArea();
		console.setText("console..");
		console.setEditable(false);
		panel.add(console);
		
	}

	
	public static void main(String[] args) {
		new GUICore ();
	}

}
