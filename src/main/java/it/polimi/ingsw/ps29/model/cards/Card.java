package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Development Card
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public abstract class Card {
	
	private String name;
	private Period period;
	private CardType type;
	private final int id;
	private ArrayList<Effect> immediateEffects;
	private ArrayList<Effect> permanentEffects;
	private ArrayList<Resource> cost;
	
	public Card(String name, Period period, String type, int id, ArrayList<Effect> immediate, ArrayList<Effect> permanent, ArrayList<Resource> cost) {
		
		super();
		this.name = name;
		this.period = period;
		this.id = id;
		this.type = CardType.parseInput(type);
		this.immediateEffects = immediate;
		this.permanentEffects = permanent;
		this.cost= cost;
	}

	public Period getPeriod() {
		return this.period;
	}
	
	public ArrayList<Effect> getImmediateEffects() {
		return immediateEffects;
	}

	public void setImmediateEffects(ArrayList<Effect> immediateEffects) {
		this.immediateEffects = immediateEffects;
	}

	public ArrayList<Effect> getPermanentEffects() {
		return permanentEffects;
	}

	public void setPermanentEffects(ArrayList<Effect> permanentEffects) {
		this.permanentEffects = permanentEffects;
	}

	public String getType() {
		return type.getType();
	}
	
	public ArrayList<Resource> getCost() {
		ArrayList <Resource> copy = new ArrayList<Resource>();
		for(Resource res: cost)
			copy.add(new Resource(res.getType(), res.getAmount()));
		return copy;
	}
	
	public String getName() {
		return name;
	}

/*	@Override
	public String toString() {
		String msg = "Card name:" + name + ", period:" + period + ", type:" + type +"immediate: ";
		for(Effect eff: immediateEffects)
			msg+=eff.toString();
		msg+=", permanent:";
		for(Effect eff: permanentEffects)
			msg+=eff.toString();
		msg+=", cost: ";
		for(Resource res: cost)
			msg+=res.toString()+"";
		return msg;
	}
*/

	@Override
	public String toString() {
		return  name.toUpperCase() + " (" + period +" PERIOD)"+ ", immediateEffects:" + immediateEffects
				+ ", permanentEffects:" + permanentEffects + ", cost:" + cost;
	}

	public int getId() {
		return id;
	}

}
