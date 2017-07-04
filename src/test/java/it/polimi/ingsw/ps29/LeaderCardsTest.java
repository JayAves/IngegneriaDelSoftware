package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.model.action.LeaderAction;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import junit.framework.TestCase;

public class LeaderCardsTest extends TestCase{
	
	Match model;
	int playedSize;
	ArrayList<LeaderCard> permanentLeaderCards;
	ArrayList<LeaderCard> temporaryLeaderCards;
	
	public LeaderCardsTest(String testName){
		
		super(testName);
	}
	
	@Before
	public void setUp () throws FileNotFoundException {
		
		ArrayList<ArrayList<Object>> leaderChoice = new ArrayList<ArrayList<Object>>();
		String player1 = "primo";
		String player2 = "secondo";
		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		
		model = new Match(names);
		
		temporaryLeaderCards = new ArrayList<LeaderCard>();
		permanentLeaderCards = new ArrayList<LeaderCard>();
		
		for (LeaderCard card : model.getBoard().getPlayerByName("primo").getPersonalBoard().getLeaderCards()){
			if (card.isPermanent())
				permanentLeaderCards.add(card);
			else
				temporaryLeaderCards.add(card);
		}
		
		//costruisco vettore dei leader e aggiungo "PLAY" a tutti
		leaderChoice = model.getBoard().getPlayerByName("primo").getPersonalBoard().buildLeaderChoice();
		
		for (int i = 0; i < leaderChoice.size(); i++)
			leaderChoice.get(i).add("PLAY");
		
		Move move = new Move(model.getBoard().getPlayerByName("primo"), "string", 0, 0, null);
		ActionChoice aChoice = new ActionChoice(model.getBoard().getPlayerByName("primo").getName());
		
		//metto il vettore dei leader in actionChoice
		aChoice.setLeaderSituation(leaderChoice);
		
		LeaderAction action = new LeaderAction(model, move, aChoice);	
		action.actionHandler();
		
		playedSize = model.getBoard().getPlayerByName("primo").getPersonalBoard().getPlayedLeaderCards().size();
		
		//faccio una seconda azione in cui attivo le carte
		leaderChoice = model.getBoard().getPlayerByName("primo").getPersonalBoard().buildLeaderChoice();
		
		for (int i = 0; i < leaderChoice.size(); i++)
			leaderChoice.get(i).add("ACTIVATE");
		
		aChoice.setLeaderSituation(leaderChoice);
		
		LeaderAction action2 = new LeaderAction(model, move, aChoice);	
		action2.actionHandler();
		
		/*doAction reinserisce le carte che hanno effetti validi per
		 * un solo turno nell 'arrayList playedleaderCards permettendo
		 * di attivarle di nuovo al turno successivo
		 */
		RoundSetupState setUp = new RoundSetupState();
		setUp.doAction(2, model);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(4, playedSize);
		/*testo che le carte con temporary effect siano state rimesse nel vettore playedLeaderCards
		 * e che le carte con permanent effects siano ancora in ActivatedLeaderCards
		 */
		assertEquals(true, model.getBoard().getPlayerByName("primo").getPersonalBoard().getPlayedLeaderCards().containsAll(temporaryLeaderCards));
		assertEquals(true, model.getBoard().getPlayerByName("primo").getPersonalBoard().getActivatedLeaderCards().containsAll(permanentLeaderCards));
		
		assertEquals(false, model.getBoard().getPlayerByName("primo").getPersonalBoard().getActivatedLeaderCards().containsAll(temporaryLeaderCards));
        //assertEquals(false, model.getBoard().getPlayerByName("primo").getPersonalBoard().getPlayedLeaderCards().containsAll(permanentLeaderCards));
	}

}
