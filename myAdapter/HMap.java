package myAdapter;

/**
 * <p>
 * <b>Summary:</b> interfaccia avente i metodi comuni per la manipolazione di
 * mappe
 * </p>
 */
public interface HMap {
	/**
	 * Rimuove tutti gli elementi della mappa e dalle strutture dati ausiliarie
	 */
	void clear() throws UnsupportedOperationException;

	/**
	 * Verifica se la mappa contiene una entry chiave-valore avente come chiave
	 * quella passata come parametro
	 * 
	 * @param key chiave per cui si verifica la presenza nella mappa
	 * @return true se la chiave e' contenuta nella mappa
	 * @throws NullPointerException se key e' un riferimento null (opzionale)
	 */
	boolean containsKey(Object key) throws ClassCastException, NullPointerException;

	/**
	 * Verifica se la mappa contiene una entry chiave-valore avente come valore
	 * quello passato come parametro
	 * 
	 * @param value valore per cui si verifica la presenza nella mappa
	 * @return true se il valore e' contenuto nella mappa
	 * @throws NullPointerException se value e' un riferimento null (opzionale)
	 */
	boolean containsValue(Object value) throws ClassCastException, NullPointerException;

	/**
	 * Restituisce la struttura dati ausiliaria set che memorizza tutte le entry
	 * chiave-valore contenute nella mappa
	 * 
	 * @return set delle entry chiave-valore della mappa
	 */
	HSet entrySet();

	/**
	 * Verifica se la mappa e' uguale all'oggetto passato come parametro, ossia se
	 * ha stesso tipo, dimensione ed entry chiave-valore
	 * 
	 * @param o oggetto corrispondente alla mappa con cui avviene il confronto
	 * @return true se la mappa e' uguale all'oggetto o
	 */
	boolean equals(Object o);

	/**
	 * Restituisce il valore associato alla entry chiave-valore della mappa con
	 * chiave passata come parametro
	 * 
	 * @param key chiave associata a una entry chiave-valore della mappa
	 * @return valore associato alla entry chiave-valore della mappa avente chiave
	 *         key
	 */
	Object get(Object key) throws ClassCastException, NullPointerException;

	/**
	 * Restituisce il codice hash della mappa
	 * 
	 * @return codice hash della mappa
	 */
	int hashCode();

	/**
	 * Verifica se la mappa e' vuota
	 * 
	 * @return true se la mappa e' vuota
	 */
	boolean isEmpty();

	/**
	 * Restituisce la struttura dati ausiliaria set che memorizza tutte le chiavi
	 * contenute nella mappa
	 * 
	 * @return set delle chiavi della mappa
	 */
	HSet keySet();

	/**
	 * Aggiunge alla mappa una entry chiave-valore specificata dai parametri key e
	 * value e aggiorna le sue strutture dati ausiliarie
	 * 
	 * @param key   chiave della entry chiave-valore da aggiungere alla mappa
	 * @param value valore della entry chiave-valore da aggiungere alla mappa
	 * @return valore precedentemente associato alla chiave key, se gia presente
	 *         nella mappa, altrimenti null
	 * @throws NullPointerException se key o value e' un riferimento null
	 *                              (opzionale)
	 */
	Object put(Object key, Object value)
			throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException;

	/**
	 * Aggiunge alla mappa le entry chiave-valore della mappa specificata come
	 * parametro e aggiorna le sue strutture dati ausiliarie
	 * 
	 * @param t mappa contente le entry chiave-valore da aggiungere alla mappa
	 *          corrente
	 * @throws NullPointerException se t e' un riferimento null, oppure t ha almeno
	 *                              una entry chiave-valore con chiave o valore null
	 *                              (opzionale)
	 */
	void putAll(HMap t)
			throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException;

	/**
	 * Rimuove dalla mappa la entry chiave-valore avente la chiave passata come
	 * parametro e, a supporto del backing della mappa, aggiorna di conseguenza la
	 * mappa e le sue strutture dati ausiliarie
	 * 
	 * @param key chiave relativa alla entry chiave-valore da rimuovere dalla mappa
	 *            e dalle strutture dati ausiliarie
	 * @return valore associato alla entry chiave-valore rimossa
	 * @throws NullPointerException se key e' un riferimento null (opzionale)
	 */
	Object remove(Object key) throws UnsupportedOperationException, ClassCastException, NullPointerException;

	/**
	 * Restituisce il numero di entry chiave-valore contenute nella mappa
	 * 
	 * @return numero di entry chiave-valore contenute nella mappa
	 */
	int size();

	/**
	 * Restituisce la struttura dati ausiliaria collezione che memorizza tutti i
	 * valori contenuti nella mappa
	 * 
	 * @return collezione dei valori della mappa
	 */
	HCollection values();

	/**
	 * Restituisce una stringa che illustra le entry chiave-valore contenute nella
	 * mappa
	 * 
	 * @return stringa illustrativa delle entry chiave-valore contenuti nella mappa
	 */
	String toString();
}