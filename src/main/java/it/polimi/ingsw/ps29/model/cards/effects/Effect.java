package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;


/**
 * Any effect that comes from any Card, Excommunication Card, LeaderCard.
 * 
 * <h2>	CARD EFFECTS:
 *		<ol>
*			<li>- GainResources (ext EffectAboutResources)
*			<li>- MultipliedResources
*			<li>- ExchangeResources 
*			<li>- BonusAction 
*				<li>- BonusActivity
*				<li>- BonusPlacement
*			<li>- EmpowermentAction 
*				<li>- EmpowermentActivity
*				<li>- EmpowermentPlacement
*			<li>-ExchangeResourcesEffect
*		</ol>
*	<h2>EXCOMMUNCATIONS:
*		<ol>
*			<li>- MalusResources (ext EffectAboutResources)
*			<li>- (Dis)Empowerment  ---> USED EmpowermentAction
*			<li>- Familiar(Dis)EmpowermentByColor 
*			<li>- AdHocEffect
*			<li>- TerritoryCardsPenalty
*			<li>- CharacterCardsPenalty
*			<li>- BuildingCardsPenaltyEffect
*			<li>- VictoryPointsPenaltyEffect
*			<li>- ResourcePenaltyEffect
*			<li>- MilitaryPointsPenaltyEffect
*			<li>- VentureCardsPenaltyEffect
*			<li>- AddGatherer
*			<li>- RemoveGatherer
*
*		</ol>
*	<h2>LEADERS:
*		<ol>
*			<li>- AdHocEffect
*			<li>- GainResourcesEffect
*			<li>- BonusActionEffect
*			<li>- SantaRitaEffect
*			<li>- Familiar EmpowermentByColor
*		</ol>
*	<p>See more in wiki
*		
* 
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
	
	

}
