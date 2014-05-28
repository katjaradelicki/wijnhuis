package be.vdab.services;

import be.vdab.dao.WijnDAO;
import be.vdab.entities.Wijn;

public class WijnService {
	private static final WijnDAO wijnDAO=new WijnDAO();
	
	public Wijn read(int wijnNr){
		
		Wijn wijn=null;
		wijn=wijnDAO.read(wijnNr);
		return wijn;
	}

}
