package myAdapter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * <p>
 * <b>Summary:</b> struttura dati che implementa l'interfaccia
 * {@link HCollection} e permette l'inserimento di elementi e loro duplicati,
 * ordinati secondo la sequenza di inserimento
 * </p>
 * <p>
 * <b>Optional:</b> non sono ammessi nella collezione elementi corrispondenti a
 * riferimenti null e non sono nemmeno accettate come parametri dei metodi
 * collezioni che non rispettano tale vincolo
 * </p>
 */
public class CollectionAdapter implements HCollection {
	/**
	 * Vettore che memorizza gli elementi della collezione
	 */
	protected Vector v;

	/**
	 * <p>
	 * <b>Summary:</b> iteratore che implementa l'interfaccia {@link HIterator} e
	 * permette di iterare e rimuovere gli elementi di collezioni della classe
	 * {@link CollectionAdapter}
	 * </p>
	 */
	protected class CollectionIterator implements HIterator {
		/**
		 * Iteratore che itera gli elementi della collezione
		 */
		private Iterator iter;

		/**
		 * Costruttore che inizializza l'iteratore della collezione
		 */
		public CollectionIterator() {
			this.iter = CollectionAdapter.this.v.iterator();
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
	 * Costruttore che inizializza una collezione vuota
	 */
	public CollectionAdapter() {
		this.v = new Vector();
	}

	/**
	 * Costruttore che inizializza la collezione con gli elementi della collezione
	 * passata come parametro
	 * 
	 * @param c collezione avente gli elementi che inizializzano la collezione
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null
	 */
	public CollectionAdapter(HCollection c) throws NullPointerException {
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
	 * Aggiunge l'oggetto passato come parametro alla collezione
	 * 
	 * @param o elemento aggiunto alla collezione
	 * @return true se la collezione cambia per effetto dell'aggiunta
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean add(Object o)
			throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException {
		if (o == null)
			throw new NullPointerException();

		return this.v.add(o);
	}

	/**
	 * Aggiunge gli elementi della collezione passata come parametro alla collezione
	 * 
	 * @param c collezione avente gli elementi da aggiungere alla collezione
	 * @return true se la collezione cambia per effetto delle aggiunte
	 * @throws NullPointerException se c e' riferimento null, oppure se c ha almeno
	 *                              un elemento null
	 */
	public boolean addAll(HCollection c)
			throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector cav = new Vector(Arrays.asList(c.toArray()));

		return this.v.addAll(cav);
	}

	/**
	 * Rimuove tutti gli elementi della collezione
	 */
	public void clear() throws UnsupportedOperationException {
		this.v.clear();
	}

	/**
	 * Verifica se la collezione contiene l'oggetto passato come parametro
	 * 
	 * @param o elemento per cui si verifica la presenza nella collezione
	 * @return true se l'elemento e' contenuto nella collezione
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean contains(Object o) throws ClassCastException, NullPointerException {
		if (o == null)
			throw new NullPointerException();

		return this.v.contains(o);
	}

	/**
	 * Verifica se la collezione contiene gli elementi della collezione passata come
	 * parametro
	 * 
	 * @param c collezione degli elementi per cui si verifica la presenza nella
	 *          collezione
	 * @return true se gli elementi della collezione c sono tutti contenuti nella
	 *         collezione
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null
	 */
	public boolean containsAll(HCollection c) throws ClassCastException, NullPointerException {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector cav = new Vector(Arrays.asList(c.toArray()));

		return this.v.containsAll(cav);
	}

	/**
	 * Verifica se la collezione e' uguale all'oggetto passato come parametro, ossia
	 * se ha stesso tipo, dimensione, elementi e ordine
	 * 
	 * @param o oggetto corrispondente alla collezione con cui avviene il confronto
	 * @return true se la collezione e' uguale all'oggetto o
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof CollectionAdapter))
			return false;

		CollectionAdapter ca = (CollectionAdapter) o;

		return this.v.equals(ca.v);
	}

	/**
	 * Restituisce il codice hash della collezione
	 * 
	 * @return codice hash della collezione
	 */
	public int hashCode() {
		return this.v.hashCode();
	}

	/**
	 * Verifica se la collezione e' vuota
	 * 
	 * @return true se la collezione e' vuota
	 */
	public boolean isEmpty() {
		return this.v.isEmpty();
	}

	/**
	 * Restituisce un iteratore della collezione
	 * 
	 * @return iteratore della collezione
	 */
	public HIterator iterator() {
		return new CollectionIterator();
	}

	/**
	 * Rimuove l'oggetto passato come parametro dalla collezione
	 * 
	 * @param o elemento rimosso dalla collezione
	 * @return true se la collezione cambia per effetto della rimozione
	 * @throws NullPointerException se o e' un riferimento null
	 */
	public boolean remove(Object o) throws UnsupportedOperationException, ClassCastException, NullPointerException {
		if (o == null)
			throw new NullPointerException();

		return this.v.remove(o);
	}

	/**
	 * Rimuove gli elementi della collezione passata come parametro dalla collezione
	 * 
	 * @param c collezione avente gli elementi da rimuovere dalla collezione
	 * @return true se la collezione cambia per effetto delle rimozioni
	 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
	 *                              un elemento null
	 */
	public boolean removeAll(HCollection c)
			throws UnsupportedOperationException, ClassCastException, NullPointerException {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector cav = new Vector(Arrays.asList(c.toArray()));

		return this.v.removeAll(cav);
	}

	/**
	 * Mantiene nella collezione solamente gli elementi della collezione passata
	 * come parametro
	 * 
	 * @param c collezione avente gli elementi da mantenere nella collezione
	 * @return true se la collezione cambia per effetto delle conservazioni
	 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
	 *                              un elemento null
	 */
	public boolean retainAll(HCollection c)
			throws UnsupportedOperationException, ClassCastException, NullPointerException {
		if (c == null)
			throw new NullPointerException();

		HIterator iter = c.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null)
				throw new NullPointerException();
		}

		Vector cav = new Vector(Arrays.asList(c.toArray()));

		return this.v.retainAll(cav);
	}

	/**
	 * Restituisce il numero di elementi contenuti nella collezione
	 * 
	 * @return numero di elementi contenuti nella collezione
	 */
	public int size() {
		if (this.v.size() > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		return this.v.size();
	}

	/**
	 * Restituisce un array con tutti gli oggetti contenuti nella collezione
	 * 
	 * @return array di oggetti della collezione
	 */
	public Object[] toArray() {
		return this.v.toArray();
	}

	/**
	 * Riempie l'array di oggetti passato come parametro con gli elementi contenuti
	 * nella collezione
	 * 
	 * @param a array di oggetti da riempire
	 * @return array di oggetti della collezione
	 * @throws NullPointerException se a e' un riferimento null
	 */
	public Object[] toArray(Object[] a) throws ArrayStoreException, NullPointerException {
		if (a == null)
			throw new NullPointerException();

		if (a.length > v.size())
			a = new Object[this.v.size()];

		return this.v.toArray(a);
	}

	/**
	 * Restituisce una stringa che illustra gli elementi contenuti nella collezione
	 * 
	 * @return stringa illustrativa degli elementi contenuti nella collezione
	 */
	public String toString() {
		return this.v.toString();
	}
}