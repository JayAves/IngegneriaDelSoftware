package it.polimi.ingsw.ps29.view.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.InputOutput;

public class InputOutputGUI implements InputOutput, Observer, Runnable {
	private GUICore screen;
	private boolean endTime = false;
	private boolean running = true;
	private int timer;
	private long timeStart;
	
	/*
	 * per ogni tipo di messaggio sono presenti questi oggetti:
	 * - un booleano che indica quando il messaggio è pronto
	 * - un oggetto che contiene il messaggio stesso che deve essere tornato alla funzione chiamante
	 */
	private boolean actionChoiceReady;
	private ActionChoice actionChoice;
	
	public InputOutputGUI (String name) {
		screen = new GUICore(name);
		screen.addObserver (this);
		actionChoiceReady = false;
	}


	@Override
	public void showMessage(InteractionMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exchange askExchange(Exchange msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printBonusAction(BonusActionEffect effect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceType askSpecificPrivilege() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askAboutExcommunication() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showInfo(GameBoardDTO gameBoardDTO, TowersDTO towerdDTO,
		HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		// TODO Auto-generated method stub
		
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
		screen.tower.setCards(idCards);
		
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
		screen.tile.setImage("bonus_tiles/personalbonustile_"+tileId+".png");
		screen.excomm1.setImage("excomm_card/excomm_1_"+msg.getExCards().get(0).getId()+".png");
		screen.excomm2.setImage("excomm_card/excomm_2_"+msg.getExCards().get(1).getId()+".png");
		screen.excomm3.setImage("excomm_card/excomm_3_"+msg.getExCards().get(2).getId()+".png");
	}


	@Override
	public ActionChoice handleAskNextAction(ActionChoice msg) throws ExpiredTimeException {
		timeStart = System.currentTimeMillis();
		
		//ogni funzione aspetta finchè l'update riceve un messaggio (messageReady passa a TRUE)
		actionChoiceReady = false;
		setTimerControl();
		
		new Thread(this).start();
		
		while(!actionChoiceReady && !endTime) {
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (endTime)  {
			//sono uscito dal while ma l'utente non ha inserito nessun messaggio
			running = false;
			throw new ExpiredTimeException();
		}
		
		running = false;
		return actionChoice;
	}


	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) {
		// TODO Auto-generated method stub
		return null;
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
		
		if (arg instanceof ActionChoice) {
			actionChoice = (ActionChoice) arg;
			actionChoiceReady = true;
		}
		
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
}
