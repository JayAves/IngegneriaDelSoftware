package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.AddPrivileges;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.action.ExchangeResources;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.LeaderAction;
import it.polimi.ingsw.ps29.model.action.MarketAction;
import it.polimi.ingsw.ps29.model.action.NoAction;
import it.polimi.ingsw.ps29.model.action.ProductionAction;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.action.actionstates.RejectedState;
import it.polimi.ingsw.ps29.model.action.actionstates.StateOfActionIdentifier;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstablishState;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;
import it.polimi.ingsw.ps29.model.game.roundstates.EndOfTheRoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.VaticanReportState;
import it.polimi.ingsw.ps29.model.space.Floor;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import it.polimi.ingsw.ps29.server.ClientThread;

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
	
	
	public void callCorrectView () {
		
		ArrayList<ArrayList<Object>> leaderSituation;
		leaderSituation = new ArrayList<ArrayList<Object>>();
		if(PlayersConnected()) {
			
			String playerName = model.getCurrentPlayer().getName();
			//leaderSituation = model.getBoard().getCurrentPlayer().getPersonalBoard().buildLeaderChoice();
			ClientThread view = views.get(playerName);
 			
			
			//modifico lo stato appena prima di interagire con la view, così da poter fare la giusta richiesta
			stateOfAction = stateOfAction.beforeAction();

			if  (view.getInGame()) {
				//costruisco l'oggetto da utilizzare nell'interazione con l'utente//
				InteractionMessage object = stateOfAction.objectForView(playerName);
				
				if(stateOfAction.getState().equals(StateOfActionIdentifier.TO_ESTABLISH.getName())) {
					leaderSituation = model.getBoard().getPlayerByName(playerName).getPersonalBoard().buildLeaderChoice();
					//l'oggetto generato è di tipo ActionChoice se entro in questo if//
					((ActionChoice)object).setLeaderSituation(leaderSituation);
				
				}
				view.startInteraction (object);
			}
			
			else {
				sendInfo=false;
				info = new InfoForView(model.getCurrentPlayer().getName());
				info.familiar=placeRandomFamiliar();
				info.space=12;
				if (info.familiar!=-1)	
					sendInfo=true;
				if(sendInfo) {
					info.resSituation = new HashMap<String, ArrayList<ResourceDTO>>();
					for(Player player: model.getBoard().getPlayers()) {
						ArrayList<ResourceDTO> resCon = new ArrayList<ResourceDTO>();
						for(ResourceInterface res: player.getPersonalBoard().getResources().hashMapToArrayListResources())
							resCon.add(new ResourceDTO(res.getType(), res.getAmount()));
						info.resSituation.put(player.getName(), resCon);
					}
					for(HashMap.Entry <String, ClientThread> viewz: views.entrySet()) 
						viewz.getValue().startInteraction(info);
				}
				model.getBoard().changePlayerOrder();
				gameEngine();
			}
		}
		
		else {
			System.out.println("All players are disconnected!");
			//setto lo stato della partita a fine
			
		}
	}
	

	private boolean PlayersConnected() {
		
		for(Map.Entry<String, ClientThread> entry: views.entrySet()) {
			
			if (entry.getValue().getInGame())
				return true;
		}
		return false;
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
			info = new InfoForView(model.getCurrentPlayer().getName());
			info.playerColor = model.getCurrentPlayer().getColor();
			((InteractionMessage)arg).visit(visitor);
			
			//se l'azione ha modificato lo stato della partita mostro le info ai players
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
	
	
	public void handleInputAction (ActionChoice arg, int power) {
		
		Action action;
		ChoiceToMove adapter = new ChoiceToMove(model.getBoard());
		Move move= adapter.createMove(arg);
		
		if(power>-1) //si tratta di un'azione bonus
			move.getFamiliar().setPower(power);
		
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
			
		case 13 :
			action = new LeaderAction(model, move, arg);
			System.out.println("\n Sto facendo una " + action.toString());
			break;
		
		default:
			action= new NoAction(model,move);
			break;
		}
		
		try{
			stateOfAction = action.actionHandler();
			System.out.println(" sono dopo actionHandler" + stateOfAction + "da azione " + action.toString());
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
		
		} catch (RejectException exception) {
			views.get(model.getCurrentPlayer().getName()).startInteraction(new RejectMessage(
					model.getCurrentPlayer().getName(), exception));
		}
	}
	
	public ActionChoice handleBonusAction (BonusChoice msg) {
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
			case "all":
				switch (msg.getSpace()) {
				case 1:
					choice.setChoice(0, 3);
					break;
				case 2: 
					choice.setChoice(0, 4);
					break;
				case 3:
					choice.setChoice(0, 5);
					break;
				case 4:
					choice.setChoice(0, 6);
					break;
				case 5:
					choice.setChoice(0, 12);
					break;
				}
		}
		
		//non faccio azione bonus
		if (choice.getChoice(2)<0)
			choice.setChoice(0, 12);
		
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
		sendInfo = true;
		if(stateOfAction.getState().equals(StateOfActionIdentifier.PERFORMED.getName())) 
			if(model.getCurrentPlayer().getPersonalBoard().getSpecificResource("privilege").getAmount()>0) {
				stateOfAction = new PrivilegesState(stateOfAction, model.getCurrentPlayer().getPersonalBoard().getSpecificResource("privilege").getAmount());
				String name = model.getCurrentPlayer().getName();
				views.get(name).startInteraction(stateOfAction.objectForView(name));
				stateOfAction = stateOfAction.afterAction(model);
				
			}
				
	}
	
	private void handlePrivilegesChoice (PrivilegeChoice msg) {
		AddPrivileges addPrivileges = new AddPrivileges();
		System.out.println(msg.getChoices().get(0).getType());
		addPrivileges.handlePrivileges(model.getCurrentPlayer(), msg.getChoices());
		stateOfAction = stateOfAction.afterAction(model); //ottengo lo stato precedente
		stateOfAction = stateOfAction.afterAction(model); //eseguo il comando che non ho potuto eseguire nell'interazione precedente
		sendInfo = true;
	}
	
	private  void handleExcommunication (VaticanChoice msg) {
		model.getBoard().getPlayerByName(msg.getName()).setVaticanReportPerformed(true);
		//function to define
		System.out.println("...funzione vaticano...");
		sendInfo=true;
	}
	
	
	
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

		public void visit(PlayerInfoMessage playerInfoMessage) {
			// TODO Auto-generated method stub
			
				for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
					view.getValue().startInteraction(playerInfoMessage);
			
			if (playerInfoMessage.getName().contentEquals(model.getCurrentPlayer().getName())) {
				if ((stateOfAction instanceof RejectedState)||(stateOfAction instanceof ToEstablishState)) {
					info.familiar=placeRandomFamiliar();
					info.space=12;
				if (info.familiar!=-1)	
					sendInfo=true;
				}
					
				if (stateOfAction instanceof VaticanChoice) {
					VaticanChoice msg= new VaticanChoice(playerInfoMessage.getName());
					msg.setSustain(false);
					handleExcommunication(msg);
				}
					
				stateOfAction= new PerformedState();
				stateOfAction.afterAction(model);
			}
			
		}
	}
	
	private int placeRandomFamiliar() {
		
		if (!model.getCurrentPlayer().getFamiliarByColor(DiceColor.NEUTRAL).getBusy()) {
			model.getCurrentPlayer().getFamiliarByColor(DiceColor.NEUTRAL).setBusy(true);
			return 4;
		}
			
		else {
			
			ArrayList<FamilyMemberInterface> freeMembers= new ArrayList<FamilyMemberInterface>();
			
			for (DiceColor color: DiceColor.values()) {
				if (!model.getCurrentPlayer().getFamiliarByColor(color).getBusy())
					freeMembers.add(model.getCurrentPlayer().getFamiliarByColor(color));
			}	
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
	

	
	
	public void gameEngine () {
		
		for (Player player : model.getBoard().getPlayers()){
			System.out.println(" "+ player.getName() + " leaderCards");
			for (LeaderCard card : player.getPersonalBoard().getLeaderCards())
				System.out.println(" " + player.getName() + " " + card.toString());
		}
		
		System.out.println("\n");
		
		for (Player player  : model.getBoard().getPlayers()){
			System.out.println(" "+ player.getName() + " playedLeaderCards");
			for (LeaderCard card : player.getPersonalBoard().getPlayedLeaderCards())
				System.out.println(" " + player.getName() + " " + card.toString());
		}
		
		System.out.println("+++Round state: "+roundState+" +++ Player: "+model.getCurrentPlayer());

		
		if (roundState.getStateNumber()==1 || roundState.getStateNumber()==4) { 
			roundState = roundState.doAction(model.getRound(), model); //mi porto nello stato 2
			
			initGameMessagesForView();
			//initRoundMessagesForView(); //entra in questo ramo della funzione solo la prima volta
			
			callCorrectView(); //svolgo action
		}
		
		else {
			if (roundState.getStateNumber()==2)
				
				if(isStateTwoTerminated() && !(stateOfAction.getState().equals(StateOfActionIdentifier.BONUS_ACTION.getName())) ) {
					//ho concluso il turno di gioco: inizio la fase di VaticanReport
					roundState = new VaticanReportState();
					askForExcommunication();
				} else
					callCorrectView();
			
			else if (roundState.getStateNumber()==3)
				if(isStateThreeTerminated()) 
				//ho concluso la fase di VaticanReport
					endVaticanState();
			else
				//sono nell'azione 3: chiedo per il supporto alla Chiesa
				askForExcommunication();
		}
			
	}

	private boolean isStateTwoTerminated() {
		for(Player player: model.getBoard().getPlayers())
			for(FamilyMemberInterface member: player.getFamily())
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
			
			while(!model.getCurrentPlayer().canAskSubstain(0) && !isStateThreeTerminated()) {
				//se non ho abbastanza punti per sostenere la Chiesa e non ho ancora controllato tutti
				model.getCurrentPlayer().setVaticanReportPerformed(true);
				model.getBoard().changePlayerOrder();
			}
		
			if(!isStateThreeTerminated()) {
				//devo chiedere la scelta a un giocatore
				player = model.getCurrentPlayer().getName();
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
		//chiamo questa funzione quando non devo fare il Vatican Report
		//oppure mi accorgo di averlo concluso senza aver svolto un'azione per la gameEngine 
		roundState = new EndOfTheRoundState();
		roundState = roundState.doAction(model.getRound(), model); //dopo aver cambiato lo stato, svolgo azione
		
		if(model.endOfMatch) //se la partita termina esco, altrimenti devo richiamare una funzione per proseguire
			conclusion();
		
		else {
			initRoundMessagesForView();
			callCorrectView(); //inizia una nuova fase per le azioni
		}
		
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
	
	public int[] createDicesDTO () {
		int[] dices = new int[3];
		for (int i=0; i<model.getBoard().getDices().size(); i++)
			dices[i] = model.getBoard().getDices().get(i).getValue();
		return dices;
	}
	
	public void initRoundMessagesForView () {
		//mostro le torri alle view
		TowersDTO towersForView = createTowersDTO();
		int[] dices = createDicesDTO();
		
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(new TowersAndDicesForView(view.getValue().getClientName(), towersForView, dices));
	}
	
	public void initGameMessagesForView () {
		//costruisco l'oggetto per le tile
		HashMap <String, PersonalBonusTileDTO> tiles = new HashMap <String, PersonalBonusTileDTO> ();
		for (Player player: model.getBoard().getPlayers())
			tiles.put(player.getName(), new PersonalBonusTileDTO(
					player.getPersonalBoard().getPersonalBonusTile().getId(), 
					player.getPersonalBoard().getPersonalBonusTile().toString()));
		
		//costruisco l'oggetto per le scomuniche
		ArrayList<ExcommunicationCardDTO> exCards = new ArrayList<ExcommunicationCardDTO>();
		for (int i=0; i<3; i++) {
			ExcommunicationCard exCard = model.getBoard().getExcommunication(i+1);
			exCards.add(new ExcommunicationCardDTO(exCard.getId(), exCard.getPeriod(), exCard.toString()));
		}
		
		TowersDTO towersForView = createTowersDTO();
		int[] dices = createDicesDTO();
		
		//invio il messaggio alle view
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) {
			view.getValue().startInteraction(new FirstBoardInfo(view.getValue().getClientName(), tiles, exCards,
					new TowersAndDicesForView(view.getValue().getClientName(), towersForView, dices)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
			//views.get(model.getBoard().getCurrentPlayer().getName()).startInteraction(new FirstBoardInfo(model.getBoard().getCurrentPlayer().getName(), tiles, exCards,
				//	new TowersAndDicesForView(model.getBoard().getCurrentPlayer().getName(), towersForView, dices)));
		
		}
	
	}

