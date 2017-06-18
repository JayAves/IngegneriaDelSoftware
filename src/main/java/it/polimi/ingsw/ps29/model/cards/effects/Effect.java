package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public abstract class Effect {
	
	public abstract void performEffect (Player player);

	@Override
	public String toString() {
		return " ";
	}
	
	/*
	EFFECTS:
		- GainResources (ext EffectAboutResources)
		- MultipliedResources
		- ExchangeResources (manca interazione con l'utente)
		- BonusAction (manca interazione con l'utente)
			- BonusActivity
			- BonusPlacement
		- EmpowermentAction (manca discount resources)
			- EmpowermentActivity
			- EmpowermentPlacement
			
	EXCOMMUNCATIONS:
		- MalusResources (ext EffectAboutResources)
		- DisempowermentAction ---> utilizzo EmpowermentAction
		(implementare anche la scomunica che abbassa di uno il valore dei familiari colorati)
		- GeneralExcommunications
		(1.piazzamento proibito nel mercato
		 2.perdita del primo turno
		 3.costo doppio servitori)
		- NoVictoryPoint
		- LoseVictoryPoint (ext EffectAboutResources)
		
		
	*/

}
