package myAdapter;

/**
 * <p>
 * <b>Summary:</b> interfaccia avente i metodi comuni per la manipolazione di
 * collezioni
 * </p>
 */
public interface HCollection {
	/**
	 * Aggiunge l'oggetto passato come parametro alla collezione
	 * 
	 * @param o elemento aggiunto alla collezione
	 * @return true se la collezione cambia per effetto dell'aggiunta
	 * @throws NullPointerException se o e' un riferimento null (opzionale)
	 */
	boolean add(Object o)
			throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException;

	/**
	 * Aggiunge gli elementi della collezione passata come parametro alla collezione
	 * 
	 * @param c collezione avente gli elementi da aggiungere alla collezione
	 * @return true se la collezione cambia per effetto delle aggiunte
	 * @throws NullPointerException se c e' riferimento null, oppure se c ha almeno
	 *                              un elemento null, oppure c ha almeno un elemento
	 *                              null (opzionale)
	 */
	boolean addAll(HCollection c)
			throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException;

	/**
	 * Rimuove tutti gli elementi della collezione
	 */
	void clear() throws UnsupportedOperationException;

	/**
	 * Verifica se la collezione contiene l'oggetto passato come parametro
	 * 
	 * @param o elemento per cui si verifica la presenza nella collezione
	 * @return true se l'elemento e' contenuto nella collezione
	 * @throws NullPointerException se o e' un riferimento null (opzionale)
	 */
	boolean contains(Object o) throws ClassCastException, NullPointerException;

	/**
	 * Verifica se la collezione contiene gli elementi della collezione passata come
	 * parametro
	 * 
	 * @param c collezione degli elementi per cui si verifica la presenza nella
	 *          collezione
	 * @return true se gli elementi della collezione c sono tutti contenuti nella
	 *         collezione
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null, oppure c ha almeno un
	 *                              elemento null (opzionale)
	 */
	boolean containsAll(HCollection c) throws ClassCastException, NullPointerException;

	/**
	 * Verifica se la collezione e' uguale all'oggetto passato come parametro, ossia
	 * se ha stesso tipo, dimensione, elementi e ordine
	 * 
	 * @param o oggetto corrispondente alla collezione con cui avviene il confronto
	 * @return true se la collezione e' uguale all'oggetto o
	 */
	boolean equals(Object o);

	/**
	 * Restituisce il codice hash della collezione
	 * 
	 * @return codice hash della collezione
	 */
	int hashCode();

	/**
	 * Verifica se la collezione e' vuota
	 * 
	 * @return true se la collezione e' vuota
	 */
	boolean isEmpty();

	/**
	 * Restituisce un iteratore della collezione
	 * 
	 * @return iteratore della collezione
	 */
	HIterator iterator();

	/**
	 * Rimuove l'oggetto passato come parametro dalla collezione
	 * 
	 * @param o elemento rimosso dalla collezione
	 * @return true se la collezione cambia per effetto della rimozione
	 * @throws NullPointerException se o e' un riferimento null (opzionale)
	 */
	boolean remove(Object o) throws UnsupportedOperationException, ClassCastException, NullPointerException;

	/**
	 * Rimuove gli elementi della collezione passata come parametro dalla collezione
	 * 
	 * @param c collezione avente gli elementi da rimuovere dalla collezione
	 * @return true se la collezione cambia per effetto delle rimozioni
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null, oppure c ha almeno un
	 *                              elemento null (opzionale)
	 */
	boolean removeAll(HCollection c) throws UnsupportedOperationException, ClassCastException, NullPointerException;

	/**
	 * Mantiene nella collezione solamente gli elementi della collezione passata
	 * come parametro
	 * 
	 * @param c collezione avente gli elementi da mantenere nella collezione
	 * @return true se la collezione cambia per effetto delle conservazioni
	 * @throws NullPointerException se c e' un riferimento null, oppure se c ha
	 *                              almeno un elemento null, oppure c ha almeno un
	 *                              elemento null (opzionale)
	 */
	boolean retainAll(HCollection c) throws UnsupportedOperationException, ClassCastException, NullPointerException;

	/**
	 * Restituisce il numero di elementi contenuti nella collezione
	 * 
	 * @return numero di elementi contenuti nella collezione
	 */
	int size();

	/**
	 * Restituisce un array con tutti gli oggetti contenuti nella collezione
	 * 
	 * @return array di oggetti della collezione
	 */
	Object[] toArray();

	/**
	 * Riempie l'array di oggetti passato come parametro con gli elementi contenuti
	 * nella collezione
	 * 
	 * @param a array di oggetti da riempire
	 * @return array di oggetti della collezione
	 * @throws NullPointerException se a e' un riferimento null (opzionale)
	 */
	Object[] toArray(Object[] a) throws ArrayStoreException, NullPointerException;

	/**
	 * Restituisce una stringa che illustra gli elementi contenuti nella collezione
	 * 
	 * @return stringa illustrativa degli elementi contenuti nella collezione
	 */
	String toString();
}