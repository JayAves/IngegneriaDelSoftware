/*package it.polimi.ingsw.ps29.model.provvisorio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

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
import it.polimi.ingsw.ps29.view.GUI.ImageToPrint;
import it.polimi.ingsw.ps29.view.GUI.PlayerListener;
import it.polimi.ingsw.ps29.view.GUI.PrintTower;

public class GUICore {
	
	JFrame frame;
	TowersDTO towers;
	
	JTextArea  statusBar;
	PrintTower board;
	JPanel gamePanel;
	
	ImageToPrint tile;
	ImageToPrint personal;
	ImageToPrint cardPreview;
	JButton showVentures;
	JButton showCharacters;
	JButton prevBoard;
	JButton nextBoard;
	
	ButtonGroup familiar;
	JSpinner servants;
	JButton sendAction;
	
	ImageToPrint excommunication1;
	ImageToPrint excommunication2;
	ImageToPrint excommunication3;
	
	JTextArea console;
	
	public GUICore () {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				frame = new JFrame ();
				frame.setVisible(true);
				//frame.setSize(780, 1200);
				//frame.setResizable(false);
				setFrame (frame);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				
				System.out.println(statusBar.getSize().getWidth()+" - "+statusBar.getSize().getHeight());
				System.out.println(board.getSize().getWidth()+" - "+board.getSize().getHeight());
				System.out.println(gamePanel.getSize().getWidth()+" - "+gamePanel.getSize().getHeight());
				System.out.println(frame.getSize().getHeight());
			}
		});
		
	}
	
	
	//function where i create GUI
	void setFrame (JFrame frame) {
		frame.setLayout(new BorderLayout());
		
		//1.status bar
		statusBar = new JTextArea();
		statusBar.setText("Waiting for other players...");
		statusBar.setEditable(false);
		statusBar.setPreferredSize(new Dimension (0, 20));
		frame.add(statusBar, BorderLayout.PAGE_START);
		
		//2.game board
		ArrayList<Integer> id = new ArrayList<Integer> ();
		for(int i=0; i<16; i++)
			id.add(i+1);
		board = new PrintTower(id, this);
		board.setPreferredSize(new Dimension (board.getWidthImage(), board.getHeightImage()));
		frame.add(board, BorderLayout.LINE_START);
		//frame.add(board);
		
		//3.panel of the game
		gamePanel = new JPanel ();
		setGamePanel (gamePanel);
		frame.add(gamePanel, BorderLayout.CENTER);
		//frame.add(gamePanel);
	}
	
	//function where i create center-right side of the GUI
	public void setGamePanel (JPanel panel) {
		panel.setLayout(new BorderLayout());
		
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
		tile = new ImageToPrint("bonustile.png", this);
		tile.setPreferredSize(new Dimension(50, 0));
		
		//2.personal board
		personal = new ImageToPrint("personalboard.jpg", this);
		personal.setPreferredSize(new Dimension (personal.getWidthImage()+70, personal.getHeightImage()+40));
		
		//3.buttons and preview
		JPanel rightTopPanel = new JPanel ();
		rightTopPanel.setPreferredSize(new Dimension(200, 0));
		rightTopPanel.setLayout(new BorderLayout());
			
			
		
			//preview
			cardPreview = new ImageToPrint("devcards_f_en_c_1.png", this);
			rightTopPanel.add(cardPreview, BorderLayout.CENTER);
			
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setPreferredSize(new Dimension(0, 100));
			buttonsPanel.setLayout(new GridLayout(2, 2));
			
				//venture button
				showVentures = new JButton("Ventures");
				buttonsPanel.add(showVentures);
				
				//character button
				showCharacters = new JButton("Characters");
				buttonsPanel.add(showCharacters);
				
				//prev button
				prevBoard = new JButton("Prev Board");
				buttonsPanel.add(prevBoard);
				
				//next button
				nextBoard = new JButton("Next Board");
				buttonsPanel.add(nextBoard);
			
			rightTopPanel.add(buttonsPanel, BorderLayout.PAGE_END);
			
		panel.add(tile, BorderLayout.LINE_START);
		panel.add(personal, BorderLayout.CENTER);
		panel.add(rightTopPanel, BorderLayout.LINE_END);
		
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
		black.setActionCommand("Black");
		JRadioButton white = new JRadioButton("White");
		white.setActionCommand("White");
		JRadioButton orange = new JRadioButton("Orange");
		orange.setActionCommand("Orange");
		JRadioButton neutral = new JRadioButton("Neutral");
		neutral.setActionCommand("Neutral");
		black.setSelected(true);
		
		familiar = new ButtonGroup();
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
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		servants = new JSpinner(model);
		buttonsPanel.add(servants);
		
		sendAction = new JButton("Do Action!");
		sendAction.addMouseListener(new PlayerListener(this));
		buttonsPanel.add(sendAction);
		
		panel.add(pointsPanel);
		panel.add(familyPanel);
		panel.add(buttonsPanel);
		
	}
	
	//panel for stats, leader, excommunications and console
	public void createSouthPanel (JPanel panel) {
		panel.setLayout(new GridLayout(1, 6));
		
		//1.leader button
		ImageToPrint leader = new ImageToPrint("leader.jpg", this);
		panel.add(leader);
		panel.setPreferredSize(new Dimension(0, 180));
		
		//2.excommunication cards
		excommunication1 = new ImageToPrint("excomm_back_1.png", this);
		panel.add(excommunication1);
		
		excommunication2 = new ImageToPrint("excomm_back_2.png", this);
		panel.add(excommunication2);
		
		excommunication3 = new ImageToPrint("excomm_back_3.png", this);
		panel.add(excommunication3);
		
		//3.stats
		JTextArea stats = new JTextArea();
		stats.setText("stats of the match..");
		stats.setEditable(false);
		panel.add(stats);
		
		//4.console
		console = new JTextArea();
		console.setText("console..");
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		panel.add(scrollPane);
		
	}

	public TowersDTO getTowers () {
		return towers;
	}
	
	public void setTowers (TowersDTO towers) {
		this.towers = towers;
	}
	
	public void zoomImage (int idCard) {
		cardPreview.setImage("cards/devcards_f_en_c_"+idCard+".png");
	}
	
	public static void main(String[] args) {
		new GUICore ();
	}

}
*/