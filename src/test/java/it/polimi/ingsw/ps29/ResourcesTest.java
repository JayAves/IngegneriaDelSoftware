package it.polimi.ingsw.ps29;

import org.junit.BeforeClass;
import org.junit.Test;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceDecorator;
import junit.framework.TestCase;

public class ResourcesTest extends TestCase {
	
	Container container;
	ResourceDecorator resd;
	
	public ResourcesTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		container = new Container ();
		container.updateResource(new Resource ("coin", 4));
		container.updateResource(new Resource ("coin", 2));
		
		resd = new ResourceDecorator(container.getResource("coin"), -2);
		container.substituteResource(resd);
		container.updateResource(new Resource("coin", 3));
		container.updateResource(new Resource ("servant", 1));
		
		//decoratore da controllare per sweeps
		/*container.swipeResource(new Resource("coin", 2),  new Resource ("servant", -1));
		System.out.println(container.getResource("coin").getAmount());
		System.out.println(container.getResource("servant").getAmount());*/
	}
	
	
	@Test
	public void test() {
		assertEquals(7, container.getResource("coin").getAmount());
		assertEquals(4, container.getResource("servant").getAmount());
		
	}

}
