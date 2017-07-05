package it.polimi.ingsw.ps29.model.cards.effects;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * CARD EFFECT: Allows to do a Resources Exchange
 * @author Pietro Melzi
 *
 */
public class ExchangeResourcesEffect extends Effect implements Cloneable, Serializable {

	//auto-generated serialVersionUID
	private static final long serialVersionUID = -7432793364767288256L;
	private ArrayList<ExchangeResourceHandler> choices;
	
	public ExchangeResourcesEffect(ArrayList <ExchangeResourceHandler> erh) {
		choices = erh;
	}
	

	
	public ArrayList<ExchangeResourceHandler> getChoices () {
		return choices;
	}
	

	@Override
	public void performEffect(Player player) {
		//not necessary. 
		
		
	}

	
	/**
	 * Real performEffect. 
	 * @param player
	 * @param indexExchange explains Exchange type
	 * @param indexOut is what resource was chosen among the ones to pay
	 * @param indexIn is what resource was chosen among the ones to get
	 */
	public void exchangeResources (Player player, int indexExchange, int indexOut, int indexIn) {
		choices.get(indexExchange).performExchange(player.getPersonalBoard().getResources(), indexOut, indexIn);
	}
	
	@Override
	public ExchangeResourcesEffect clone () {
		ArrayList <ExchangeResourceHandler> array = new ArrayList<ExchangeResourceHandler> ();
		for(ExchangeResourceHandler handler: this.getChoices())
			array.add(handler.clone());
		return new ExchangeResourcesEffect(array);
	}
	
	@Override
	public String toString () {
		String msg = super.toString()+"Exchange:  ";
		for(ExchangeResourceHandler erh: choices)
			msg+=erh.toString()+"; ";
		return msg;
	}
}
