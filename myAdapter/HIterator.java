package myAdapter;

import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary:</b> interfaccia avente i metodi comuni per l'utilizzo di
 * iteratori
 * </p>
 */
public interface HIterator {
	/**
	 * Verifica se l'iteratore ha un elemento successivo
	 * 
	 * @return true se l'iteratore ha un elemento successivo
	 */
	boolean hasNext();

	/**
	 * Avanza l'iteratore all'elemento successivo
	 * 
	 * @return elemento successivo dell'iteratore
	 * @throws NoSuchElementException se l'iteratore non ha un elemento successivo
	 */
	Object next() throws NoSuchElementException;

	/**
	 * Rimuove l'ultimo elemento iterato dal metodo {@link HIterator#next()}
	 * 
	 * @throws IllegalStateException se prima non e' stato chiamato il metodo
	 *                               {@link HIterator#next()}
	 */
	void remove() throws UnsupportedOperationException, IllegalStateException;
}