package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.Exchange;

public interface InputOutput {
	
	abstract void showUpdatedSituation (String playerName, InfoDTO board);
	
	abstract void showMessage (String message);
	
	abstract int[] askTypeOfAction ();
	
	abstract int askNumberOfServants ();
	
	abstract int askFamiliarColor ();

	abstract Exchange askExchange(Exchange msg);
	
	abstract int askFloor ();
	
	abstract void printBonusAction (BonusActionEffect effect);

	abstract ResourceType askSpecificPrivilege();

}
