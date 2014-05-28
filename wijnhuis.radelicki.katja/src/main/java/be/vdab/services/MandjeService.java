/* NIET MEER NODIG doordat ik een object Mandje heb gemaakt (en daar deze services als methoden heb geïmplementeerd) ipv rechtstreeks met List<Bestelbonlijn> te werken

package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.valueobjects.Bestelbonlijn;
*/
/*
 * Service klasse voor het sessie-object Mandje
 */
/*
public class MandjeService {
	
	public BigDecimal getTotaleKostMandje(List<Bestelbonlijn> mandje){
		//alternatief zou kunnen zijn: een eigengemaakte klasse als sessie-object bewaren of bestelbon 
		//en daar deze methode getTotaleKost voorzien. Zo zitten verantwoordelijkheden bij de juiste klasse. 
		//Maar hier gekozen voor een List als sessie-object, dus aparte service-klasse nodig
		BigDecimal totaal=BigDecimal.ZERO;
		for(Bestelbonlijn lijn:mandje){
			totaal=totaal.add(lijn.getKost());
		}
		return totaal;
	}
	
*/	
	/*
	 * Als er al een bestelbonlijn met wijn x in het mandje zit, wanneer je bestelbonlijn met wijn x wil toevoegen, 
	 * dan voeg je geen nieuwe bestelbonlijn toe maar verhoog je het aantalFlessen in de bestelbonlijn met wijn x
	 * Zit er nog geen bestelbonlijn in met wijn x, dan voeg je de nieuwe bestelbonlijn toe aan het mandje.	 */
/*
	public void voegLijnToeAanMandje(List<Bestelbonlijn> mandje,Bestelbonlijn bestelbonlijn){
		if(mandje.contains(bestelbonlijn)){
			Bestelbonlijn oudeLijn= mandje.get(mandje.indexOf(bestelbonlijn));
			Bestelbonlijn nieuweLijn = new Bestelbonlijn(oudeLijn.getWijn(), oudeLijn.getAantal()+bestelbonlijn.getAantal());
			//mandje.remove(oudeLijn);
			//mandje.add(nieuweLijn);
			mandje.set(mandje.indexOf(bestelbonlijn), nieuweLijn);
		}else{
			mandje.add(bestelbonlijn);
		}

	}

}
*/
