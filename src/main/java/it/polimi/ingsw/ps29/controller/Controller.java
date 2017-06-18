package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.AddPrivileges;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.action.ExchangeResources;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.MarketAction;
import it.polimi.ingsw.ps29.model.action.NoAction;
import it.polimi.ingsw.ps29.model.action.ProductionAction;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.StateOfActionIdentifier;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstabilishState;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;
import it.polimi.ingsw.ps29.model.game.roundstates.EndOfTheRoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.VaticanReportState;
import it.polimi.ingsw.ps29.model.space.Floor;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import it.polimi.ingsw.ps29.server.ClientThread;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InfoForView;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.TowersForView;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

public class Controller implements Observer{
	
	private Match model;
	private Map<String, ClientThread> views;
	private RoundState roundState;
	private ActionState stateOfAction; 
	private InfoForView info;
	private boolean sendInfo;
	//il booleano è settato a false all'inizio di ogni gestione dell'input utente
	//se ci sarà qualcosa da notificare viene settato a true
	
	public Controller (Match model) {
		this.model = model;
		views =  new HashMap <String, ClientThread> ();
		roundState = new RoundSetupState();
		stateOfAction = new ToEstabilishState();
	}
	
	public void addView (String playerName, ClientThread view) {
		if(!views.containsKey(playerName))
			views.put(playerName, view);
	}
	
	
	public void callCorrectView () {
		String playerName = model.getBoard().getCurrentPlayer().getName();
		ClientThread view = views.get(playerName);
		//modifico lo stato appena prima di interagire con la view, così da poter fare la giusta richiesta
		stateOfAction = stateOfAction.beforeAction();
		//costruisco l'oggetto da utilizzare nell'interazione con l'utente
		InteractionMessage object = stateOfAction.objectForView(playerName);
		view.startInteraction (object);
		//
		
	}
	

	@Override
	public void update(Observable o, Object arg) {
		//se arriva dalla view può riguardare azione standard, azione bonus, scelta exchange, scelta scomunica
		VisitorMessages visitor = new VisitorMessages();
		if(o instanceof Match){
			//da capire se serve dopo il cambiamento nel flusso del gioco
		}
		else if (o instanceof ClientThread) {
			//eseguo l'azione scelta dall'utente
			sendInfo = false;
			info = new InfoForView(model.getBoard().getCurrentPlayer().getName());
			info.playerColor = model.getBoard().getCurrentPlayer().getColor();
			((InteractionMessage)arg).visit(visitor);
			if(sendInfo) {
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
				
			gameEngine();
		}
		else 
			throw new IllegalArgumentException();
	}
	
	
	public void handleInputAction (ActionChoice arg) {
		
		Action action;
		ChoiceToMove adapter = new ChoiceToMove(model.getBoard());
		Move move= adapter.createMove(arg);
		
		switch (arg.getChoice(0))	{
		
		case 1:
			action= new HarvestAction(model, move);
			break;
		
		case 2:
			action= new ProductionAction(model, move);
			break;
		
		case 3:
		case 4:
		case 5:
		case 6:
			action= new TowerAction(model, move);
			break;
		
		case 7:
		case 8:
		case 9:
		case 10:
			action= new MarketAction(model, move);
			break;
		
		case 11:
			action= new CouncilPalaceAction(model, move);
			break;
		
		default:
			action= new NoAction(model,move);
			break;
		}
		
		stateOfAction = action.actionHandler();
		//recupero lo stato dopo che ho eseguito le istruzioni
		
		if(stateOfAction.getState().equals(StateOfActionIdentifier.PERFORMED.getName())||stateOfAction.getState().equals(StateOfActionIdentifier.ASK_EXCHANGE.getName())
				||stateOfAction.getState().equals(StateOfActionIdentifier.BONUS_ACTION.getName())||stateOfAction.getState().equals(StateOfActionIdentifier.PRIVILEGES.getName())) {
			//se ho piazzato aggiorno la board da mostrare all'utente con le informazioni relative al nuovo piazzamento
			//e al nuovo stato delle risorse (eventuali carte sono aggiunte appena vengono prelevate)
			info.space = arg.getChoice(0);
			info.floor = arg.getChoice(1);
			info.familiar = arg.getChoice(3);
			sendInfo = true;
		}
		//else viewsnotifyRejection();
	}
	
	private ActionChoice handleBonusAction (BonusChoice msg) {
		ActionChoice choice = new ActionChoice (msg.getName());
		switch(msg.getBonus().getType()) {
			case "harvest":
				choice.setChoice(0, 1);
				break;
			case "production":
				choice.setChoice(0, 2);
				break;
			case "territory":
				choice.setChoice(0, 3);
				break;
			case "building":
				choice.setChoice(0, 4);
				break;
			case "character":
				choice.setChoice(0, 5);
				break;
			case "venture":
				choice.setChoice(0, 6);
				break;
			default:
				choice.setChoice(0, 12);
				break;
		}
		
		//nel caso si tratta di un piazzamento sulla torre setto il piano scelto
		if(msg.getFloor()>0)
			choice.setChoice(1, msg.getFloor());
		
		choice.setChoice(2, msg.getServants());
		choice.setChoice(3,  -1);
		
		return choice;
	}
	
	private void handleExchangeAction (Exchange msg) {
		ExchangeResources res = new ExchangeResources(model, stateOfAction);
		stateOfAction = res.exchangeHandler(msg);
		if(stateOfAction.getState().equals(StateOfActionIdentifier.PERFORMED.getName())) 
			sendInfo = true;
		
	}
	
	private void handlePrivilegesChoice (PrivilegeChoice msg) {
		AddPrivileges addPrivileges = new AddPrivileges();
		addPrivileges.handlePrivileges(model.getBoard().getCurrentPlayer(), msg.getChoices());
		stateOfAction = stateOfAction.afterAction(model); //ottengo lo stato precedente
		stateOfAction = stateOfAction.afterAction(model); //eseguo il comando che non ho potuto eseguire nell'interazione precedente
		sendInfo = true;
	}
	
	private  void handleExcommunication (VaticanChoice msg) {
		model.getBoard().getPlayerByName(msg.getName()).setVaticanReportPerformed(true);
		//function to define
		System.out.println("...funzione vaticano...");
	}
	
	
	
	public class VisitorMessages {
		
		public void visit (ActionChoice msg) {
			handleInputAction(msg);
		}
		
		public void visit (Exchange msg) {
			handleExchangeAction(msg);
		}
		
		public void visit(BonusChoice msg){
			handleInputAction(handleBonusAction (msg));
		}
		
		public void visit(VaticanChoice msg){
			handleExcommunication(msg);
		}
		
		public void visit(PrivilegeChoice msg){
			handlePrivilegesChoice(msg);
		}

		public void visit(PlayerInfoMessage playerInfoMessage) {
			// TODO Auto-generated method stub
			//se è scattato un timeout, notifico players che uno di questi è disconnesso
		}
		
		
	}
	
	public void gameEngine () {
		//utilizzo questo oggetto all'inizio di ogni round per mostrare le torri alla view
		TowersDTO towersForView;
		
		if (roundState.getStateNumber()==1 || roundState.getStateNumber()==4) { 
			roundState = roundState.doAction(model.getRound(), model); //mi porto nello stato 2
			
			//mostro le torri alle view
			towersForView = createTowersDTO();
			for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
				view.getValue().startInteraction(new TowersForView(view.getValue().getClientName(), towersForView));
			
			callCorrectView(); //svolgo action
		}
		
		else {
			if (roundState.getStateNumber()==2 && isStateTwoTerminated()) {
				System.out.println("terminato stato 2");
				//ho concluso il turno di gioco: inizio la fase di VaticanReport
				roundState = new VaticanReportState();
				System.out.println("++"+roundState);
				askForExcommunication();
			}
			
			else if (roundState.getStateNumber()==3 && isStateThreeTerminated()) {
				System.out.println("terminato stato 3");
				//ho concluso la fase di VaticanReport
				endVaticanState();
			}
			
			else if (roundState.getStateNumber()==2)
				//sono ancora nella fase 2: chiedo un'azione
				callCorrectView();
			else
				//sono nell'azione 3: chiedo per il supporto alla Chiesa
				askForExcommunication();
		}
			
	}

	private boolean isStateTwoTerminated() {
		for(Player player: model.getBoard().getPlayers())
			for(FamilyMember member: player.getFamily())
				if (!member.getBusy())
					return false;
		
		//ha controllato che tutti i familiari sono occupati, se anche un eventuale azione
		//che richiede interazione è terminata torna true
		return (stateOfAction.getState().equals("performed"));
	}

	private boolean isStateThreeTerminated() {
		for (Player player: model.getBoard().getPlayers())
			if (!player.isVaticanReportPerformed())
				return false;
		return true;
	}
	
	private void conclusion () {
		//funzione per la terminazione del gioco: gestione punteggi, connessioni, notifiche alle view...
		System.out.println("...end of match..");
	}
	
	private void askForExcommunication () {
		String player;
		
		if(model.getRound()%2==0) { //funzioni da svolgere solo nei turni pari
			
			while(!model.getBoard().getCurrentPlayer().canAskSubstain(0) && !isStateThreeTerminated()) {
				//se non ho abbastanza punti per sostenere la Chiesa e non ho ancora controllato tutti
				model.getBoard().getCurrentPlayer().setVaticanReportPerformed(true);
				model.getBoard().changePlayerOrder();
			}
		
			if(!isStateThreeTerminated()) {
				//devo chiedere la scelta a un giocatore
				player = model.getBoard().getCurrentPlayer().getName();
				VaticanChoice msg = new VaticanChoice (player);
				views.get(player).startInteraction(msg);
			}
		
			else {
				//devo terminare il turno
				endVaticanState ();
			}	
		}
		
		else
			endVaticanState();
	}
	
	public void endVaticanState () {
		TowersDTO towersForView;
		//chiamo questa funzione quando non devo fare il Vatican Report
		//oppure mi accorgo di averlo concluso senza aver svolto un'azione per la gameEngine 
		roundState = new EndOfTheRoundState();
		roundState = roundState.doAction(model.getRound(), model); //dopo aver cambiato lo stato, svolgo azione
		
		//mostro le torri alle view
		towersForView = createTowersDTO();
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(new TowersForView(view.getValue().getClientName(), towersForView));
		
		if(model.endOfMatch) //se la partita termina esco, altrimenti devo richiamare una funzione per proseguire
			conclusion();
		else
			callCorrectView(); //inizia una nuova fase per le azioni
	}
	
	public TowersDTO createTowersDTO () {
		TowersDTO msg = new TowersDTO();
		String [] towersName = {"territoryTower", "buildingTower", "characterTower", "ventureTower"};
		for (String towerName: towersName)
			for (Floor floor: ((TowerArea)model.getBoard().getSpace(towerName)).getFloors()) {
				Card cardOnTower = floor.getCard();
				msg.addCard(new CardDTO (cardOnTower.getId(), cardOnTower.getType(), cardOnTower.toString()));
			}
		return msg;
	}
}
