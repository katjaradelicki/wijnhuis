package be.vdab.dao;

import be.vdab.entities.Wijn;

public class WijnDAO extends AbstractDAO {
	public Wijn read(int wijnNr){
		return getEntityManager().find(Wijn.class, wijnNr);
	}
	
	public void pasInBestellingAan(int wijnNr,int inBestelling){
		Wijn oudeWijn=read(wijnNr);
		oudeWijn.setAantalInBestelling(inBestelling);
		
	}
}
