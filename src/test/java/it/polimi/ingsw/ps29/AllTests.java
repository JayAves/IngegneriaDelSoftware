package it.polimi.ingsw.ps29;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ BonusActionTest.class})

public class AllTests {	
	
	public static Test suite () {
		TestSuite suite = new TestSuite();
		/*suite.addTest(new PrivilegesTest("test"));
		suite.addTest(new BonusActionTest("test"));*/
		
		suite.addTest(new DiscountEffectTest("test"));
		
		return suite;
	}
		
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
		
	}


}