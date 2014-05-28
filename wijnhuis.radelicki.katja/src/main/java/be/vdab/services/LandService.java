package be.vdab.services;

import java.util.List;

import be.vdab.dao.LandDAO;
import be.vdab.entities.Land;

public class LandService {
	private static final LandDAO landDAO=new LandDAO();
	
	public List<Land> findAll(){
		return landDAO.findAll();
	}
	
	public Land read(int landNr){
		return landDAO.read(landNr);
	}

}
