package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FinalScores;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.ExchangeResources;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.action.actionstates.StateOfActionIdentifier;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstablishState;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.StateOfRoundIdentifier;
import it.polimi.ingsw.ps29.model.game.roundstates.VaticanReportState;
import it.polimi.ingsw.ps29.server.ClientThread;

/**
 * Stands between {@link View } s and  {@link Match} (Model) . Every communication between the two passes through it. 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class Controller extends Observable implements Observer{
	
	private Match model;
	private Map<String, ClientThread> views;
	private RoundState roundState;
	private ActionState stateOfAction; 
	private InfoForView info;
	private boolean sendInfo;
	private boolean callGameEngine = true;
	
	//sendInfo is set false at the beginning of any user input management 
	//if there's anything to be notified it changes to true value.
	
	
	public Controller (Match model) {
		this.model = model;
		views =  new HashMap <String, ClientThread> ();
		roundState = new RoundSetupState();
		stateOfAction = new ToEstablishState();
	}
	
	public void addView (String playerName, ClientThread view) {
		if(!views.containsKey(playerName))
			views.put(playerName, view);
	}
	
	public void removeView(String playerName, ClientThread view) {
		if(views.containsKey(playerName))
			views.remove(playerName);
		System.out.println("\nPlayer "+playerName+ " is back in Game");
	}
	
	/**
	 * Starts interaction with current player. Sends the correct InteractionMessage according to the current ActionState. 
	 * If player is disconnected does a playerInactivePlacement.
	 * If all players are disconnected ends the game.
	 * 
	 * @see ActionState
	 * 
	 */
	
	public void callCorrectView () {
		
		if(PlayersConnected()) {
			
			String playerName = model.getCurrentPlayer().getName();
			ClientThread view = views.get(playerName);
 			
			//changes state before interacting with View, to do the correct request
			stateOfAction = stateOfAction.beforeAction();

			if  (view.getInGame()) {
				//build of the object used in the interaction with the user
				InteractionMessage object = stateOfAction.objectForView(playerName);
				
				if(stateOfAction.getState().equals(StateOfActionIdentifier.TO_ESTABLISH.getName())) {
					ArrayList<ArrayList<Object>> leaderSituation;
					leaderSituation = new ArrayList<ArrayList<Object>>();
					
					//set up of leader situation  
					leaderSituation = model.getBoard().getPlayerByName(playerName).getPersonalBoard().buildLeaderChoice();
					((ActionChoice)object).setLeaderSituation(leaderSituation);
				}
				
				view.startInteraction (object);	
			} 
			
			else 
				//set a familiar busy to ensure the correct flow of the game
				inactivePlacement();
		}
		
		else {
			System.out.println("All players are disconnected!");
			setChanged();
			notifyObservers();
			
			// for future expansion - save the game's stat on disk for future use.
		}
	}
	
	
	/**
	 * Puts a random familiar in the noAction space, notifies the updated situation to all active players' views and calls gameEngine to let the game go on.
	 */
	
	private void inactivePlacement () {
		info = PlayerInactiveFunctions.playerInactivePlacement(model.getCurrentPlayer().getName(), model,
				model.getCurrentPlayer().getColor(), placeRandomFamiliar());
		
		generateResourcesDTOAndSend ();
		
		stateOfAction= new PerformedState();
		stateOfAction = stateOfAction.afterAction(model);
		
		//if callGameEngine is FALSE, update method will not call gameEngine()
		callGameEngine = false;
		gameEngine();
	}
	
	
	/**
	 * Checks if there are active players.
	 * @return true after the first active player found
	 */
	
	private boolean PlayersConnected() {
		for(Map.Entry<String, ClientThread> entry: views.entrySet()) 
			if (entry.getValue().getInGame())
				return true;
		
		return false;
	}

	
	@Override
	public void update(Observable o, Object arg) {
		callGameEngine = true;
		
		if (o instanceof ClientThread) {
			initInfoForView();
			VisitorMessages visitor = new VisitorMessages();
			
			//perform the specific type of action with pattern VISITOR
			((InteractionMessage)arg).visit(visitor);
			
			//if the situation of game is changed after the action...
			if(sendInfo) 
				generateResourcesDTOAndSend();
			
			//if gameEngin is not called yet
			if(callGameEngine) 
				gameEngine();
		}
		
		else 
			throw new IllegalArgumentException();
	}
	
	
	/**
	 * Transforms an ActionChoice message in a Move and triggers corresponding action in Model. After feedback, prepares info to be sent to View. 
	 * @param arg the message to handle
	 * @param power dice power, meaning Action's power
	 */
	public void handleInputAction (ActionChoice arg, int power) {
		Move move= ChoiceToMove.createMove(arg, model.getBoard());
		
		//power>-1 for BonusAction
		if(power>-1) 
			move.getFamiliar().setPower(power);
		
		//create the action based on the user choice
		Action action = ControllerSupporter.createAction(model, move, arg);
		
		try{
			stateOfAction = action.actionHandler();
			
			if(stateOfAction.getState().equals(StateOfActionIdentifier.PERFORMED.getName())||
				stateOfAction.getState().equals(StateOfActionIdentifier.ASK_EXCHANGE.getName())||
				stateOfAction.getState().equals(StateOfActionIdentifier.BONUS_ACTION.getName())||
				stateOfAction.getState().equals(StateOfActionIdentifier.PRIVILEGES.getName())) {
				// if a placement was made, updates Gameboard's info to show in View and players' resources situation
				
				info.space = arg.getChoice(0);
				info.floor = arg.getChoice(1);
				info.familiar = arg.getChoice(3);
				sendInfo = true;
			}
		
		} catch (RejectException exception) {
			//show the reason why the placement is failed
			views.get(model.getCurrentPlayer().getName()).startInteraction(
					new RejectMessage(model.getCurrentPlayer().getName(), exception));
		}
	}
	
	
	/**
	 * Manages BonusChoice messages. From a BonusChoice builds an ActionChoice.
	 * @param msg bonusAction message
	 * @return  ActionChoice to be transformed in Move
	 */
	
	public ActionChoice handleBonusAction (BonusChoice msg) {
		ActionChoice choice = ControllerSupporter.bonusActionChoice(msg);
		
		//bonus action is not performed
		if (choice.getChoice(2)<0)
			choice.setChoice(0, 12);
		
		//set the floor
		if(msg.getFloor()>0)
			choice.setChoice(1, msg.getFloor());
		
		choice.setChoice(2, msg.getServants());
		choice.setChoice(3,  -1);
		
		return choice;
	}
	
	
	private void handleExchangeAction (Exchange msg) {
		ExchangeResources res = new ExchangeResources(model, stateOfAction);
		stateOfAction = res.exchangeHandler(msg);
		sendInfo = true;
		
		if(stateOfAction.getState().equals(StateOfActionIdentifier.PERFORMED.getName())) 
			if(model.getCurrentPlayer().getPersonalBoard().getSpecificResource("privilege").getAmount()>0) {
				stateOfAction = new PrivilegesState(stateOfAction, 
						model.getCurrentPlayer().getPersonalBoard().getSpecificResource("privilege").getAmount(), true);
				String name = model.getCurrentPlayer().getName();
				
				views.get(name).startInteraction(stateOfAction.objectForView(name));
				stateOfAction = stateOfAction.afterAction(model);
				
			}
	}
	
	
	private void handlePrivilegesChoice (PrivilegeChoice msg) {
		((PrivilegesState)stateOfAction).handlePrivileges(model.getCurrentPlayer(), msg.getChoices());
		
		stateOfAction = stateOfAction.afterAction(model); //back to previous state
		stateOfAction = stateOfAction.afterAction(model); //do the instruction of previous state
		sendInfo = true;
	}
	
	
	private void handleExcommunication (VaticanChoice msg) {
		model.getBoard().getPlayerByName(msg.getName()).setVaticanReportPerformed(true);
		
		((VaticanReportState)roundState).handleVaticanChoice(msg, model);
		model.getBoard().changePlayerOrder();
		sendInfo=true;
	}
	
	
	private void handlePlayerInfoMessage (PlayerInfoMessage msg) {
		
		//show the info to each user
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(msg);
		
		//if player disconnetted is also current player
		if(msg.getName().equals(model.getCurrentPlayer().getName())) {
			
			if (roundState.getState() == StateOfRoundIdentifier.VATICAN_REPORT)
				handleExcommunication(PlayerInactiveFunctions.playerInactiveVatican(model.getCurrentPlayer().getName()));
			
			else if (stateOfAction.getState().equals(StateOfActionIdentifier.TO_ESTABLISH.getName())) 
				inactivePlacement();
			
			else if (stateOfAction.getState().equals(StateOfActionIdentifier.BONUS_ACTION.getName()) ||
					stateOfAction.getState().equals(StateOfActionIdentifier.ASK_EXCHANGE.getName()) ) 
				inactivePlayerEnd();
			
			
			else if (stateOfAction.getState().equals(StateOfActionIdentifier.PRIVILEGES.getName())) {
				model.getCurrentPlayer().getPersonalBoard().getResources().removeResource(ResourceType.PRIVILEGE);
				inactivePlayerEnd();
			}
			
		}
		
		//player disconnects when he's not his turn
		else 
			callGameEngine = false; 
	}
	
	private void restoreSituation (RestoreSituation msg) {
		for (Map.Entry<String, ClientThread> view: views.entrySet())
			if(view.getKey().equals(msg.getName()))
				view.getValue().startInteraction(CreationMessagesSupporter.restoreSituation(model, msg));
		sendInfo = true;
		callGameEngine = false;
	}
	
	
	private void inactivePlayerEnd () {
		stateOfAction= new PerformedState();
		stateOfAction = stateOfAction.afterAction(model);
		sendInfo = false;
	}
	
	
	private int placeRandomFamiliar() {
		if (!model.getCurrentPlayer().getFamiliarByColor(DiceColor.NEUTRAL).getBusy()) {
			model.getCurrentPlayer().getFamiliarByColor(DiceColor.NEUTRAL).setBusy(true);
			return 4;
		}
			
		else {
			ArrayList<FamilyMemberInterface> freeMembers= new ArrayList<FamilyMemberInterface>();
			for (FamilyMemberInterface familiar: model.getCurrentPlayer().getFamily())
				if (!familiar.getBusy())
					freeMembers.add(familiar);
			
			Random random= new Random();
			FamilyMemberInterface randomMember= freeMembers.get(random.nextInt(freeMembers.size()));
			model.getCurrentPlayer().getFamiliarByColor(randomMember.getFamiliarColor()).setBusy(true);
			
			switch(randomMember.getFamiliarColor()) {
			case BLACK:
				return 1;
			case WHITE:
				return 2;
			case ORANGE:
				return 3;
			default:
				System.out.println("Error in random placement");
				return -1;
						
			}
		}
	}

	
	/**
	 * Allows the game to flow. Triggers correct Controller's routine for the current roundState.
	 * 
	 */
	
	public void gameEngine () {
		
		if (roundState.getStateNumber()==1 || roundState.getStateNumber()==4) { 
			//change to state two
			roundState = roundState.doAction(model.getRound(), model); 
			CreationMessagesSupporter.initGameMessagesForView(model, views);
			callCorrectView();
		}
		
		else {
			
			if (roundState.getStateNumber()==2)
				if(isStateTwoTerminated() && !(stateOfAction.getState().equals(StateOfActionIdentifier.BONUS_ACTION.getName())) ) {
					roundState = new VaticanReportState(model);
					askForExcommunication();
				} else
					callCorrectView();
			
			else if (roundState.getStateNumber()==3)
				if(isStateThreeTerminated()) 
					endVaticanState();
				else
					askForExcommunication();
		}
			
	}

	
	private boolean isStateTwoTerminated() {
		for(Player player: model.getBoard().getPlayers())
			for(FamilyMemberInterface member: player.getFamily())
				if (!member.getBusy())
					return false;
		
		//each familiar is busy and the last action is performed
		return (stateOfAction.getState().equals("performed"));
	}

	
	private boolean isStateThreeTerminated() {
		for (Player player: model.getBoard().getPlayers())
			if (!player.isVaticanReportPerformed())
				return false;
		//vatican report is managed for each player
		return true;
	}
	
	
	private void conclusion () {
		
		for (Player player : model.getBoard().getPlayers()){
			player.passPersonalBoard();
			player.getFinalPoints();
			
			if (!player.getVentureCardPenalty())
				for (Card card : player.getPersonalBoard().getCards("venture"))
					for (Effect effect : card.getPermanentEffects())
						effect.performEffect(player);
		}		
			model.getBoard().assignPointsForMilitaryTrack();
		
		int[] scores= new int[model.getBoard().getPlayers().size()];
		ArrayList<String> names= new ArrayList<String>();
		int j=0;
		
		for (Player player: model.getBoard().getPlayers()) {
			
			names.add(player.getName());
			scores[j]= player.getPersonalBoard().getSpecificResource("victory").getAmount();
			j++;
		}
		
		//send final scores
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(new FinalScores(view.getValue().getClientName(), scores.length, names ));
		
		//Room will be closed
		setChanged();
		notifyObservers();
		
	}
	
	
	private void askForExcommunication () {
		
		if(model.getRound()%2==0) { //funzioni da svolgere solo nei turni pari
			int tresHold = model.getBoard().getExcommunicationTreshold(model.getRound());
			
			while(!model.getCurrentPlayer().canAskSubstain(tresHold) && !isStateThreeTerminated()) {
				//the player doesn't have enough points to substain but the state isn't terminated yet
				model.getCurrentPlayer().setVaticanReportPerformed(true);
				((VaticanReportState)roundState).excommunicatePlayer(model.getCurrentPlayer());
				model.getBoard().changePlayerOrder();
			}
		
			if(!isStateThreeTerminated()) {
				//ask to a player if he want to substain the curch
				String player = model.getCurrentPlayer().getName();
				
				if(views.get(player).getInGame()) {
					VaticanChoice msg = new VaticanChoice (player);
					views.get(player).startInteraction(msg);
				} 
				
				else {
					handleExcommunication(PlayerInactiveFunctions.playerInactiveVatican(model.getCurrentPlayer().getName()));
					gameEngine();
				}
			}
		
			else 
				//conclusion of the state
				endVaticanState();
			
		}
		
		else 		
			endVaticanState();	//in odd rounds the Vatican report isn't performed	
	}
	
	
	public void endVaticanState () {		
		roundState= roundState.doAction(model.getRound(), model);	//go to state four
		roundState = roundState.doAction(model.getRound(), model); 	//do the action is state four
		
		if(model.endOfMatch) //if the match ends
			conclusion();
		else {
			CreationMessagesSupporter.initRoundMessagesForView(model, views); 
			callCorrectView(); //a new round starts
		}
		
	}
	
	
	private void initInfoForView () {
		//if I need to send info to view after this method I'll set sendInfo to TRUE
		sendInfo = false;
		info = new InfoForView(model.getCurrentPlayer().getName());
		info.playerColor = model.getCurrentPlayer().getColor();
	}
	
	private void generateResourcesDTOAndSend () {
		info.resSituation = new HashMap<String, ArrayList<ResourceDTO>>();
		for(Player player: model.getBoard().getPlayers()) {
			ArrayList<ResourceDTO> resCon = new ArrayList<ResourceDTO>();
			for(ResourceInterface res: player.getPersonalBoard().getResources().hashMapToArrayListResources())
				resCon.add(new ResourceDTO(res.getType(), res.getAmount()));
			info.resSituation.put(player.getName(), resCon);
		}
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(info);
	}
	
	
	/**
	 * Dispatcher of messages from views. For every type of message triggers corresponding action handler. 
	 * @author Pietro Melzi
	 * @author Pietro Grotti
	 * @author Giovanni Mele
	 *
	 */
	public class VisitorMessages {
		
		public void visit (ActionChoice msg) {
			handleInputAction(msg, -1);
		}
		
		public void visit (Exchange msg) {
			handleExchangeAction(msg);
		}
		
		public void visit(BonusChoice msg){
			handleInputAction(handleBonusAction (msg), msg.getPower());
		}
		
		public void visit(VaticanChoice msg){
			handleExcommunication(msg);
		}
		
		public void visit(PrivilegeChoice msg){
			handlePrivilegesChoice(msg);
		}

		//called after disconnection of a player
		public void visit(PlayerInfoMessage msg) {
			handlePlayerInfoMessage(msg);
		}
		
		//called after a player came back in game
		public void visit(RestoreSituation msg) {
			restoreSituation(msg);
		}
	}
	
	
	
}

