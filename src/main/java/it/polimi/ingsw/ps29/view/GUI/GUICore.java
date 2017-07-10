package it.polimi.ingsw.ps29.view.GUI;

/**
 * Creates the GUI of the application
 * @author Pietro Melzi
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.GUI.coordinates.StartCoordinates;

public class GUICore extends Observable{
	String playerName;
	
	JFrame frame; 
	TowersDTO towers;
	ActionChoice message; //used for leaders
	
	PrintTower tower;
	ImageToPrint tile, preview;
	PrintPersonal personal;
	ButtonGroup family;
	JSpinner servants;
	JButton doAction, noAction, otherCards, leaderButton;
	JLabel ts1, ts2, ts3;
	JLabel coin, stone, wood, servant, military, faith, victory, privileges;
	ImageToPrint excomm1, excomm2, excomm3;
	JTextArea console;

	PlayerListener listener;
	
	public GUICore (String name) {
		playerName = name;
		listener = new PlayerListener(this);
		
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
	
				frame = new JFrame ();
				frame.setVisible(true);
				setFrame (frame);
				Toolkit tk = Toolkit.getDefaultToolkit();
				int xSize = ((int) tk.getScreenSize().getWidth());
				int ySize = ((int) tk.getScreenSize().getHeight())-38;
				frame.setSize(xSize,ySize);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	/**
	 * Splits the window in two parts: tower and utilities
	 * @param frame contains each element of the GUI
	 */
	
	public void setFrame (JFrame frame) {
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weighty = 1;
		c.weightx = 4;
		c.fill = GridBagConstraints.BOTH;
		StartCoordinates startCoord = new StartCoordinates(14,20,51,85,97,91,4,4);
		tower = new PrintTower("gameboard.png", this, startCoord, 413, 607);
		frame.add(tower, c);
		
		c.gridx = 1;
		c.weightx = 1;
		JPanel centralPanel = new JPanel();
		setCentralPanel(centralPanel);
		frame.add(centralPanel, c);
	}
	
	/**
	 * Creates threee panels in order to organize the content of the application
	 * @param centralPanel
	 */
	
	public void setCentralPanel (JPanel centralPanel) {
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		JPanel panelCenter = new JPanel ();
		panelCenter.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		JPanel panelSouth = new JPanel ();
		panelSouth.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		
		setPanelNorth(panelNorth);
		setPanelCenter(panelCenter);
		setPanelSouth(panelSouth);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 6;
		centralPanel.add(panelNorth, c);
		
		c.gridy = 1;
		c.weighty = 1;
		centralPanel.add(panelCenter, c);
		
		c.gridy = 2;
		c.weighty = 4;
		c.fill = GridBagConstraints.BOTH;
		centralPanel.add(panelSouth,  c);
				
	}
	
	/**
	 * North panel contains tile, personal board, leader cards and excommunications info
	 * @param panelNorth
	 */
	
	public void setPanelNorth (JPanel panelNorth) {
		panelNorth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tile = new ImageToPrint("bonus_tiles/personalbonustile_1.png");
		c.gridheight = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panelNorth.add(tile, c);
		
		StartCoordinates startCoord = new StartCoordinates(6, 9, 59, 92, 67, 115, 2, 6);
		personal = new PrintPersonal("personalboard.jpg", this, startCoord, 400, 285);
		
		c.gridx = 1;
		c.weightx = 6;
		c.weighty = 2;
		
		panelNorth.add(personal, c);
		
		JPanel leaderPanel = new JPanel ();
		leaderPanel.setLayout(new BorderLayout());
		
		ImageToPrint leader = new ImageToPrint("leader.jpg");
		leaderPanel.add(leader, BorderLayout.CENTER);
		
		leaderButton = new JButton ("LEADER");
		leaderButton.addMouseListener(listener);
		leaderPanel.add(leaderButton, BorderLayout.PAGE_END);
		
		c.gridx = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		panelNorth.add(leaderPanel, c);
		
		excomm1 = new ImageToPrint("excomm_back_1.png");
		c.gridx = 3;
		panelNorth.add(excomm1, c);
		
		excomm2 = new ImageToPrint("excomm_back_2.png");
		c.gridx = 2;
		c.gridy = 1;
		panelNorth.add(excomm2, c);
		
		excomm3 = new ImageToPrint("excomm_back_3.png");
		c.gridx = 3;
		panelNorth.add(excomm3, c);
	}
	
	/**
	 * Central panel contains info about points, cards that aren't shown on board and the buttons used to perform actions
	 * @param panelCenter
	 */
	public void setPanelCenter (JPanel panelCenter) {
		panelCenter.setLayout(new GridLayout(1, 4));
		
		JPanel buttonsPanel = new JPanel ();
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		JPanel familiarPanel = new JPanel();
		familiarPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		JPanel doActionPanel = new JPanel();
		doActionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		JPanel pointsPanel = new JPanel();
		pointsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		buttonsPanel.setLayout(new GridBagLayout());
		familiarPanel.setLayout(new GridLayout(2, 2));
		doActionPanel.setLayout(new GridLayout(2, 2));
		pointsPanel.setLayout(new GridLayout(2, 4));
		
		setButtonsPanel(buttonsPanel);
		setFamiliarPanel(familiarPanel);
		setDoActionPanel(doActionPanel);
		setPointsPanel(pointsPanel);
		
		panelCenter.add(buttonsPanel);
		panelCenter.add(familiarPanel);
		panelCenter.add(doActionPanel);
		panelCenter.add(pointsPanel);
	}
	
	/**
	 * South panel contains a zoom of the card selected, a console where info messages are shown and the situation of the resources
	 * @param panelSouth
	 */
	public void setPanelSouth (JPanel panelSouth) {
		panelSouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		preview = new ImageToPrint("devcards_f_en_c_1.png");
		c.weightx = 2;
		c.fill = GridBagConstraints.BOTH;
		panelSouth.add(preview, c);
		
		c.gridx = 1;
		c.weightx = 3;
		c.weighty = 1;
		
		console = new JTextArea("console...");
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		panelSouth.add(scrollPane, c);
		
		c.gridx = 2;
		c.weightx = 1;
		JPanel resourcesPanel = new JPanel();
		resourcesPanel.setLayout(new GridLayout(4, 2));
		
		ImageToPrint coinIMG = new ImageToPrint("resources/coin.png");
		resourcesPanel.add(coinIMG);
		coin = new JLabel("0");
		coin.setHorizontalAlignment(JLabel.CENTER);
		resourcesPanel.add(coin);
		
		ImageToPrint stoneIMG = new ImageToPrint("resources/stone.png");
		resourcesPanel.add(stoneIMG);
		stone = new JLabel("0");
		stone.setHorizontalAlignment(JLabel.CENTER);
		resourcesPanel.add(stone);
		
		ImageToPrint woodIMG = new ImageToPrint("resources/wood.png");
		resourcesPanel.add(woodIMG);
		wood = new JLabel("0");
		wood.setHorizontalAlignment(JLabel.CENTER);
		resourcesPanel.add(wood);
		
		ImageToPrint servantIMG = new ImageToPrint("resources/servant.png");
		resourcesPanel.add(servantIMG);
		servant = new JLabel("0");
		servant.setHorizontalAlignment(JLabel.CENTER);
		resourcesPanel.add(servant);
		
		panelSouth.add(resourcesPanel, c);
		
	}
	
	public void setButtonsPanel (JPanel buttonsPanel) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 3;
		otherCards = new JButton("OTHER CARDS");
		otherCards.addMouseListener(listener);
		buttonsPanel.add(otherCards, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		buttonsPanel.add(new JLabel("THRESHOLDS"), c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.gridwidth = 1;
		ts1 = new JLabel ("I");
		buttonsPanel.add(ts1, c);
		
		c.gridx = 1;
		ts2 = new JLabel ("II");
		buttonsPanel.add(ts2, c);
		
		c.gridx = 2;
		ts3 = new JLabel ("III");
		buttonsPanel.add(ts3, c);
	}
	
	public void setFamiliarPanel (JPanel familiarPanel) {
		
		JRadioButton black = new JRadioButton("BLACK");
		black.setActionCommand("Black");
		black.setSelected(true);
		black.setIcon(new ColorIcon(16, Color.BLACK));
		black.setSelectedIcon(new ColorIconSelected(16, Color.BLACK));
		familiarPanel.add(black);
		
		JRadioButton white = new JRadioButton("WHITE");
		white.setActionCommand("White");
		white.setIcon(new ColorIcon(16, Color.WHITE));
		white.setSelectedIcon(new ColorIconSelected(16, Color.WHITE));
		familiarPanel.add(white);
		
		JRadioButton orange = new JRadioButton("ORANGE");
		orange.setActionCommand("Orange");
		orange.setIcon(new ColorIcon(16, Color.ORANGE));
		orange.setSelectedIcon(new ColorIconSelected(16, Color.ORANGE));
		familiarPanel.add(orange);
		
		JRadioButton neutral = new JRadioButton("NEUTRAL");
		neutral.setActionCommand("Neutral");
		neutral.setIcon(new ColorIcon(16, Color.LIGHT_GRAY));
		neutral.setSelectedIcon(new ColorIconSelected(16, Color.LIGHT_GRAY));
		familiarPanel.add(neutral);
		
		family = new ButtonGroup();
		family.add(black);
		family.add(white);
		family.add(orange);
		family.add(neutral);
	}
	
	public void setDoActionPanel (JPanel doActionPanel) {
		JLabel labelServants = new JLabel("# of servants: ");
		doActionPanel.add(labelServants);
		
		doAction = new JButton("Do Action!");
		doAction.setEnabled(false);
		doAction.addMouseListener(listener);
		doActionPanel.add(doAction);
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		servants = new JSpinner(model);
		doActionPanel.add(servants);
		
		noAction = new JButton("NO Action");
		noAction.setEnabled(false);
		noAction.addMouseListener(listener);
		doActionPanel.add(noAction);
	}
	
	public void setPointsPanel (JPanel pointsPanel) {
		
		ImageToPrint militaryIMG = new ImageToPrint("resources/military.png");
		pointsPanel.add(militaryIMG);
		military = new JLabel("0");
		military.setHorizontalAlignment(JLabel.CENTER);
		pointsPanel.add(military);
		
		ImageToPrint faithIMG = new ImageToPrint("resources/faith.png");
		pointsPanel.add(faithIMG);
		faith = new JLabel("0");
		faith.setHorizontalAlignment(JLabel.CENTER);
		pointsPanel.add(faith);
		
		ImageToPrint victoryIMG = new ImageToPrint("resources/victory.png");
		pointsPanel.add(victoryIMG);
		victory = new JLabel("0");
		victory.setHorizontalAlignment(JLabel.CENTER);
		pointsPanel.add(victory);
		
		ImageToPrint privilegeIMG = new ImageToPrint("resources/privilege.png");
		pointsPanel.add(privilegeIMG);
		privileges = new JLabel("0");
		privileges.setHorizontalAlignment(JLabel.CENTER);
		pointsPanel.add(privileges);
	}	
	
	public TowersDTO getTowers () {
		return towers;
	}
	
	public void setTowers (TowersDTO towers) {
		this.towers = towers;
	}
	
	public void zoomImage (int idCard) {
		preview.setImage("cards/devcards_f_en_c_"+idCard+".png");
	}
	
	/*public static void main(String[] args) {
		new GUICore ("test");
	}*/
	
	public void notifyInput (Object msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	public void setMessage (ActionChoice msg) {
		message = msg;
	}
	
	public ActionChoice getMessage () {
		return message;
	}
	
	public JLabel getCoin() {
		return coin;
	}

	public JLabel getStone() {
		return stone;
	}

	public JLabel getWood() {
		return wood;
	}

	public JLabel getServant() {
		return servant;
	}

	public JLabel getMilitary() {
		return military;
	}

	public JLabel getFaith() {
		return faith;
	}

	public JLabel getVictory() {
		return victory;
	}

	public JLabel getPrivileges() {
		return privileges;
	}
	
	public String getPlayerName () {
		return playerName;
	}

}
