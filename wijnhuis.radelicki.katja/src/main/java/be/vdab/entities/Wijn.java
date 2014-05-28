package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="wijnen")
public class Wijn implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue
	private int wijnNr;
	private int jaar;
	private int beoordeling;
	private double prijs;
	@Column(name="InBestelling")
	private int aantalInBestelling;
	@ManyToOne //(fetch=FetchType.LAZY) //In eclipse geeft fetchType.LAZY problemen 
										//(org.hibernate.LazyInitializationException: could not initialize proxy - no Session).
										//In netbeans werkt het perfect.
	@JoinColumn(name="SoortNr")
	private Soort soort;
	//combinatie jaar-soort is uniek
	
	protected Wijn(){} //voor JPA
	public Wijn(int jaar,int beoordeling,double prijs,int aantalInBestelling,Soort soort){
		this.jaar=jaar;
		this.beoordeling=beoordeling;
		this.prijs=prijs;
		this.aantalInBestelling=aantalInBestelling;
		this.soort=soort;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Wijn)){
			return false;
		}
		return (jaar== ((Wijn)obj).jaar) && soort.equals(((Wijn)obj).soort);
	}
	
	@Override
	public int hashCode() {
		
		return jaar + soort.hashCode();
	}
	
	
	public int getWijnNr() {
		return wijnNr;
	}
	public int getJaar() {
		return jaar;
	}
	public int getBeoordeling() {
		return beoordeling;
	}
	public double getPrijs() {
		return prijs;
	}
	
	public int getAantalInBestelling() {
		return aantalInBestelling;
	}
	
	public Soort getSoort() {
		return soort;
	}
	

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
	public void setBeoordeling(int beoordeling) {
		this.beoordeling = beoordeling;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	public void setAantalInBestelling(int aantalInBestelling) {
		this.aantalInBestelling = aantalInBestelling;
	}
	/*
	 * Bidirectionale associatie tussen wijn en soort
	 * Soort in wijn aanpassen en wijn in soort als dat nog niet gebeurd is
	 */
	public void setSoort(Soort soort) {
		if(this.soort!=null && this.soort.getWijnen().contains(this)){
			this.soort.removeWijn(this);
		}
		this.soort = soort;
		if(soort!=null && ! soort.getWijnen().contains(this)){
			soort.addWijn(this);
		}
	}
	
	@Override
	public String toString() {
		
		return jaar + ":" +soort.getNaam()+"(In bestelling: "+aantalInBestelling+")";
	}

}
