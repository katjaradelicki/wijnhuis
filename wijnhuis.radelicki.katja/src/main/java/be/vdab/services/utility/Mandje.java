package be.vdab.services.utility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import be.vdab.entities.Wijn;
import be.vdab.valueobjects.Bestelbonlijn;
//voordeel van "implements List<Bestelbonlijn>" is dat ik weinig code in de Servlets enzo moet veranderen, 
//want tegen de interface List geprogrammeerd. Implementeerde je de klasse niet dan moest je overal List<Bestelbonlijn> vervangen door Mandje
//nadeel is dat je veel methoden moet implementeren (in de meeste gevallen gewoon overriden van List-methoden)
/*
 * Mandje wordt gebruikt als sessie-Object in dit project
 */
public class Mandje implements List<Bestelbonlijn>, Serializable{
	
	private List<Bestelbonlijn> lijst = new ArrayList<>();

	@Override
	public int size() {
		return lijst.size();
	}

	@Override
	public boolean isEmpty() {
		return lijst.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		if( o instanceof Bestelbonlijn)
			return lijst.contains(o);
		else if( o instanceof Wijn){
			boolean gevonden=false;
			Iterator<Bestelbonlijn> i = lijst.iterator();
			while(!gevonden && i.hasNext()){
				gevonden = i.next().getWijn().equals(o);
			}
			return gevonden;
		}else
			return false;
	}

	@Override
	public Iterator<Bestelbonlijn> iterator() {
		return lijst.iterator();
	}

	@Override
	public Object[] toArray() {
		return lijst.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return lijst.toArray(a);
	}

	/*
	 * Als er al een bestelbonlijn met wijn x in het mandje zit, wanneer je bestelbonlijn met wijn x wil toevoegen, 
	 * dan voeg je geen nieuwe bestelbonlijn toe maar verhoog je het aantalFlessen in de bestelbonlijn met wijn x
	 * Zit er nog geen bestelbonlijn in met wijn x, dan voeg je de nieuwe bestelbonlijn toe aan het mandje.	 */
	@Override
	public boolean add(Bestelbonlijn bestelbonlijn) {
		if(bestelbonlijn==null || bestelbonlijn.getAantal()<=0){
			return false;
		}
		if(lijst.contains(bestelbonlijn)){
			Bestelbonlijn oudeLijn= lijst.get(lijst.indexOf(bestelbonlijn));
			Bestelbonlijn nieuweLijn = new Bestelbonlijn(oudeLijn.getWijn(), oudeLijn.getAantal()+bestelbonlijn.getAantal());
			//mandje.remove(oudeLijn);
			//mandje.add(nieuweLijn);
			lijst.set(lijst.indexOf(bestelbonlijn), nieuweLijn);
			return true;
		}else{
			lijst.add(bestelbonlijn);
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		if( o instanceof Bestelbonlijn)
			return lijst.remove(o);
		else if( o instanceof Wijn){
			boolean gevonden=false;
			Iterator<Bestelbonlijn> i = lijst.iterator();
			while(!gevonden && i.hasNext()){
				gevonden = i.next().getWijn().equals(o);
			}
			i.remove();
			return true;
		}else
			return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return lijst.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Bestelbonlijn> c) {
		return lijst.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Bestelbonlijn> c) {
		return lijst.addAll(index,c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		return lijst.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		return lijst.retainAll(c);
	}

	@Override
	public void clear() {
		lijst.clear();
		
	}

	@Override
	public Bestelbonlijn get(int index) {
		
		return lijst.get(index);
	}
	
	
	
	public Bestelbonlijn get(Wijn wijn){
		boolean gevonden=false;
		Iterator<Bestelbonlijn> i = lijst.iterator();
		while(!gevonden && i.hasNext()){
			Bestelbonlijn bestelbonlijn=i.next();
			if(bestelbonlijn.getWijn().equals(wijn)){
				gevonden=true;
				return bestelbonlijn;
			}
		}
		return null;
		
	}

	@Override
	public Bestelbonlijn set(int index, Bestelbonlijn element) {
		
		return lijst.set(index, element);
	}

	@Override
	public void add(int index, Bestelbonlijn element) {
		lijst.add(index, element);
		
	}

	@Override
	public Bestelbonlijn remove(int index) {
		
		return lijst.remove(index);
	}

	@Override
	public int indexOf(Object o) {//eventueel indexOf(Wijn) voorzien
		
		return lijst.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		
		return lijst.lastIndexOf(o);
	}

	@Override
	public ListIterator<Bestelbonlijn> listIterator() {
		
		return lijst.listIterator();
	}

	@Override
	public ListIterator<Bestelbonlijn> listIterator(int index) {
		
		return lijst.listIterator(index);
	}

	@Override
	public List<Bestelbonlijn> subList(int fromIndex, int toIndex) {
		
		return lijst.subList(fromIndex, toIndex);
	}
	
	public BigDecimal getTotaleKost(){
		
		BigDecimal totaal=BigDecimal.ZERO;
		for(Bestelbonlijn lijn:lijst){
			totaal=totaal.add(lijn.getKost());
		}
		return totaal;
	}
	

}
