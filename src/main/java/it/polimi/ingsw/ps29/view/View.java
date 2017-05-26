package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.StateOfActionIdentifier;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private ArrayList <Player> playerOrder;
	
	public View (String inputType) {
		inputOutput = inputOutputFactory.getInput(inputType);
		playerOrder = null;
	}
	
	
	public String getPlayerActive () {
		return playerOrder.get(0).getName();
	}
	
	private void setPlayerOrder (ArrayList<Player> order) {
		playerOrder = order;
	}
	
	
	private int[] askNextAction(GameBoard board) { 
		int[] move = new int[4];
		/*
		move[0]= board.getIdPartita();
		move[1]=inputOutput.askTypeOfAction();
		move[2]=inputOutput.askNumberOfServants();
		move[3]=inputOutput.askFamiliarColor();
		*/
		return move;
	}
	
	/*public Move askNextAction (GameBoard board) {
		ActionSpace space = MoveCreator.getActionSpace(board, inputOutput.askTypeOfAction());
		FamilyMember member = MoveCreator.getFamilyMember(board, inputOutput.askFamiliarColor());
		return new Move (board.getIdPartita(), board.getPlayers().get(0), space, inputOutput.askNumberOfServants(), member);
	}*/
	
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

}
