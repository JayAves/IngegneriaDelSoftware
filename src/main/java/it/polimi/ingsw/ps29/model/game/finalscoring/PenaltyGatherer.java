package it.polimi.ingsw.ps29.model.game.finalscoring;

/**object used to remove victory points at the end of the game according to the amount
 * of a specific resource
 * 
 * @author Giovanni Mele
 *
 */

public abstract class PenaltyGatherer implements VictoryPointsGatherer{
	
	public abstract void setPenalty(int interval);

}
