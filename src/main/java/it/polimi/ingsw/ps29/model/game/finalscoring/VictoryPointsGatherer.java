package it.polimi.ingsw.ps29.model.game.finalscoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;

/**generic interfece used to gather victory points at the end of the game according to the amount of a 
 * specific resource
 * 
 * <h2> PENALTY GATHERERS
 * 		<ol>
 * 			<li>- Penalty Gatherer ( impl VictoryPointsgatherer
*				<li>- BuildingCostsPenaltyPointsGatherer
*				<li>- MilitaryPenaltyPointsGatherer
*				<li>- ResourcePenaltyPintsGatherer
*				<li>- VictoryPenaltyPointsGatherer
*		</ol>
* <h2> POINTS GATHERER
* 	<ol>
 * 				<li>- VictoryPointsGatherer
*					<li>- CharacterCardVictoryPointsGatherer
*					<li>- TerritoryCardVictoryPointsGatherer
*					<li>- ResourcesVictoryPointsGatherer
*			</ol>
*
 * 
 * @author Giovanni Mele
 *
 */

public interface VictoryPointsGatherer {
	
	public void getVictoryPoints(PersonalBoard board);

}
