package it.polimi.ingsw.ps29.model.game.familymember;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;

/**
 * 
 * @author Giovanni Mele
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public abstract class FamilyMemberDecorator implements FamilyMemberInterface{
	
	protected FamilyMemberInterface decoratedFamilyMember;
	
    public FamilyMemberDecorator(FamilyMemberInterface decoratedFamilyMember) {
		super();
		this.decoratedFamilyMember = decoratedFamilyMember;
	}

	public int getPower() {
		return decoratedFamilyMember.getPower();
	}

	@Override
	public int getHarvestPower() {
		return decoratedFamilyMember.getHarvestPower();
	}

	@Override
	public int getProductionPower() {
		return decoratedFamilyMember.getProductionPower();
	}

	@Override
	public int getTowerPower() {
		return decoratedFamilyMember.getTowerPower();
	}

	@Override
	public int getMarketPower() {
		return decoratedFamilyMember.getMarketPower();
	}

	@Override
	public int getCouncilPower() {
		return decoratedFamilyMember.getCouncilPower();
	}
	
	@Override
	public DiceColor getFamiliarColor(){
		return decoratedFamilyMember.getFamiliarColor();
	}
	
	@Override
	public void setPower (int power){
		decoratedFamilyMember.setPower(power);
	}
	
	@Override
	public void setBusy ( boolean busy){
		decoratedFamilyMember.setBusy(busy);
	}
	
	@Override
	public void setFixedPower(){
		decoratedFamilyMember.setFixedPower();
	}

	@Override
	public boolean getIfFixed(){
		return decoratedFamilyMember.getIfFixed();
	}
	
	@Override
	public boolean getBusy(){
		return decoratedFamilyMember.getBusy();
	}
	
	@Override
	public Color getPlayerColor(){
		return decoratedFamilyMember.getPlayerColor();
	}
	
}
