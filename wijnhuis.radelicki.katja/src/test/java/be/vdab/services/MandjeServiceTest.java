/*
package be.vdab.services;

import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Land;
import be.vdab.entities.Soort;
import be.vdab.entities.Wijn;
import be.vdab.valueobjects.Bestelbonlijn;
import org.junit.*;


public class MandjeServiceTest  {
	
	private List<Bestelbonlijn> mandje1=null;
	private List<Bestelbonlijn> mandje2=null;
	private List<Bestelbonlijn> mandjeLeeg=null;

	Bestelbonlijn bestelbonlijn1;
	Bestelbonlijn bestelbonlijn2;
	Bestelbonlijn bestelbonlijn3;
	
	
	
	@Before
	public void before(){
		
		mandje1=new ArrayList<>();
		mandje2=new ArrayList<>();
		mandjeLeeg=new ArrayList<>();
		bestelbonlijn1=new Bestelbonlijn(new Wijn(1988,4,3.5,2,new Soort("Chardonnay",new Land("Bulgarijë"))), 2);
		bestelbonlijn2=new Bestelbonlijn(new Wijn(1882,3,5.9,0,new Soort("Baden",new Land("Duitsland"))), 1);
		bestelbonlijn3=new Bestelbonlijn(new Wijn(1996, 2, 4.6, 10, new Soort("Calabria",new Land("Italië"))), 3);
		mandje1.add(bestelbonlijn1);
		mandje1.add(bestelbonlijn2);
		mandje1.add(bestelbonlijn3);
		mandje2.add(bestelbonlijn1);
		mandje2.add(bestelbonlijn2);
		mandje2.add(bestelbonlijn3);
	}

	@Test
	public void test_bestelbonlijn_toevoegen_met_wijn_die_er_niet_inzit(){
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(new Wijn(1988,4,3.5,2,new Soort("Chardonnay",new Land("Australië"))), 2);
		mandjeService.voegLijnToeAanMandje(mandje1, bestelbonlijn);
		//nu moeten er 4 lijnen inzitten
		Assert.assertEquals(4, mandje1.size());
		Assert.assertTrue(mandje1.contains(bestelbonlijn));
	}
	
	@Test
	public void test_bestelbonlijn_toevoegen_met_wijn_die_er_al_inzit(){
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(new Wijn(1882,3,5.9,0,new Soort("Baden",new Land("Duitsland"))), 2);
		mandjeService.voegLijnToeAanMandje(mandje1, bestelbonlijn);
		Assert.assertEquals(3, mandje1.size());
		Assert.assertTrue(mandje1.contains(bestelbonlijn));
		Assert.assertEquals(3, mandje1.get(mandje1.indexOf(bestelbonlijn)).getAantal());
	}
}
*/
