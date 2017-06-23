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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class GUICore {
	
	JFrame frame;
	ImageToPrint excommunication1;
	ImageToPrint excommunication2;
	ImageToPrint excommunication3;
	
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
		PrintTower board = new PrintTower();
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
		ImageToPrint tile = new ImageToPrint("bonustile.png");
		//tile.setPreferredSize(new Dimension(50, 0));
		
		//2.personal board
		ImageToPrint personal = new ImageToPrint("personalboard.jpg");
		
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
		
		/*JTextField servants = new JTextField();
		servants.setText("Insert number of servants you want to use..");
		buttonsPanel.add(servants);*/
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		JSpinner spinner = new JSpinner(model);
		buttonsPanel.add(spinner);
		
		JButton confirm = new JButton("Do Action!");
		buttonsPanel.add(confirm);
		
		panel.add(pointsPanel);
		panel.add(familyPanel);
		panel.add(buttonsPanel);
		
	}
	
	//panel for preview, leader, excommunications and console
	public void createSouthPanel (JPanel panel) {
		panel.setLayout(new GridLayout(1, 6));
		
		//1.preview
		ImageToPrint card = new ImageToPrint("devcards_f_en_c_1.png");
		panel.add(card);

		//2.leader button
		ImageToPrint leader = new ImageToPrint("leader.jpg");
		panel.add(leader);
		
		//3.excommunication cards
		excommunication1 = new ImageToPrint("excomm_back_1.png");
		panel.add(excommunication1);
		
		excommunication2 = new ImageToPrint("excomm_back_2.png");
		panel.add(excommunication2);
		
		excommunication3 = new ImageToPrint("excomm_back_3.png");
		panel.add(excommunication3);
		
		//4.console
		JTextArea console = new JTextArea();
		console.setText("console..");
		console.setEditable(false);
		panel.add(console);
		
	}
	
	/*public void setCards (JFrame frame) {
		ImageToStamp cartaProva = new ImageToStamp("devcards_f_en_c_1.png");
		cartaProva.setSize(10, 10);
		System.out.println(cartaProva.getWidth());
		frame.add(cartaProva);
	}*/

	
	public static void main(String[] args) {
		new GUICore ();
	}

}
