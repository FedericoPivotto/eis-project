package myAdapter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * <p>
 * <b>Summary:</b> struttura dati che implementa l'interfaccia {@link HSet} e
 * permette l'inserimento senza ordinamento di elementi non duplicati
 * </p>
 * <p>
 * <b>Optional:</b> non sono ammessi nel set elementi corrispondenti a
 * riferimenti null e non sono nemmeno accettati come parametri dei metodi set
 * che non rispettano tale vincolo
 * </p>
 */
public class SetAdapter implements HSet {
	/**
	 * Vettore che memorizza gli elementi del set
	 */
	protected Vector v;

	/**
	 * <p>
	 * <b>Summary:</b> iteratore che implementa l'interfaccia {@link HIterator} e
	 * permette di iterare e rimuovere gli elementi di set della classe
	 * {@link SetAdapter}
	 * </p>
	 */
	protected class SetIterator implements HIterator {
		/**
		 * Iteratore che itera gli elementi del set
		 */
		private Iterator iter;

		/**
		 * Costruttore che inizializza l'iteratore del set
		 */
		public SetIterator() {
			this.iter = v.iterator();
		}

		/**
		 * Verifica se l'iteratore ha un elemento successivo
		 * 
		 * @return true se l'iteratore ha un elemento successivo
		 */
		public boolean hasNext() {
			return this.iter.hasNext();
		}

		/**
		 * Avanza l'iteratore all'elemento successivo
		 * 
		 * @return elemento successivo dell'iteratore
		 * @throws NoSuchElementException se l'iteratore non ha un elemento successivo
		 */
		public Object next() throws NoSuchElementException {
			return this.iter.next();
		}

		/**
		 * Rimuove l'ultimo elemento iterato dal metodo {@link HIterator#next()}
		 * 
		 * @throws IllegalStateException se prima non e' stato chiamato il metodo
		 *                               {@link HIterator#next()}
		 */
		public void remove() throws UnsupportedOperationException, IllegalStateException {
			this.iter.remove();
		}
	}

	/**
	 * Costruttore che inizializza un set vuoto
	 */
	public SetAdapter() {
		this.v = new Vector();
	}

	/**
	 * Costruttore che inizializza il set con gli elementi del set passato come
	 * parametro
	 * 
	 * @param c set avente gli elementi che inizializzano il set
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null
	 */
	public SetAdapter(HCollection c) throws NullPointerException {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		this.v = new Vector(Arrays.asList(c.toArray()));
	}

	/**
	 * Aggiunge l'oggetto passato come parametro al set se non presente
	 * 
	 * @param o elemento aggiunto al set se non duplicato
	 * @return true se il set cambia per effetto dell'aggiunta
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean add(Object o) {
		if (o == null)
			throw new NullPointerException();

		if (!this.v.contains(o))
			return this.v.add(o);

		return false;
	}

	/**
	 * Aggiunge gli elementi del set passato come parametro al set se non presenti
	 * 
	 * @param c set avente gli elementi da aggiungere al set se non duplicati
	 * @return true se il set cambia per effetto delle aggiunte
	 * @throws NullPointerException se c e' riferimento null, oppure c ha almeno un
	 *                              elemento null
	 */
	public boolean addAll(HCollection c) {
		if (c == null)
			throw new NullPointerException();

		boolean change = false;
		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			Object tmp = iter.next();
			if (this.add(tmp))
				change = true;
		}

		return change;
	}

	/**
	 * Rimuove tutti gli elementi del set
	 */
	public void clear() {
		this.v.clear();
	}

	/**
	 * Verifica se il set contiene l'oggetto passato come parametro
	 * 
	 * @param o elemento per cui si verifica la presenza nel set
	 * @return true se l'elemento e' contenuto nel set
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean contains(Object o) {
		if (o == null)
			throw new NullPointerException();

		return this.v.contains(o);
	}

	/**
	 * Verifica se il set contiene gli elementi del set passato come parametro
	 * 
	 * @param c set degli elementi per cui si verifica la presenza nel set
	 * @return true se gli elementi del set c sono tutti contenuti nel set
	 * @throws NullPointerException se c e' un riferimento null
	 */
	public boolean containsAll(HCollection c) {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector sav = new Vector(Arrays.asList(c.toArray()));

		return this.v.containsAll(sav);
	}

	/**
	 * Verifica se il set e' uguale all'oggetto passato come parametro, ossia se ha
	 * stesso tipo, dimensione ed elementi
	 * 
	 * @param o oggetto corrispondente al set con cui avviene il confronto
	 * @return true se il set e' uguale all'oggetto o
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof SetAdapter))
			return false;

		SetAdapter sa = (SetAdapter) o;

		return this.v.containsAll(sa.v);
	}

	/**
	 * Restituisce il codice hash del set
	 * 
	 * @return codice hash del set
	 */
	public int hashCode() {
		int hash = 0;
		HIterator iter = this.iterator();
		while (iter.hasNext()) {
			Object tmp = iter.next();
			if (tmp != null)
				hash += tmp.hashCode();
		}

		return hash;
	}

	/**
	 * Verifica se il set e' vuoto
	 * 
	 * @return true se il set e' vuoto
	 */
	public boolean isEmpty() {
		return this.v.isEmpty();
	}

	/**
	 * Restituisce un iteratore del set
	 * 
	 * @return iteratore del set
	 */
	public HIterator iterator() {
		return new SetIterator();
	}

	/**
	 * Rimuove l'oggetto passato come parametro dal set
	 * 
	 * @param o elemento rimosso dal set
	 * @return true se il set cambia per effetto della rimozione
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean remove(Object o) {
		if (o == null)
			throw new NullPointerException();

		return this.v.remove(o);
	}

	/**
	 * Rimuove gli elementi del set passato come parametro dal set
	 * 
	 * @param c set avente gli elementi da rimuovere dal set
	 * @return true se il set cambia per effetto delle rimozioni
	 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
	 *                              un elemento null
	 */
	public boolean removeAll(HCollection c) {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector sav = new Vector(Arrays.asList(c.toArray()));

		return this.v.removeAll(sav);
	}

	/**
	 * Mantiene nel set solamente gli elementi del set passato come parametro
	 * 
	 * @param c set avente gli elementi da mantenere nel set
	 * @return true se il set cambia per effetto delle conservazioni
	 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
	 *                              un elemento null
	 */
	public boolean retainAll(HCollection c) {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector sav = new Vector(Arrays.asList(c.toArray()));

		return this.v.retainAll(sav);
	}

	/**
	 * Restituisce il numero di elementi contenuti nel set
	 * 
	 * @return numero di elementi contenuti nel set
	 */
	public int size() {
		if (this.v.size() > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		return this.v.size();
	}

	/**
	 * Restituisce un array con tutti gli oggetti contenuti nel set
	 * 
	 * @return array di oggetti del set
	 */
	public Object[] toArray() {
		return this.v.toArray();
	}

	/**
	 * Riempie l'array di oggetti passato come parametro con gli elementi contenuti
	 * nel set
	 * 
	 * @param a array di oggetti da riempire
	 * @return array di oggetti del set
	 * @throws NullPointerException se a e' un riferimento null
	 */
	public Object[] toArray(Object[] a) {
		if (a == null)
			throw new NullPointerException();

		if (a.length > this.v.size())
			a = new Object[this.v.size()];

		return this.v.toArray(a);
	}

	/**
	 * Restituisce una stringa che illustra gli elementi contenuti nel set
	 * 
	 * @return stringa illustrativa degli elementi contenuti nel set
	 */
	public String toString() {
		return this.v.toString();
	}
}