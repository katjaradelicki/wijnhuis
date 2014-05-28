package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table(name="bestelbonnen")
public class Bestelbon implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue
	private int bonNr;
	@Temporal(TemporalType.TIMESTAMP)
	private Date bestelDatum;//datum+tijd: om zelfde tijstip kan 1 persoon niet tweemaal iets bestellen (als je datum+tijd wil moet je 'timestamp' gebruiken in de annotation)
	private String naam;
	@Embedded
	private Adres adres;
	private Bestelwijze bestelwijze; //0 is afhalen, 1 is levering aan huis
	
	@ElementCollection
	@CollectionTable(name="bestelbonlijnen",joinColumns= @JoinColumn(name="BonNr"))
	@OrderBy("wijn.soort.land.naam")
	private Set<Bestelbonlijn> bestelbonlijnen;
	//combinatie bestelDatum (met tijd) - naam -adres als uniek beschouwd
	
	
	
	/*
	 * Als een bestelbonlijn wordt toegevoegd, 
	 * verhoogt de variable "aantalInBestelling" van wijn met het aantal in de bestelbonlijn
	 * (Deze methode wijzigt enkel het interne geheugen, niet de databank)
	 */
	public void addBestelbonlijn(Bestelbonlijn bestelbonlijn){
		
		bestelbonlijnen.add(bestelbonlijn);
		int aantalInBestelling=bestelbonlijn.getWijn().getAantalInBestelling();
		bestelbonlijn.getWijn().setAantalInBestelling(aantalInBestelling+bestelbonlijn.getAantal());
		
	}
	
	protected Bestelbon(){}//voor JAP
	
	public Bestelbon(Date bestelDatum,String naam, Adres adres, Bestelwijze bestelwijze){
		this.bestelDatum=bestelDatum;
		this.naam=naam;
		this.adres=adres;
		this.bestelwijze=bestelwijze;
		bestelbonlijnen= new HashSet<Bestelbonlijn>();
	}

	public int getBonNr() {
		return bonNr;
	}

	public Date getBestelDatum() {
		return bestelDatum;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public Bestelwijze getBestelwijze() {
		return bestelwijze;
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}

	public void setBonNr(int bonNr) {
		this.bonNr = bonNr;
	}

	public void setBestelDatum(Date bestelDatum) {
		this.bestelDatum = bestelDatum;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}
	

	
	@Override
	public String toString() {
		String output="BESTELBON: ";
		for(Bestelbonlijn lijn:bestelbonlijnen){
			output=output+" lijn: "+lijn;
		}
		return output;
	}
	

}
