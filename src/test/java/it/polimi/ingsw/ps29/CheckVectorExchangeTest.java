package it.polimi.ingsw.ps29;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.ExchangeSupport;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class CheckVectorExchangeTest extends TestCase {
	
	ExchangeSupport support;
	
	public CheckVectorExchangeTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		ArrayList<Resource> r1 = new ArrayList<Resource>();
		r1.add(new Resource("coin", 2));
		ArrayList<Resource> r2 = new ArrayList<Resource>();
		r2.add(new Resource("wood", 3));
		ArrayList<Resource> r3 = new ArrayList<Resource>();
		r3.add(new Resource("coin", 1));
		r3.add(new Resource("stone", 2));
		ArrayList<Resource> r4 = new ArrayList<Resource>();
		r4.add(new Resource("servant", 2));
		ArrayList<Resource> r5 = new ArrayList<Resource>();
		r5.add(new Resource("wood", 2));
		ArrayList<Resource> r6 = new ArrayList<Resource>();
		r6.add(new Resource("privilege", 2));
		r6.add(new Resource("faith", 2));
		ArrayList<Resource> r7 = new ArrayList<Resource>();
		r7.add(new Resource("coin", 4));
		ArrayList<Resource> r8 = new ArrayList<Resource>();
		r8.add(new Resource("military",1));
		
		ExchangeResourceHandler erh1 = new ExchangeResourceHandler(r1, r2, false, false);
		ExchangeResourceHandler erh2 = new ExchangeResourceHandler(r3, r4, false, false);
		ExchangeResourceHandler erh3 = new ExchangeResourceHandler(r5, r6, false, false);
		ExchangeResourceHandler erh4 = new ExchangeResourceHandler(r7, r8, false, false);
		
		ArrayList <ExchangeResourceHandler> arr1 = new ArrayList <ExchangeResourceHandler> ();
		arr1.add(erh1);
		ArrayList <ExchangeResourceHandler> arr2 = new ArrayList <ExchangeResourceHandler> ();
		arr2.add(erh2);
		arr2.add(erh3);
		ArrayList <ExchangeResourceHandler> arr3 = new ArrayList <ExchangeResourceHandler> ();
		arr3.add(erh4);
		
		ExchangeResourcesEffect eff1 = new ExchangeResourcesEffect(arr1);
		ExchangeResourcesEffect eff2 = new ExchangeResourcesEffect(arr2);
		ExchangeResourcesEffect eff3 = new ExchangeResourcesEffect(arr3);
		
		ArrayList <ExchangeResourcesEffect> effects = new ArrayList<ExchangeResourcesEffect>();
		effects.add(eff1);
		effects.add(eff2);
		effects.add(eff3);
		
		Container container = new Container ();
		//container.updateResource(new Resource ("coin", 3));
		
		//container ha 2wood, 3servant, 2stone
		support = new ExchangeSupport(effects, container);
		
		support.checkVector();
		
	}
	
	@Test
	public void test() {
		assertEquals(1, support.getOptions().size());
		
	}
	

}