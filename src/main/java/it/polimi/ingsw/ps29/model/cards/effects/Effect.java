package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;


/**
 * Any effect that comes from any Card, Excommuncation Card, LeaderCard
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public abstract class Effect {
	
	public abstract void performEffect (Player player);

	@Override
	public String toString() {
		return " ";
	}
	
	/*
	CARD EFFECTS:
		- GainResources (ext EffectAboutResources)
		- MultipliedResources
		- ExchangeResources 
		- BonusAction 
			- BonusActivity
			- BonusPlacement
		- EmpowermentAction 
			- EmpowermentActivity
			- EmpowermentPlacement
		-ExchangeResourcesEffect
			
	EXCOMMUNCATIONS:
		- MalusResources (ext EffectAboutResources)
		- DisempowermentAction ---> USED EmpowermentAction
		
		- GeneralExcommunications
		(1.piazzamento proibito nel mercato
		 2.perdita del primo turno
		 3.costo doppio servitori)
		- NoVictoryPoint
		- LoseVictoryPoint (ext EffectAboutResources)
	
	LEADERS:
		
		
		(implementare anche la scomunica che abbassa di uno il valore dei familiari colorati)
	*/

}
