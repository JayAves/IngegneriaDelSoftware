package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.action.BonusAndMalusPlayer;

public abstract class Effect {
	
	protected abstract void performEffect(BonusAndMalusPlayer modifier);

}
