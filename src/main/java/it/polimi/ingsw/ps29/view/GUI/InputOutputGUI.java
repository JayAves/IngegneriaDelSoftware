package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FinalScores;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.InputOutput;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.BasePanel;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.BonusPanelCreator;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.ExchangePanelCreator;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.PrivilegesPanelCreator;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.VaticanPanelCreator;
import it.polimi.ingsw.ps29.view.GUI.utilities.PrintInfoFunctions;

/**
 * Graphical User Interface InputOutput
 * @author Pietro Melzi
 *
 */
public class InputOutputGUI implements InputOutput, Observer, Runnable {
	private GUICore screen;
	private boolean endTime = false;
	private boolean running = true;
	private int timer;
	private long timeStart;
	private BasePanel interactionPanel;
	private int round = 0;
	
	/*facilities used to communicate user choice
	 * interactionReady is true when the message is ready
	 * userMessage contains the message for the calling function
	 */
	private ResourceType resType;
	private int excommunicationChoice;
	private boolean interactionReady;
	private InteractionMessage userMessage;	
	
	public InputOutputGUI (String name) {
		screen = new GUICore(name);
		screen.addObserver (this);
		interactionReady = false;
	}

	@Override
	public void showMessage(InteractionMessage message) {
		if(message instanceof RejectMessage)
			JOptionPane.showMessageDialog(null, ((RejectMessage)message).getException().getMessage());
		else if (message instanceof FinalScores)
			printFinalScore((FinalScores)message);
			
	}
	
	@Override
	public Exchange askExchange(Exchange msg) throws ExpiredTimeException {
		ExchangePanelCreator epc = new ExchangePanelCreator (screen, msg);
		interactionPanel = new BasePanel (epc.createLeftPanel(), epc.createRightPanel(), "EXCHANGE ACTION");
		messageInTime();
		
		return msg;
	}

	@Override
	public ResourceType askSpecificPrivilege(boolean different, boolean multiple) throws ExpiredTimeException {
		PrivilegesPanelCreator ppc = new PrivilegesPanelCreator (screen, different, multiple);
		interactionPanel = new BasePanel (ppc.createLeftPanel(), ppc.createRightPanel(), "PRIVILEGE CHOICE");
		messageInTime();
		
		return resType; 
	}

	@Override
	public int askAboutExcommunication() throws ExpiredTimeException {
		VaticanPanelCreator vpc = new VaticanPanelCreator (screen);
		interactionPanel = new BasePanel(vpc.createLeftPanel(), vpc.createRightPanel(), "EXCOMMUNICATION CHOICE");
		messageInTime();
		
		return excommunicationChoice;
	}

	@Override
	public void showInfo(InfoForView info, GameBoardDTO gameBoardDTO, TowersDTO towersDTO,
		HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		
		//reset previous action on player's board
		screen.tower.deleteIndexSpacePressed();
		screen.console.setText("");
		
		int spaceIndex = PrintInfoFunctions.getIndex(info.space, info.floor);
		FamilyMemberDTO fam = PrintInfoFunctions.getFamiliarDTO(info);
		
		screen.tower.showFamiliar(spaceIndex, fam);
		//if a card is taken, getIdCards will return -1 as ID of that card
		screen.tower.setCards(towersDTO.getIdCards(), false);
		screen.tower.repaint();
		
		ArrayList<Integer> ids = PrintInfoFunctions.createIdCards(personalBoardsDTO.get(screen.playerName).getIdCards());
		screen.personal.setCards(ids, true);
		PrintInfoFunctions.printUpdatedResources(screen, personalBoardsDTO.get(screen.playerName).getResources());
		
	}

	@Override
	public void showTowerAndDices(TowersAndDicesForView msg) {
		round++;
		screen.frame.setTitle("Player: "+msg.getName()+" - Round "+round);
		screen.setTowers(msg.getTowers());
		TowersDTO towers = msg.getTowers();
		ArrayList<Integer> idCards = new ArrayList<Integer>();
		String[] types = {"territory", "character", "building", "venture"};
		for(int i=0; i<types.length; i++)
			for(CardDTO card: towers.getTowers().get(types[i]))
				idCards.add(card.getId());
		screen.tower.setCards(idCards, true);
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int value: msg.getDices())
			values.add(value);
		
		screen.tower.setValueDices(values);
		screen.tower.setPlayerOrder(msg.getPlayerOrder());
		screen.tower.cleanCoordFamiliar();
		
	}

	@Override
	public void showFirstInfo(FirstBoardInfo msg) {
		screen.frame.setTitle("Player: "+msg.getName()+" - Round: "+round);		
		boolean ready = false;
		
		do {
			try {
				int tileId = msg.getTiles().get(msg.getName()).getId();
				screen.tile.setImage("bonus_tiles/personalbonustile_"+tileId+".png");
				screen.excomm1.setImage("excomm_card/excomm_1_"+msg.getExCards().get(0).getId()+".png");
				screen.excomm2.setImage("excomm_card/excomm_2_"+msg.getExCards().get(1).getId()+".png");
				screen.excomm3.setImage("excomm_card/excomm_3_"+msg.getExCards().get(2).getId()+".png");
				screen.ts1.setText("I: "+msg.getThreshold()[0]);
				screen.ts2.setText("II: "+msg.getThreshold()[1]);
				screen.ts3.setText("III: "+msg.getThreshold()[2]);
				ready = true;
			}
			catch (NullPointerException e) {
				ready = false;
			}
			
		} while (!ready);
		
		
		ready = false;
		do {
			try {
				for(Map.Entry<String, ArrayList<ResourceDTO>> row: msg.getInitialResources().entrySet())
					if(msg.getName().equals(row.getKey()))
						PrintInfoFunctions.printUpdatedResources(screen, row.getValue());
				ready = true;
			}
			catch (NullPointerException e) {
				ready = false;
			}
			
		} while(!ready);
		
	}


	@Override
	public ActionChoice handleAskNextAction(ActionChoice msg) throws ExpiredTimeException {
		//save the message sended by server where is showed the leader situation
		screen.setMessage(msg);
		
		screen.console.setText("IT'S YOUR TURN!");
		screen.doAction.setEnabled(true);
		screen.doAction.setBackground(Color.RED);
		screen.noAction.setEnabled(true);
		screen.servants.setValue(0);
		
		messageInTime();
		
		screen.console.setText("SENDING INFO TO SERVER...");
		screen.doAction.setEnabled(false);
		screen.doAction.setBackground(null);
		screen.noAction.setEnabled(false);
		return (ActionChoice)userMessage;
	}


	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) throws ExpiredTimeException {
		BonusPanelCreator bpc = new BonusPanelCreator (screen, msg);
		interactionPanel = new BasePanel (bpc.createLeftPanel(), bpc.createRightPanel(), "BONUS ACTION");
		messageInTime();
		
		return msg;
	}


	@Override
	public void setTimer(int timer) {
		this.timer = timer;
		
	}


	@Override
	public int getTimer() {
		return timer;
	}


	@Override
	public long getTimeStart() {
		return timeStart;
	}


	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof InteractionMessage) 
			userMessage = (InteractionMessage)arg;
		
		else if (arg instanceof ResourceType) 
			resType = (ResourceType) arg;
		
		else if (arg instanceof Integer)
			excommunicationChoice = new Integer (arg.toString());
		
		interactionReady = true;
		
	}
	
	@Override
	public void run() {
		while (running) {
			
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			if (System.currentTimeMillis() - this.getTimeStart() > timer)
				endTime = true;
		}
	}

	private void setTimerControl () {
		running = true;
		endTime = false;
	}
	
	private boolean messageInTime () throws ExpiredTimeException {
		//set the initial time of the action
		timeStart = System.currentTimeMillis();
		
		//each function waits until receives a message (messageReady goes TRUE)
		interactionReady = false;
		setTimerControl();
		
		//thread to handle timeout
		new Thread(this).start();
		
		while(!interactionReady && !endTime) {
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
		
		if (endTime)  {
			//out from the while, but the user doesn't insert anything
			running = false;

			//notify on GUI that time is expired
			screen.frame.setTitle("Player: "+screen.playerName+" - DISCONNETTED");
			screen.doAction.setEnabled(false);
			screen.doAction.setBackground(null);
			screen.noAction.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Time expired! Restart main to get back in the game!");
			
			if(interactionPanel!=null)
				interactionPanel.dispose();
			throw new ExpiredTimeException();
		}
		
		running = false;
		return true;
	}

	@Override
	public void restore(RestoreSituation msg) {
		//get the index of coordinates to show family members on the towers
		for(Map.Entry <String, HashMap<String, ArrayList<FamilyMemberDTO>>> first: msg.getGameBoard().getBoardMap().entrySet()) 
			for (Map.Entry<String, ArrayList<FamilyMemberDTO>> second: first.getValue().entrySet()) 
				for(FamilyMemberDTO famDTO: second.getValue()) {
					int index = PrintInfoFunctions.getIndexFromHashMap(first.getKey(), second.getKey());
					screen.tower.showFamiliar(index, famDTO);
				}
		
		//set the cards (with ev null cards)
		screen.tower.setCards(msg.getFirstInfo().getTowers().getTowers().getIdCards(), true);
		
		//get personal board of the player
		PersonalBoardDTO pbSearched = null;
		for(PersonalBoardDTO pbDTO: msg.getPersonalBoard())
			if(pbDTO.getName().equals(screen.playerName))
				pbSearched = pbDTO;
		
		//show cards and resources of the player
		
		ArrayList<Integer> ids = PrintInfoFunctions.createIdCards(pbSearched.getIdCards());
		screen.personal.setCards(ids, true);
		PrintInfoFunctions.printUpdatedResources(screen, pbSearched.getResources());
		
			
	}
	
	public void printFinalScore (FinalScores msg) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<msg.getPlayers().size(); i++)
			sb.append("Player: "+msg.getPlayers().get(i)+" - Score: "+msg.getScores()[i]+"\n");
		screen.console.setText(sb.toString());
		
		int max = msg.getScores()[0];
		int iMax=0;
		for(int i=1; i<msg.getScores().length; i++)
			if(msg.getScores()[i]>max) {
				max = msg.getScores()[i];
				iMax = i;
			}
		
		if(screen.getPlayerName().equals(msg.getPlayers().get(iMax)))
			screen.console.append("\n\nCONGRATULATIONS YOU WIN\n");
	}
	
	
	
}
