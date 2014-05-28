package be.vdab.services;

import be.vdab.dao.BestelbonDAO;
import be.vdab.dao.WijnDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.valueobjects.Bestelbonlijn;

public class BestelbonService {
	
	private static final BestelbonDAO bestelbonDAO=new BestelbonDAO();
	private static final WijnDAO wijnDAO=new WijnDAO();
	
	
	/*
	 * Eerst bestelbonlijnen toevoegen aan bestelbon in het intern geheugen 
	 * Dan bestelbon( automatisch met zijn lijnen) toevoegen aan de databank met deze methode
	 */
	public void create(Bestelbon bestelbon){//in 1 transactie: bestelbon met zijn lijnen creëren en InBestelling bij wijn aanpassen voor elke lijn
		bestelbonDAO.beginTransaction();
		bestelbonDAO.create(bestelbon);
		for(Bestelbonlijn lijn:bestelbon.getBestelbonlijnen()){
			wijnDAO.pasInBestellingAan(lijn.getWijn().getWijnNr(), lijn.getWijn().getAantalInBestelling());
		}
		bestelbonDAO.commit();
	}
	

}
