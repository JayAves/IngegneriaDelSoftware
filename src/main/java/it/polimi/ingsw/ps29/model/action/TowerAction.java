package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class TowerAction implements Action {

	private Move move; 
	private TowerArea space;
	
	public TowerAction(Move move, TowerArea space) {
		
		this.move = move;
		this.space = space;
		
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() {
		// TODO Auto-generated method stub
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getTowerPower());

	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
		/*non considero l'effetto che blocca il bonus da torri...lo implementeremo in seguito*/
		
		GainResourcesEffect effect= new GainResourcesEffect (space.takeResource()); //leggo risorse dal oiano, se non ne ha aggiungo null
		
		effect.performEffect(move.getPlayer()); //aggiungo le risorse al player, dobbiamo gestire il caso in cui non ci siano risorse
		
		move.getPlayer().getPersonalBoard().addCard(space.takeCard());
		
		for(Effect immediateEffect : space.takeCard().getImmediateEffects()) {
			
			immediateEffect.performEffect(move.getPlayer());
		}
		
		space.getPlacementFloor().setCard(null); //il piano si svota della carta
		
		//ha senso che aggiunga l'effetto permanente della carta appena pescata dal giocatore all'attributo della classe BonusAndMalusPlayer?
	}
	

}
