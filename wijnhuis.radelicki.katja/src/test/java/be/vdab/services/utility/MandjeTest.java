package be.vdab.services.utility;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import be.vdab.entities.Land;
import be.vdab.entities.Soort;
import be.vdab.entities.Wijn;
import be.vdab.valueobjects.Bestelbonlijn;

public class MandjeTest {

	private List<Bestelbonlijn> mandje1=null;
	private List<Bestelbonlijn> mandje2=null;
	private List<Bestelbonlijn> mandjeLeeg=null;

	Bestelbonlijn bestelbonlijn1;
	Bestelbonlijn bestelbonlijn2;
	Bestelbonlijn bestelbonlijn3;
	
	
	
	@Before
	public void before(){
		
		mandje1=new Mandje();
		mandje2=new Mandje();
		mandjeLeeg=new Mandje();
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
	
	//TEST add(Bestelbonlijn)
	@Test
	public void test_bestelbonlijn_toevoegen_met_wijn_die_er_niet_inzit(){
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(new Wijn(1988,4,3.5,2,new Soort("Chardonnay",new Land("Australië"))), 2);
		//mandjeService.voegLijnToeAanMandje(mandje1, bestelbonlijn);
		mandje1.add(bestelbonlijn);
		//nu moeten er 4 lijnen inzitten
		Assert.assertEquals(4, mandje1.size());
		Assert.assertTrue(mandje1.contains(bestelbonlijn));
	}
	
	@Test
	public void test_bestelbonlijn_toevoegen_met_wijn_die_er_al_inzit(){
		Wijn wijn=new Wijn(1882,3,5.9,0,new Soort("Baden",new Land("Duitsland")));
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(wijn, 2);
		//mandjeService.voegLijnToeAanMandje(mandje1, bestelbonlijn);
		mandje1.add(bestelbonlijn);
		Assert.assertEquals(3, mandje1.size());
		Assert.assertTrue(mandje1.contains(wijn));
		Assert.assertEquals(3, ((Mandje)mandje1).get(wijn).getAantal());
	}
	
	@Test
	public void test_bestelbonlijn_toevoegen_in_leeg_mandje(){
		Wijn wijn=new Wijn(1988,4,3.5,2,new Soort("Chardonnay",new Land("Bulgarijë")));
		mandjeLeeg.add(bestelbonlijn1);
		Assert.assertEquals(1, mandjeLeeg.size());
		Assert.assertTrue(mandjeLeeg.contains(wijn));
		
	}
	
	//TEST contains(Object)
	
	@Test
	public void test_contains_wijn_die_erin_zit(){
		Wijn wijn=new Wijn(1996, 2, 4.6, 10, new Soort("Calabria",new Land("Italië")));
		Assert.assertTrue(mandje1.contains(wijn));
		
	}
	
	@Test
	public void test_contains_wijn_die_er_niet_in_zit_jaartal_anders(){
		Wijn wijn=new Wijn(1995, 2, 4.6, 10, new Soort("Calabria",new Land("Italië")));
		Assert.assertFalse(mandje1.contains(wijn));
		
	}
	
	@Test
	public void test_contains_null(){
		
		Assert.assertFalse(mandje1.contains(null));
		
	}
	@Test
	public void test_contains_wijn_in_leeg_mandje(){
		Wijn wijn=new Wijn(1995, 2, 4.6, 10, new Soort("Calabria",new Land("Italië")));
		Assert.assertFalse(mandjeLeeg.contains(wijn));
		
	}
	
	@Test
	public void test_contains_bestelbonlijn_die_erin_zit(){
		Wijn wijn=new Wijn(1996, 2, 4.6, 10, new Soort("Calabria",new Land("Italië")));
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(wijn, 3);
		Assert.assertTrue(mandje1.contains(bestelbonlijn));
		
	}
	
	@Test
	public void test_contains_bestelbonlijn_die_erin_zit_hoewel_ander_aantal(){
		Wijn wijn=new Wijn(1996, 2, 4.6, 10, new Soort("Calabria",new Land("Italië")));
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(wijn, 2);
		//2 bestelbonlijnen zijn gelijk als ze dezelfde wijn bezitten, aantal maakt niet uit
		Assert.assertTrue(mandje1.contains(bestelbonlijn));
		
	}
	
	@Test
	public void test_contains_bestelbonlijn_die_er_niet_in_zit_verkeerde_wijn(){
		Wijn wijn=new Wijn(1996, 2, 4.6, 10, new Soort("Calabria",new Land("Spanje")));
		Bestelbonlijn bestelbonlijn=new Bestelbonlijn(wijn, 3);
		Assert.assertFalse(mandje1.contains(bestelbonlijn));
		
	}
	
	@Test
	public void test_contains_foutieve_klasse(){
		String eenString="Spanje"; 
		Assert.assertFalse(mandje1.contains(eenString));
		
	}
	

}
