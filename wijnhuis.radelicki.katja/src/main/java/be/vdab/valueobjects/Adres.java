package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable { //immutable: geen setters
	private static final long serialVersionUID=1L;
	
	private String straat;
	private String huisNr;
	private String postcode;
	private String gemeente;
	
	
	public String getStraat() {
		return straat;
	}
	public String getHuisNr() {
		return huisNr;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
	
	protected Adres(){}
	public Adres(String straat,String huisNr,String postcode,String gemeente){
		this.straat=straat;
		this.huisNr=huisNr;
		this.postcode=postcode;
		this.gemeente=gemeente;
	}
	
	

}
