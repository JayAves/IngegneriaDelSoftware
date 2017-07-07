package it.polimi.ingsw.ps29.view.GUI;

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
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.InputOutput;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.BonusPanelCreator;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.ExchangePanelCreator;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.PanelBase;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.PrivilegesPanelCreator;
import it.polimi.ingsw.ps29.view.GUI.utilities.PrintInfoFunctions;

public class InputOutputGUI implements InputOutput, Observer, Runnable {
	private GUICore screen;
	private boolean endTime = false;
	private boolean running = true;
	private int timer;
	private long timeStart;
	private PanelBase interactionPanel;
	private ResourceType resType;
	
	/*
	 * per ogni tipo di messaggio sono presenti questi oggetti:
	 * - un booleano che indica quando il messaggio è pronto
	 * - un oggetto che contiene il messaggio stesso che deve essere tornato alla funzione chiamante
	 */
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
			//screen.console.append(((RejectMessage)message).getException().getMessage());
			JOptionPane.showMessageDialog(null, ((RejectMessage)message).getException().getMessage());
		
	}

	@Override
	public Exchange askExchange(Exchange msg) throws ExpiredTimeException {
		ExchangePanelCreator epc = new ExchangePanelCreator (screen, msg);
		interactionPanel = new PanelBase (epc.createLeftPanel(), epc.createRightPanel(), "EXCHANGE ACTION");
		messageInTime();
		
		return msg;
	}

	@Override
	public ResourceType askSpecificPrivilege() throws ExpiredTimeException {
		PrivilegesPanelCreator ppc = new PrivilegesPanelCreator (screen, null);
		interactionPanel = new PanelBase (ppc.createLeftPanel(), ppc.createRightPanel(), "PRIVILEGE CHOICE");
		messageInTime();
		
		return resType; 
	}

	@Override
	public int askAboutExcommunication() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showInfo(InfoForView info, GameBoardDTO gameBoardDTO, TowersDTO towersDTO,
		HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		
		//reset previous action on that board
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
		
	}

	@Override
	public ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showFirstInfo(FirstBoardInfo msg) {
		screen.frame.setTitle(msg.getName());
		int tileId = msg.getTiles().get(msg.getName()).getId();
		boolean ready = false;
		
		do {
			try {
				screen.tile.setImage("bonus_tiles/personalbonustile_"+tileId+".png");
				screen.excomm1.setImage("excomm_card/excomm_1_"+msg.getExCards().get(0).getId()+".png");
				screen.excomm2.setImage("excomm_card/excomm_2_"+msg.getExCards().get(1).getId()+".png");
				screen.excomm3.setImage("excomm_card/excomm_3_"+msg.getExCards().get(2).getId()+".png");
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
		//timer control
		messageInTime();
		return (ActionChoice)userMessage;
	}


	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) throws ExpiredTimeException {
		BonusPanelCreator bpc = new BonusPanelCreator (screen, msg);
		interactionPanel = new PanelBase (bpc.createLeftPanel(), bpc.createRightPanel(), "BONUS ACTION");
		messageInTime();
		
		return msg;
	}


	@Override
	public void setTimer(int timer) {
		this.timer = timer;
		
	}


	@Override
	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}


	@Override
	public long getTimeStart() {
		// TODO Auto-generated method stub
		return timeStart;
	}


	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof InteractionMessage) 
			userMessage = (InteractionMessage)arg;
		
		else if (arg instanceof ResourceType) 
			resType = (ResourceType) arg;
		
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
				e.printStackTrace();
			}
		}
		
		if (endTime)  {
			//out from the while, but the user doesn't insert anything
			running = false;
			if(interactionPanel!=null)
				interactionPanel.dispose();
			throw new ExpiredTimeException();
		}
		
		running = false;
		return true;
	}
	
	
}
