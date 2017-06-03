package it.polimi.ingsw.ps29.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	
	public View (String inputType, String n) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = n;
		System.out.println("Mi hanno creato: Sono la view del Player " + namePlayer);
	}
	
	public void askNextAction () {
		int [] choice = new int [4];
		int[] temp = inputOutput.askTypeOfAction();
		choice[0] = temp[0];
		choice[1] = temp[1];
		choice[2] = inputOutput.askNumberOfServants();
		choice[3] = inputOutput.askFamiliarColor();
		UserChoice move = new UserChoice(namePlayer, choice);
		setChanged();
		notifyObservers(move);
	}
	
	public void askBonusAction (BonusActionEffect effect) {
		UserChoice move;
		int[] userChoice = new int[4];
		userChoice[1] = 0;
		
		switch(effect.getType()) {
			case "harvest":
				userChoice[0] = 1;
				break;
			case "production":
				userChoice[0] = 2;
				break;
			case "territory":
				userChoice[0] = 3;
				break;
			case "building":
				userChoice[0] = 4;
				break;
			case "character":
				userChoice[0] = 5;
				break;
			case "venture":
				userChoice[0] = 6;
				break;
			default:
				userChoice[0] = 0;
				break;
			
		}
		
		System.out.println("\nBonus action value: "+effect.getValue());
		System.out.println("\nType of action: "+effect.getType());
		if(effect instanceof BonusPlacementEffect){
			System.out.println("\nDicount:\n");
			for (Resource res: ((BonusPlacementEffect) effect).getDiscount())
				System.out.println(res);
			userChoice[1] = inputOutput.askFloor();
		}
		userChoice [2] = inputOutput.askNumberOfServants();
		userChoice[3] = -1;
		move = new UserChoice(namePlayer, userChoice);
		setChanged();
		notifyObservers(move);
	}
	
	public void askAboutExchange (ExchangeResourcesEffect effect) {
		setChanged();
		notifyObservers(inputOutput.askExchange(effect));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public String getPlayerActive () {
		return playerOrder.get(0).getName();
	}
	
	private void setPlayerOrder (ArrayList<Player> order) {
		playerOrder = order;
	}
	
	
	private int[] askNextAction(GameBoard board) { 
		int[] move = new int[4];
		
		//move[0]= board.getIdPartita();
		move[1]=inputOutput.askTypeOfAction();
		move[2]=inputOutput.askNumberOfServants();
		move[3]=inputOutput.askFamiliarColor();
		
		return move;
	}
	
	public Move askNextAction (GameBoard board) {
		ActionSpace space = MoveCreator.getActionSpace(board, inputOutput.askTypeOfAction());
		FamilyMember member = MoveCreator.getFamilyMember(board, inputOutput.askFamiliarColor());
		return new Move (board.getIdPartita(), board.getPlayers().get(0), space, inputOutput.askNumberOfServants(), member);
	}
	
	private void manageStateOfAction (GameBoard board, StateOfActionIdentifier stateOfAction) {
		if(stateOfAction == StateOfActionIdentifier.PERFORMED) {
			//azione eseguita, cambia il giocatore di turno
			Player temp = playerOrder.get(0);
			playerOrder.remove(0);
			playerOrder.add(temp);
			notifyObservers(askNextAction (board));
		}
		else if (stateOfAction == StateOfActionIdentifier.REJECTED) {
			//azione respinta, bisogna chiedere al giocatore di fare una nuova mossa
			inputOutput.showMessage("Azione non possibile");
		}
		else if (stateOfAction == StateOfActionIdentifier.INCOMPLETE) {
			//occorrono nuove informazioni per svolgere l'azione (es. azione bonus)
			inputOutput.showMessage("Richiesta nuove informazioni");
		}
	}
	
	public void printPlayerActive () {
		inputOutput.showMessage("\nGIOCATORE: "+getPlayerActive());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(!(o instanceof Match)){
			throw new IllegalArgumentException();			
		}
		if(playerOrder==null) //la prima volta è null il vettore dei player
			setPlayerOrder(((GameBoard)arg).getPlayers());
		inputOutput.showUpdatedSituation ((GameBoard)arg); //da implementare nella classe GUI e nella classe CLI
		manageStateOfAction ((GameBoard)arg, ((GameBoard)arg).getStateOfAction());
	}
	
	public void informController (Move move) {
		setChanged();
		notifyObservers(move);
	}
*/
}
