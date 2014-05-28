package be.vdab.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import be.vdab.entities.Land;

public class LandDAO extends AbstractDAO{
	
	public List<Land> findAll(){
		TypedQuery<Land> query=getEntityManager().createNamedQuery("land.findAll",Land.class);
		return query.getResultList();
	}
	
	public Land read(int landNr){
		return getEntityManager().find(Land.class, landNr);
	}

}
