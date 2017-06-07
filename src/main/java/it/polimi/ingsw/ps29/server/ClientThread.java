package it.polimi.ingsw.ps29.server;

import java.util.Observable;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;

public abstract class ClientThread extends Observable implements Runnable{
	
	public abstract void stopClient();
	
	public abstract String getClientName();

	public abstract void askNextAction();
	
	public abstract void callCorrectView ();

	public abstract void askBonusAction(BonusActionEffect effect);

	public abstract void askAboutExchange(ExchangeResourcesEffect exchangeResourcesEffect);
	
	
	
}
