package it.polimi.ingsw.ps29;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polimi.ingsw.ps29.model.cards.Deck;
import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ BonusActionTest.class, PrivilegesTest.class, BonusSpaceTest.class, DiscountEffectTest.class, 
	CheckVectorExchangeTest.class, CleanTowerTest.class, CouncilPalaceTest.class, FamiliarEmpowermentTest.class,
	FamilyMemberTest.class, LeaderCardsTest.class, MalusResourcesTest.class, MilitaryTrackTest.class,
	PerformExchangeTest.class, ProvaTest.class, ResourceExcommunicationTest.class, ResourcesTest.class, SatisfyRequirementsTest.class,
	TowerActionTest.class,VictoryGathererTest.class})

public class AllTests{}