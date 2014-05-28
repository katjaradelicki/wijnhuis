package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn implements Serializable { //immutable: geen setters
	
	private static final long servialVersionUID=1L;
	//private Bestelbon bestelbon; //associatie maar in 1 richting nodig. Dus bestelbon hier weglaten
	
	//value object Bestelbonlijn met een associatie naar een entity Wijn
	@ManyToOne
	@JoinColumn(name="WijnNr")
	//@Column(name="WijnNr")
	private Wijn wijn;
	private int aantal;
	//wijn is uniek per bestelbon
	

	public Wijn getWijn() {
		return wijn;
	}

	public int getAantal() {
		return aantal;
	}

	protected Bestelbonlijn(){} //voor JPA
	
	public Bestelbonlijn( Wijn wijn, int aantal){
		
		this.wijn=wijn;
		this.aantal=aantal;
	}
	
	@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Bestelbonlijn)){
				return false;
			}
			return  wijn.equals(((Bestelbonlijn)obj).wijn);
		}
	
	@Override
		public int hashCode() {
			
			return  wijn.hashCode() ;
		}
	
	public BigDecimal getKost(){
		return (new BigDecimal(String.valueOf(wijn.getPrijs()))).multiply(new BigDecimal(String.valueOf(aantal)));//constructor met String is nauwkeuriger
	}
	
	@Override
		public String toString() {
			
			return wijn +":"+aantal;
		}

}
