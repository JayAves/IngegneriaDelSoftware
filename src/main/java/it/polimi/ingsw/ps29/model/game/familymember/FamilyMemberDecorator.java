package it.polimi.ingsw.ps29.model.game.familymember;

public abstract class FamilyMemberDecorator implements FamilyMemberInterface{
	
	protected FamilyMemberInterface decoratedFamilyMember;
	
    public FamilyMemberDecorator(FamilyMemberInterface decoratedFamilyMember) {
		super();
		this.decoratedFamilyMember = decoratedFamilyMember;
	}
    
  //@Override
  //public int setPower(){
  //    qui fa una roba del tipo decoratedFamilyMember.setPower();        

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
	
	

}
