package it.polimi.ingsw.ps29.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.Move;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	
	public View (String inputType, String n) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = n;
		System.out.println("Mi hanno creato: Sono la view del Player " + namePlayer);
	}
	
	public Move askNextAction () {
		Move move = new Move();
		int[] choose = inputOutput.askTypeOfAction();
		int servants = inputOutput.askNumberOfServants();
		int familyMember = inputOutput.askFamiliarColor();
		return move;
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
