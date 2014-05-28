package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="soorten")
public class Soort implements Serializable {
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue
	private int soortNr;
	private String naam;
	@ManyToOne //(fetch=FetchType.LAZY) //In eclipse geeft fetchType.LAZY problemen 
										//(org.hibernate.LazyInitializationException: could not initialize proxy - no Session). 
										//In netbeans werkt het perfect.
	@JoinColumn(name="LandNr")
	private Land land;
	@OneToMany(mappedBy="soort")
	@OrderBy("jaar")
	private Set<Wijn> wijnen;
	//combinatie naam - land is uniek
	
	
	protected Soort(){} //voor JPA
	
	public Soort(String naam, Land land){
		this.naam=naam;
		this.land=land;
		wijnen=new HashSet<Wijn>();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(! (obj instanceof Soort)){
			return false;
		}
		return naam.equalsIgnoreCase(((Soort)obj).getNaam()) && land.equals(((Soort)obj).getLand()); 
	}
	@Override
	public int hashCode() {
		
		return naam.toUpperCase().hashCode()+land.hashCode();
	}
	
	public int getSoortNr() {
		return soortNr;
	}
	public String getNaam() {
		return naam;
	}
	public Land getLand() {
		return land;
	}
	public Set<Wijn> getWijnen() {
		return Collections.unmodifiableSet(wijnen);
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	/*
	 * Bidirectionale associatie tussen land en soort
	 * Land in soort aanpassen en soort in land als dat nog niet gebeurd is
	 */
	public void setLand(Land land) {
		if(this.land!=null && this.land.getSoorten().contains(this)){
			this.land.removeSoort(this);
		}
		this.land = land;
		if(land!=null && !land.getSoorten().contains(this)){
			land.addSoort(this);
		}
	}
	
	/*
	 * Bidirectionale associatie tussen wijn en soort
	 * Wijn in soort aanpassen en soort in wijn als dat nog niet gebeurd is
	 */
	public void addWijn(Wijn wijn){
		wijnen.add(wijn);
		if( wijn.getSoort()!=this){
			wijn.setSoort(this);
		}
	}
	
	/*
	 * Bidirectionale associatie tussen wijn en soort
	 * Wijn in soort aanpassen en soort in wijn als dat nog niet gebeurd is
	 */
	public void removeWijn(Wijn wijn){
		wijnen.remove(wijn);
		if(wijn.getSoort()==this){
			wijn.setSoort(null);
		}
	}
	
	@Override
	public String toString() {
		
		return naam+":"+land.getNaam();
	}
	
	
	
	

}
