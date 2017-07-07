package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;


import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.finalScoring.FinalScoring;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.VictoryPoints;

/**
 * Contains all player info:
 * 	<ul>
 * 		<li>{@link Color}
 * 		<li>{@link FamilyMember} 
 * 		<li>{@link PersonalBoard} e {@link PersonalBonusTile}
 * 		<li>{@link ExcommunicationCard} if excommunicated
 * 		<li>{@link Effect}s from Leaders and Cards
 * 		<li> {@link FinalScoring}
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class Player {
	private String name;
	private PlayerColor color;
	private PersonalBoard board;
	private FamilyMemberInterface[] family; 
	private FakeFamilyMemberInterface fakeFamiliar;
	private ExcommunicationCard [] excommunication;
	private ExchangeSupport supportForExchange;
	private ArrayList<Effect> specialPermanentEffects;
	private FinalScoring finalScoring;
	private boolean ventureCardsPenaltyOn;
	private boolean vaticanReportPerformed;
	private boolean ludovicoAriosto;
	private boolean filippoBrunelleschi;
	private boolean sistoIV;
	private boolean picoDellaMirandola;
	private boolean noMarket;
	
	public Player (String name, PlayerColor color, PersonalBonusTile pbt) {
		this.name = name;
		this.color = color;
		this.board = new PersonalBoard(pbt);
		initFamily();
		finalScoring = new FinalScoring();
		supportForExchange = new ExchangeSupport(new ArrayList<ExchangeResourcesEffect>(), new Container());
		excommunication = new ExcommunicationCard [3];
		specialPermanentEffects= new ArrayList<Effect>();
		ventureCardsPenaltyOn = false;
		setVaticanReportPerformed(false);
		ludovicoAriosto = false;
		filippoBrunelleschi = false;
		sistoIV = false;
		picoDellaMirandola = false;
	}
	
	
	public void initFamily () {
		family = new FamilyMemberInterface [4];
		family[0] = new FamilyMember (DiceColor.BLACK, color); 
		family[1] = new FamilyMember(DiceColor.ORANGE, color);
		family[2] = new FamilyMember(DiceColor.WHITE, color);
		family[3] = new FamilyMember(DiceColor.NEUTRAL, color);
		fakeFamiliar = new FakeFamilyMember();
		
	}
	
	public String getName() {
		return name;
	}
	
	
	public FamilyMemberInterface getFamiliarByColor (DiceColor color) {
		for (FamilyMemberInterface member: family) 
			if(member.getFamiliarColor().equals(color))
				return member;
		
		return null;
	}
	
	public PersonalBoard getPersonalBoard() {
		return board;
	}

	public FakeFamilyMemberInterface getFakeFamiliar() {
		return fakeFamiliar;
	}

	public void setFakeFamiliar(FakeFamilyMemberInterface fakeFamiliar) {
		this.fakeFamiliar = fakeFamiliar;
	}

	public PlayerColor getColor() {
		return color;
	}
	
	public FamilyMemberInterface[] getFamily () {
		return family;
	}
	
	public void setPersonalBonusTile (PersonalBonusTile pbt) {
		board.setTile(pbt);
	}

	public ExchangeSupport getSupport() {
		return supportForExchange;
	}

	public void setSupport(ExchangeSupport support) {
		this.supportForExchange = support;
	}
	
	public void passPersonalBoard(){
		finalScoring.setPersonalBoard(board);
	}
	
	public void getFinalPoints(){
		finalScoring.calculateFinalScore();
	}
	
	public boolean getVentureCardPenalty(){
		return ventureCardsPenaltyOn;
	}
	
	public FinalScoring getFinalScoring(){
		return finalScoring;
	}

	public boolean isVaticanReportPerformed() {
		return vaticanReportPerformed;
	}

	public void setVaticanReportPerformed(boolean vaticanReportPerformed) {
		this.vaticanReportPerformed = vaticanReportPerformed;
	}
	
	public boolean canAskSubstain (int faithNeeded) {
		return this.getPersonalBoard().getSpecificResource("faith").getAmount() >= faithNeeded &&
				!vaticanReportPerformed ;
	}
	
	public void setLudovicoAriosto(){
		ludovicoAriosto = true;
	}
	
	public boolean getLudovicoAriosto(){
		return ludovicoAriosto;
	}
	
	public void setFilippoBrunelleschi(){
		filippoBrunelleschi = true;
	}
	
	public boolean getBrunelleschi(){
		return filippoBrunelleschi;
	}
	
	public void setSistoIV(){
		sistoIV = true;
	}
	
	public void addSistoIVBonus(){
		if (sistoIV == true){
			this.getPersonalBoard().getResources().updateResource(new VictoryPoints(5));
		}
	}
	
	public void setPicoDellaMirandola(){
		picoDellaMirandola = true;
	}
	
	public void setNoMarket(){
		noMarket = true;
	}
	
    public boolean getNoMarket(){
        return noMarket;
	}


	public ArrayList<Effect> getSpecialPermanentEffects() {
		return specialPermanentEffects;
	}


	public void addSpecialPermanentEffects(Effect specialPermanentEffect) {
		this.specialPermanentEffects.add(specialPermanentEffect);
	}
	
	public void setSpecialPermanentEffects(ArrayList<Effect> specialPermanentEffect) {
		this.specialPermanentEffects = specialPermanentEffect;
	}

    
    
}
