package myAdapter;

/**
 * <p>
 * <b>Summary:</b> interfaccia avente i metodi comuni per la manipolazione di
 * entry chiave-valore
 * </p>
 */
public interface HEntry {
	/**
	 * Verifica se la entry e' uguale all'oggetto passato come parametro, ossia se
	 * ha stesso tipo, chiave e valore
	 * 
	 * @param o oggetto corrispondente alla entry con cui avviene il confronto
	 * @return true se la entry e' uguale all'oggetto o
	 */
	boolean equals(Object o);

	/**
	 * Restituisce la chiave associata alla entry
	 * 
	 * @return chiave associata alla entry
	 */
	Object getKey();

	/**
	 * Restituisce il valore associato alla entry
	 * 
	 * @return valore associato alla entry
	 */
	Object getValue();

	/**
	 * Restituisce il codice hash della entry
	 * 
	 * @return codice hash della entry
	 */
	int hashCode();

	/**
	 * Modifica il valore associato alla entry con il valore passato come parametro
	 * 
	 * @param value nuovo valore per la entry
	 * @return valore sostituito della entry
	 * @throws NullPointerException se value e' un riferimento null (opzionale)
	 */
	Object setValue(Object value)
			throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException;

	/**
	 * Restituisce una stringa che illustra chiave e valore della entry
	 * 
	 * @return stringa illustrativa di chiave e valore della entry
	 */
	String toString();
}