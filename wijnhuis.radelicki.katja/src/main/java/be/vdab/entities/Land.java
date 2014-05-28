package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="landen")
public class Land implements Serializable{
	
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue
	private int landNr;
	private String naam;
	@OneToMany(mappedBy="land")
	@OrderBy("naam")
	private Set<Soort> soorten;
	//naam is uniek
	
	
	
	protected Land(){} //voorJPA
	
	public Land(String naam){
		this.naam=naam;
		soorten=new HashSet<Soort>();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Land)){
			return false;
		}
		return naam.equalsIgnoreCase(((Land)obj).getNaam());
	}
	
	@Override
	public int hashCode() {
		
		return naam.toUpperCase().hashCode();
	}
	
	public int getLandNr() {
		return landNr;
	}
	public String getNaam() {
		return naam;
	}
	public Set<Soort> getSoorten() {
		return Collections.unmodifiableSet(soorten);
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	/*
	 * Bidirectionele associatie tussen land en soorten. 
	 * Dus soorten in land aanpassen en land in Soort (als dat nog niet gebeurd is)
	 */
	public void addSoort(Soort soort){
		soorten.add(soort);
		if(soort.getLand()!=this){
			soort.setLand(this);
		}
	}
	
	/*
	 * Bidirectionele associatie tussen land en soorten. 
	 * Dus soorten in land aanpassen en land in Soort (als dat nog niet gebeurd is)
	 */
	public void removeSoort(Soort soort){
		soorten.remove(soort);
		if(soort.getLand()==this){
			soort.setLand(null);
		}
	}
	
	public Soort getSoort(int soortNr){
		Soort gevondenSoort=null;
		for(Soort soort:soorten){
			if(soort.getSoortNr()==soortNr){
				gevondenSoort=soort;
			}
		}
		return gevondenSoort;
	}
	
	
	

}
