package myAdapter;

import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary:</b> struttura dati che implementa l'interfaccia {@link HMap} e
 * permette l'inserimento senza ordinamento di entry chiave-valore, dove le
 * chiavi sono uniche
 * </p>
 * <p>
 * <b>Optional:</b> non sono ammesse nella mappa entry chiave-valore aventi
 * chiave e/o valore null
 * </p>
 */
public class MapAdapter implements HMap {
	/**
	 * Tabella hash che memorizza gli elementi della mappa
	 */
	private Hashtable h;

	/**
	 * Set con supporto al backing che memorizza le entry chiave-valore della mappa
	 */
	private BackedSet entrys;

	/**
	 * Set con supporto al backing che memorizza le chiavi associate alle entry
	 * chiave-valore della mappa
	 */
	private BackedSet keys;

	/**
	 * Collezione con supporto al backing che memorizza i valori associati alle
	 * entry chiave-valore della mappa
	 */
	private BackedCollection valuec;

	/**
	 * <p>
	 * <b>Summary:</b> entry chiave-valore della mappa che implementa l'interfaccia
	 * {@link HEntry}
	 * </p>
	 * <p>
	 * <b>Optional:</b> non sono ammesse nella mappa entry chiave-valore aventi
	 * chiave e/o valore null
	 * </p>
	 */
	protected class EntryMapAdapter implements HEntry {
		/**
		 * Chiave associata alla entry chiave-valore
		 */
		private Object k;

		/**
		 * Chiave associata alla entry chiave-valore
		 */
		private Object v;

		/**
		 * Costruttore che inizializza una entry chiave-valore con chiave key e valore
		 * value
		 * 
		 * @param key   chiave della entry chiave-valore
		 * @param value valore della entry chiave-valore
		 * @throws NullPointerException se key o value e' un riferimento null
		 */
		public EntryMapAdapter(Object key, Object value) throws NullPointerException {
			if (key == null || value == null)
				throw new NullPointerException();

			this.k = key;
			this.v = value;
		}

		/**
		 * Verifica se la entry e' uguale all'oggetto passato come parametro, ossia se
		 * ha stesso tipo, chiave e valore
		 * 
		 * @param o oggetto corrispondente alla entry chiave-valore con cui avviene il
		 *          confronto
		 * @return true se la entry e' uguale all'oggetto o
		 */
		public boolean equals(Object o) {
			if (o == null || !(o instanceof EntryMapAdapter))
				return false;

			EntryMapAdapter e = (EntryMapAdapter) o;

			if (this.k.equals(e.getKey()) && this.v.equals(e.getValue()))
				return true;

			return false;
		}

		/**
		 * Restituisce la chiave associata alla entry
		 * 
		 * @return chiave associata alla entry
		 */
		public Object getKey() {
			return this.k;
		}

		/**
		 * Restituisce il valore associato alla entry
		 * 
		 * @return valore associato alla entry
		 */
		public Object getValue() {
			return this.v;
		}

		/**
		 * Restituisce il codice hash della entry
		 * 
		 * @return codice hash della entry
		 */
		public int hashCode() {
			return (this.k == null ? 0 : this.k.hashCode()) ^ (this.v == null ? 0 : this.v.hashCode());
		}

		/**
		 * Modifica il valore associato alla entry con il valore passato come parametro
		 * 
		 * @param value nuovo valore per la entry
		 * @return valore sostituito della entry
		 * @throws NullPointerException se value e' un riferimento null
		 */
		public Object setValue(Object value) throws UnsupportedOperationException, ClassCastException,
				IllegalArgumentException, NullPointerException {
			if (value == null)
				throw new NullPointerException();

			Object oldv = this.v;
			this.v = value;

			return oldv;
		}

		/**
		 * Restituisce una stringa che illustra chiave e valore della entry
		 * 
		 * @return stringa illustrativa di chiave e valore della entry
		 */
		public String toString() {
			return this.k + "=" + this.v;
		}
	}

	/**
	 * <p>
	 * <b>Summary:</b> struttura dati che estende la classe
	 * {@link CollectionAdapter} e sovrascrive i metodi di rimozione per supportare
	 * il backing della mappa e delle sue strutture dati ausiliarie, in particolare
	 * della collezione valuec
	 * </p>
	 * <p>
	 * <b>Optional:</b> non sono ammessi nella collezione elementi corrispondenti a
	 * riferimenti null
	 * </p>
	 */
	private class BackedCollection extends CollectionAdapter {
		/**
		 * <p>
		 * <b>Summary:</b> iteratore che estende la classe {@link CollectionIterator} e
		 * sovrascrive i metodi {@link CollectionIterator#next()} e
		 * {@link CollectionIterator#remove()} per supportare il backing della mappa e
		 * delle sue strutture dati ausiliarie
		 * </p>
		 */
		private class BackedCollectionIterator extends CollectionIterator {
			/**
			 * Oggetto corrente riferito dall'iteratore
			 */
			private Object curr;

			/**
			 * Costruttore che inizializza l'iteratore della collezione
			 */
			public BackedCollectionIterator() {
				super();
				this.curr = null;
			}

			/**
			 * Avanza l'iteratore all'elemento successivo
			 * 
			 * @return elemento successivo dell'iteratore
			 * @throws NoSuchElementException se l'iteratore non ha un elemento successivo
			 */
			@Override
			public Object next() {
				return this.curr = super.next();
			}

			/**
			 * Rimuove l'ultimo elemento iterato dal metodo {@link HIterator#next()} e, a
			 * supporto del backing della mappa, aggiorna di conseguenza la mappa e le sue
			 * strutture dati ausiliarie
			 * 
			 * @throws IllegalStateException se prima non e' stato chiamato il metodo
			 *                               {@link HIterator#next()}
			 */
			@Override
			public void remove() {
				MapAdapter.this.removeFromCollectionIterator(this.curr);
				super.remove();
			}
		}

		/**
		 * Rimuove tutti gli elementi della collezione e, a supporto del backing della
		 * mappa, aggiorna di conseguenza la mappa e le sue strutture dati ausiliarie
		 */
		@Override
		public void clear() {
			MapAdapter.this.clearFromBackedCollection();
		}

		/**
		 * Rimuove tutti gli elementi della collezione quando la rimozione proviene
		 * dalla mappa
		 */
		public void clearFromMap() {
			super.v.clear();
		}

		/**
		 * Restituisce un iteratore della collezione
		 * 
		 * @return iteratore della collezione
		 */
		@Override
		public HIterator iterator() {
			return new BackedCollectionIterator();
		}

		/**
		 * Rimuove l'oggetto passato come parametro dalla collezione e, a supporto del
		 * backing della mappa, aggiorna di conseguenza la mappa e le sue strutture dati
		 * ausiliarie
		 * 
		 * @param o elemento rimosso dalla collezione e dalla mappa e strutture dati
		 *          ausiliarie
		 * @return true se la collezione cambia per effetto della rimozione
		 * @throws NullPointerException se o e' un riferimento null
		 */
		@Override
		public boolean remove(Object o) {
			if (o == null)
				throw new NullPointerException();

			return MapAdapter.this.removeFromBackedCollection(o);
		}

		/**
		 * Rimuove gli elementi della collezione passata come parametro dalla collezione
		 * e, a supporto del backing della mappa, aggiorna di conseguenza la mappa e le
		 * sue strutture dati ausiliarie
		 * 
		 * @param c collezione avente gli elementi da rimuovere dalla collezione e dalla
		 *          mappa e strutture dati ausiliarie
		 * @return true se la collezione cambia per effetto delle rimozioni
		 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
		 *                              almeno un elemento null
		 */
		@Override
		public boolean removeAll(HCollection c) {
			if (c == null)
				throw new NullPointerException();

			HIterator iter = c.iterator();
			while (iter.hasNext()) {
				if (iter.next() == null)
					throw new NullPointerException();
			}

			boolean change = false;
			iter = c.iterator();
			while (iter.hasNext()) {
				Object tmp = iter.next();
				if (this.remove(tmp))
					change = true;
			}

			return change;
		}

		/**
		 * Mantiene nella collezione solamente gli elementi della collezione passata
		 * come parametro e, a supporto del backing della mappa, propaga le modifiche
		 * alla mappa e alle sue strutture dati ausiliarie
		 * 
		 * @param c collezione avente gli elementi da mantenere nella collezione e nella
		 *          mappa e strutture dati ausiliarie
		 * @return true se la collezione cambia per effetto delle conservazioni
		 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
		 *                              almeno un elemento null
		 */
		@Override
		public boolean retainAll(HCollection c) {
			if (c == null)
				throw new NullPointerException();

			HIterator iter = c.iterator();
			while (iter.hasNext()) {
				if (iter.next() == null)
					throw new NullPointerException();
			}

			boolean change = false;
			HCollection rem = new CollectionAdapter();
			iter = this.iterator();
			while (iter.hasNext()) {
				Object tmp = iter.next();
				if (!c.contains(tmp)) {
					rem.add(tmp);
					change = true;
				}
			}
			this.removeAll(rem);

			return change;
		}

		/**
		 * Rimuove l'elemento passato come parametro dalla collezione quando la
		 * rimozione proviene dalla mappa
		 * 
		 * @param o elemento da rimuovere dalla collezione
		 * @return true se la collezione cambia per effetto della rimozione
		 */
		public boolean removeFromMap(Object o) {
			return super.v.remove(o);
		}
	}

	/**
	 * Rimuove l'elemento passato come parametro dalla mappa e strutture dati
	 * ausiliarie quando la rimozione proviene dalla collezione
	 * 
	 * @param o elemento da rimuovere dalla mappa e strutture dati ausiliarie
	 * @return true se la mappa cambia per effetto della rimozione
	 */
	private boolean removeFromBackedCollection(Object o) {
		boolean change = false;
		Object key = null, value = null;
		HIterator iter = this.entrys.iterator();
		while (iter.hasNext()) {
			HEntry tmp = (HEntry) iter.next();
			if (tmp.getValue().equals(o)) {
				key = tmp.getKey();
				value = this.get(key);
				change = true;
			}
		}

		if (key == null)
			return change;

		this.entrys.removeFromMap(new EntryMapAdapter(key, value));
		this.keys.removeFromMap(key);
		this.valuec.removeFromMap(value);
		this.h.remove(key);

		return change;
	}

	/**
	 * Rimuove l'elemento o dalla mappa e strutture ausiliarie quando la rimozione
	 * proviene da un iteratore della collezione della mappa
	 * 
	 * @param o elemento da rimuovere dalla mappa e strutture dati ausilarie
	 */
	private void removeFromCollectionIterator(Object o) {
		Object key = null, value = null;
		HIterator iter = this.entrys.iterator();
		while (iter.hasNext()) {
			HEntry tmp = (HEntry) iter.next();
			if (tmp.getValue().equals(o)) {
				key = tmp.getKey();
				value = this.get(key);
			}
		}

		this.entrys.removeFromMap(new EntryMapAdapter(key, value));
		this.keys.removeFromMap(key);
		this.h.remove(key);
	}

	/**
	 * Rimuove tutti gli elementi dalla mappa e strutture dati ausiliarie quando la
	 * rimozione proviene dalla collezione
	 */
	private void clearFromBackedCollection() {
		this.h.clear();
		this.entrys.clearFromMap();
		this.keys.clearFromMap();
		this.valuec.clearFromMap();
	}

	/**
	 * <p>
	 * <b>Summary:</b> struttura dati che estende la classe {@link SetAdapter} e
	 * sovrascrive i metodi di rimozione per supportare il backing della mappa e
	 * delle sue strutture dati ausiliarie, in particolare dei set entrys e keys
	 * </p>
	 * <p>
	 * <b>Optional:</b> non sono ammessi nel set elementi corrispondenti a
	 * riferimenti null
	 * </p>
	 */
	private class BackedSet extends SetAdapter {
		/**
		 * <p>
		 * <b>Summary:</b> iteratore che estende la classe {@link SetIterator} e
		 * sovrascrive i metodi {@link SetIterator#next()} e
		 * {@link SetIterator#remove()} per supportare il backing della mappa e delle
		 * sue strutture dati ausiliarie
		 * </p>
		 */
		private class BackedSetIterator extends SetIterator {
			/**
			 * Oggetto corrente riferito dall'iteratore
			 */
			private Object curr;

			/**
			 * Costruttore che inizializza l'iteratore del set
			 */
			public BackedSetIterator() {
				super();
				this.curr = null;
			}

			/**
			 * Avanza l'iteratore all'elemento successivo
			 * 
			 * @return elemento successivo dell'iteratore
			 * @throws NoSuchElementException se l'iteratore non ha un elemento successivo
			 */
			@Override
			public Object next() {
				return this.curr = super.next();
			}

			/**
			 * Rimuove l'ultimo elemento iterato dal metodo {@link HIterator#next()} e, a
			 * supporto del backing della mappa, aggiorna di conseguenza la mappa e le sue
			 * strutture dati ausiliarie
			 * 
			 * @throws IllegalStateException se prima non e' stato chiamato il metodo
			 *                               {@link HIterator#next()}
			 */
			@Override
			public void remove() {
				MapAdapter.this.removeFromSetIterator(this.curr);
				super.remove();
			}
		}

		/**
		 * Rimuove tutti gli elementi del set e, a supporto del backing della mappa,
		 * aggiorna di conseguenza la mappa e le sue strutture dati ausiliarie
		 */
		@Override
		public void clear() {
			MapAdapter.this.clearFromBackedSet();
		}

		/**
		 * Rimuove tutti gli elementi del set quando la rimozione proviene dalla mappa
		 */
		public void clearFromMap() {
			super.v.clear();
		}

		/**
		 * Restituisce un iteratore del set
		 * 
		 * @return iteratore della collezione
		 */
		@Override
		public HIterator iterator() {
			return new BackedSetIterator();
		}

		/**
		 * Rimuove l'oggetto passato come parametro dal set e, a supporto del backing
		 * della mappa, aggiorna di conseguenza la mappa e le sue strutture dati
		 * ausiliarie
		 * 
		 * @param o elemento rimosso dal set e dalla mappa e strutture dati ausiliarie
		 * @return true se il set cambia per effetto della rimozione
		 * @throws NullPointerException se o e' un riferimento null
		 */
		@Override
		public boolean remove(Object o) {
			if (o == null)
				throw new NullPointerException();

			return MapAdapter.this.removeFromBackedSet(o);
		}

		/**
		 * Rimuove gli elementi del set passato come parametro dal set e, a supporto del
		 * backing della mappa, aggiorna di conseguenza la mappa e le sue strutture dati
		 * ausiliarie
		 * 
		 * @param c set avente gli elementi da rimuovere dal set e dalla mappa e
		 *          strutture dati ausiliarie
		 * @return true se il set cambia per effetto delle rimozioni
		 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
		 *                              un elemento null
		 */
		@Override
		public boolean removeAll(HCollection c) {
			if (c == null)
				throw new NullPointerException();

			HIterator iter = c.iterator();
			while (iter.hasNext()) {
				if (iter.next() == null)
					throw new NullPointerException();
			}

			boolean change = false;
			iter = c.iterator();
			while (iter.hasNext()) {
				Object tmp = iter.next();
				if (this.remove(tmp))
					change = true;
			}

			return change;
		}

		/**
		 * Mantiene nel set solamente gli elementi del set passato come parametro e, a
		 * supporto del backing della mappa, propaga le modifiche alla mappa e alle sue
		 * strutture dati ausiliarie
		 * 
		 * @param c set avente gli elementi da mantenere nel set e nella mappa e
		 *          strutture dati ausiliarie
		 * @return true se il set cambia per effetto delle conservazioni
		 * @throws NullPointerException se c e' un riferimento null, oppure c ha almeno
		 *                              un elemento null
		 */
		@Override
		public boolean retainAll(HCollection c) {
			if (c == null)
				throw new NullPointerException();

			HIterator c_iter = c.iterator();
			while (c_iter.hasNext()) {
				if (c_iter.next() == null)
					throw new NullPointerException();
			}

			boolean change = false;
			c_iter = c.iterator();
			if (!c_iter.hasNext()) {
				change = true;
				MapAdapter.this.clear();

				return change;
			}

			HIterator iter;
			Object type = c_iter.next();
			if (type instanceof HEntry)
				iter = entrys.iterator();
			else
				iter = keys.iterator();

			HSet rem = new SetAdapter();
			while (iter.hasNext()) {
				Object tmp = iter.next();
				if (!c.contains(tmp)) {
					rem.add(tmp);
					change = true;
				}
			}
			this.removeAll(rem);

			return change;
		}

		/**
		 * Rimuove l'elemento passato come parametro dal set quando la rimozione
		 * proviene dalla mappa
		 * 
		 * @param o elemento da rimuovere dal set
		 * @return true se il set cambia per effetto della rimozione
		 */
		public boolean removeFromMap(Object o) {
			return super.v.remove(o);
		}
	}

	/**
	 * Rimuove l'elemento passato come parametro dalla mappa e strutture dati
	 * ausiliarie quando la rimozione proviene dal set
	 * 
	 * @param o elemento da rimuovere dalla mappa e strutture dati ausiliarie
	 * @return true se la mappa cambia per effetto della rimozione
	 */
	private boolean removeFromBackedSet(Object o) {
		Object key, value;
		if (o instanceof HEntry) {
			key = ((HEntry) o).getKey();
			value = ((HEntry) o).getValue();
		} else {
			key = o;
			value = this.get(key);
		}

		boolean change = false;
		if (change = this.entrys.removeFromMap(new EntryMapAdapter(key, value))) {
			this.keys.removeFromMap(key);
			this.valuec.removeFromMap(value);
			this.h.remove(key);
		}

		return change;
	}

	/**
	 * Rimuove l'elemento o dalla mappa e strutture ausiliarie quando la rimozione
	 * proviene da un iteratore di uno dei due set della mappa
	 * 
	 * @param o elemento da rimuovere dalla mappa e strutture dati ausilarie
	 */
	private void removeFromSetIterator(Object o) {
		Object key, value;
		if (o instanceof HEntry) {
			key = ((HEntry) o).getKey();
			value = ((HEntry) o).getValue();
		} else {
			key = o;
			value = this.get(key);
		}

		if (o instanceof HEntry)
			this.keys.removeFromMap(key);
		else
			this.entrys.removeFromMap(new EntryMapAdapter(key, value));

		this.valuec.removeFromMap(value);
		this.h.remove(key);
	}

	/**
	 * Rimuove tutti gli elementi dalla mappa e strutture dati ausiliarie quando la
	 * rimozione proviene dal set
	 */
	private void clearFromBackedSet() {
		this.h.clear();
		this.entrys.clearFromMap();
		this.keys.clearFromMap();
		this.valuec.clearFromMap();
	}

	/**
	 * Costruttore che inizializza una mappa vuota e le relative strutture dati
	 * ausiliarie
	 */
	public MapAdapter() {
		this.h = new Hashtable();
		this.entrys = new BackedSet();
		this.keys = new BackedSet();
		this.valuec = new BackedCollection();
	}

	/**
	 * Costruttore che inizializza la mappa con gli elementi della mappa
	 * passata come parametro
	 * 
	 * @param t mappa avente gli elementi che inizializzano la mappa
	 * @throws NullPointerException se t e' un riferimento null, oppure se t ha
	 *                              almeno una entry chiave-valore con chiave o
	 *                              valore null
	 */
	public MapAdapter(HMap t) {
		this();
		this.putAll(t);
	}

	/**
	 * Rimuove tutti gli elementi della mappa e dalle strutture dati ausiliarie
	 */
	public void clear() {
		this.h.clear();
		this.entrys.clearFromMap();
		this.keys.clearFromMap();
		this.valuec.clearFromMap();
	}

	/**
	 * Verifica se la mappa contiene una entry chiave-valore avente come chiave
	 * quella passata come parametro
	 * 
	 * @param key chiave per cui si verifica la presenza nella mappa
	 * @return true se la chiave e' contenuta nella mappa
	 * @throws NullPointerException se key e' un riferimento null
	 */
	public boolean containsKey(Object key) {
		if (key == null)
			throw new NullPointerException();

		return this.h.containsKey(key);
	}

	/**
	 * Verifica se la mappa contiene una entry chiave-valore avente come valore
	 * quello passato come parametro
	 * 
	 * @param value valore per cui si verifica la presenza nella mappa
	 * @return true se il valore e' contenuto nella mappa
	 * @throws NullPointerException se value e' un riferimento null
	 */
	public boolean containsValue(Object value) {
		if (value == null)
			throw new NullPointerException();

		return this.h.contains(value);
	}

	/**
	 * Restituisce la struttura dati ausiliaria set che memorizza tutte le entry
	 * chiave-valore contenute nella mappa
	 * 
	 * @return set delle entry chiave-valore della mappa
	 */
	public HSet entrySet() {
		return this.entrys;
	}

	/**
	 * Verifica se la mappa e' uguale all'oggetto passato come parametro, ossia se
	 * ha stesso tipo, dimensione ed entry chiave-valore
	 * 
	 * @param o oggetto corrispondente alla mappa con cui avviene il confronto
	 * @return true se la mappa e' uguale all'oggetto o
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof HMap))
			return false;

		HSet es = ((HMap) o).entrySet();

		return this.entrys.equals(es);
	}

	/**
	 * Restituisce il valore associato alla entry chiave-valore della mappa con
	 * chiave passata come parametro
	 * 
	 * @param key chiave associata a una entry chiave-valore della mappa
	 * @return valore associato alla entry chiave-valore della mappa avente chiave
	 *         key
	 */
	public Object get(Object key) {
		return this.h.get(key);
	}

	/**
	 * Restituisce il codice hash della mappa
	 * 
	 * @return codice hash della mappa
	 */
	public int hashCode() {
		int hash = 0;
		HIterator iter = this.entrys.iterator();
		while (iter.hasNext())
			hash += iter.next().hashCode();

		return hash;
	}

	/**
	 * Verifica se la mappa e' vuota
	 * 
	 * @return true se la mappa e' vuota
	 */
	public boolean isEmpty() {
		return this.h.isEmpty();
	}

	/**
	 * Restituisce la struttura dati ausiliaria set che memorizza tutte le chiavi
	 * contenute nella mappa
	 * 
	 * @return set delle chiavi della mappa
	 */
	public HSet keySet() {
		return this.keys;
	}

	/**
	 * Aggiunge alla mappa una entry chiave-valore specificata dai parametri key e
	 * value e aggiorna le sue strutture dati ausiliarie
	 * 
	 * @param key   chiave della entry chiave-valore da aggiungere alla mappa
	 * @param value valore della entry chiave-valore da aggiungere alla mappa
	 * @return valore precedentemente associato alla chiave key, se gia presente
	 *         nella mappa, altrimenti null
	 * @throws NullPointerException se key o value e' un riferimento null
	 */
	public Object put(Object key, Object value) {
		boolean newk = false, newv = false;
		Object oldv = this.h.put(key, value);
		if (oldv == null) {
			newk = newv = true;
		} else if (oldv != null && !oldv.equals(value)) {
			newv = true;
		}

		if (newk) {
			this.entrys.add(new EntryMapAdapter(key, value));
			this.keys.add(key);
			this.valuec.add(value);
		} else if (!newk && newv) {
			HIterator iter = this.entrys.iterator();
			while (iter.hasNext()) {
				HEntry en = (HEntry) iter.next();
				if (en.getKey().equals(key))
					en.setValue(value);
			}
			this.valuec.removeFromMap(oldv);
			this.valuec.add(value);
		}

		return oldv;
	}

	/**
	 * Aggiunge alla mappa le entry chiave-valore della mappa specificata come
	 * parametro e aggiorna le sue strutture dati ausiliarie
	 * 
	 * @param t mappa contente le entry chiave-valore da aggiungere alla mappa
	 *          corrente
	 * @throws NullPointerException se t e' un riferimento null, oppure t ha almeno
	 *                              una entry chiave-valore con chiave o valore null
	 */
	public void putAll(HMap t) {
		if (t == null)
			throw new NullPointerException();

		HSet es = t.entrySet();
		HIterator iter = es.iterator();
		while (iter.hasNext()) {
			HEntry tmp = (HEntry) iter.next();
			if (tmp.getKey() == null || tmp.getValue() == null)
				throw new NullPointerException();
		}

		iter = es.iterator();
		while (iter.hasNext()) {
			HEntry tmp = (HEntry) iter.next();
			this.put(tmp.getKey(), tmp.getValue());
		}
	}

	/**
	 * Rimuove dalla mappa la entry chiave-valore avente la chiave passata come
	 * parametro e, a supporto del backing della mappa, aggiorna di conseguenza la
	 * mappa e le sue strutture dati ausiliarie
	 * 
	 * @param key chiave relativa alla entry chiave-valore da rimuovere dalla mappa
	 *            e dalle strutture dati ausiliarie
	 * @return valore associato alla entry chiave-valore rimossa
	 * @throws NullPointerException se key e' un riferimento null
	 */
	public Object remove(Object key) {
		if (key == null)
			throw new NullPointerException();

		Object value = this.get(key), change = null;
		if (this.entrys.removeFromMap(new EntryMapAdapter(key, value))) {
			this.keys.removeFromMap(key);
			this.valuec.removeFromMap(value);
			change = this.h.remove(key);
		}

		return change;
	}

	/**
	 * Restituisce il numero di entry chiave-valore contenute nella mappa
	 * 
	 * @return numero di entry chiave-valore contenute nella mappa
	 */
	public int size() {
		if (this.h.size() > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		return this.h.size();
	}

	/**
	 * Restituisce la struttura dati ausiliaria collezione che memorizza tutti i
	 * valori contenuti nella mappa
	 * 
	 * @return collezione dei valori della mappa
	 */
	public HCollection values() {
		return this.valuec;
	}

	/**
	 * Restituisce una stringa che illustra le entry chiave-valore contenute nella
	 * mappa
	 * 
	 * @return stringa illustrativa delle entry chiave-valore contenuti nella mappa
	 */
	public String toString() {
		String h_str = this.h.toString();

		return "[" + h_str.substring(1, h_str.length() - 1) + "]";
	}
}