package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;

public abstract class Effect {
	
	abstract void performEffect (Player player);
	
	/*
	EFFECTS:
		- GainResources (ResourcesArray)
		- MultipliedResources
		- ExchangeResources
		- BonusAction
			- BonusActivity
			- BonusPlacement
		- EmpowermentAction
			- EmpowermentActivity
			- EmpowermentPlacement
			
	EXCOMMUNCATIONS:
		- MalusResources (ResourcesArray)
		- DisempowermentAction ---> utilizzo EmpowermentAction
		(attenzione a implementare anche la scomunica che abbassa di uno il valore dei familiari colorati)
		- GeneralExcommunications
		(1.piazzamento proibito nel mercato
		 2.perdita del primo turno
		 3.costo doppio servitori)
		- NoVictoryPoint
		- LoseVictoryPoint (ResourceArray)
		
		
	*/

}
