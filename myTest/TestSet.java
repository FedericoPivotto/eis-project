package myTest;

import myAdapter.*;

import java.util.NoSuchElementException;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * <p>
 * <b>Summary:</b> testa il corretto funzionamento dei metodi del set realizzato
 * dalla classe {@link SetAdapter}, la quale implementa l'interfaccia
 * {@link HSet}
 * </p>
 * <p>
 * <b>Test Suite Design:</b> testa ciascuno metodo dichiarato nell'interfaccia
 * {@link HSet} per l'implementazione della classe SetAdapter, avvalendosi di
 * set
 * ausiliari costruiti appositamente per semplificare la verifica delle
 * asserzioni e che vengono ripristinati prima dell'avvio di ciascun metodo di
 * test
 * </p>
 */
public class TestSet {
	/**
	 * Set ausiliari per l'esecuzione dei test unitari
	 */
	private HSet head, param, st;

	/**
	 * <p>
	 * <b>Summary:</b> inizializza un set ausiliario per i test avente un elemento
	 * </p>
	 */
	@Before
	public void setUp_head() {
		head = new SetAdapter();
		head.add("Set Adapter");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza un set ausiliario per i test avente tre elementi
	 * </p>
	 */
	@Before
	public void setUp_param() {
		param = new SetAdapter();
		param.add("param_1");
		param.add("param_2");
		param.add("param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza un set ausiliario per i test avente quattro
	 * elementi
	 * </p>
	 */
	@Before
	public void setUp_st() {
		st = new SetAdapter();
		st.add("Set Adapter");
		st.add("param_1");
		st.add("param_2");
		st.add("param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#add(Object o)}, che aggiunge un
	 * elemento al
	 * set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento sia stato aggiunto al set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#add(Object o)} sul
	 * set st per aggiungere l'elemento passato come parametro e verifica che il
	 * valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale del set st
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st modificata con l'aggiunta di un elemento
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta dell'elemento specificato come parametro al
	 * set st
	 * </p>
	 * 
	 * @see myAdapter.HSet#add(Object o)
	 */
	@Test
	public void add_o() {
		boolean res = st.add("add(Object o)");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#addAll(HCollection c)}, che
	 * aggiunge gli
	 * elementi del set specificato come parametro al set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi del set passato come
	 * parametro siano stati aggiunti al set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#addAll(HCollection c)} sul set st per aggiungere gli elementi del
	 * set param passato come
	 * parametro e verifica che il valore booleano ritornato sia true mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale del set st
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st modificato con l'aggiunta degli elementi del
	 * set param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta degli elementi del set param specificato
	 * come parametro al set st
	 * </p>
	 * 
	 * @see myAdapter.HSet#addAll(HCollection c)
	 */
	@Test
	public void addAll_c() {
		boolean res = head.addAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#clear()}, che rimuove tutti gli
	 * elementi del
	 * set st
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi del set siano stati tutti
	 * rimossi
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HSet#clear()}
	 * sul set st e
	 * verifica che il valore booleano ritornato dalla chiamata del metodo
	 * {@link HSet#isEmpty()}
	 * sul set st sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st con almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st vuota
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi dal set st
	 * </p>
	 * 
	 * @see myAdapter.HSet#clear()
	 */
	@Test
	public void clear() {
		st.clear();
		Assert.assertTrue(st.isEmpty());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#contains(Object o)}, che verifica
	 * la presenza
	 * dell'elemento o nel set st
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento passato come parametro sia
	 * effetivamente contenuto nel set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#contains(Object o)}
	 * sul set st e verifica che il valore booleano ritornato sia true mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene l'elemento da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nel set st dell'elemento passato come
	 * parametro
	 * </p>
	 * 
	 * @see myAdapter.HSet#contains(Object o)
	 */
	@Test
	public void contains_o() {
		boolean res = st.contains("Set Adapter");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#containsAll(HCollection c)}, che
	 * verifica la
	 * presenza degli elementi del set c nel set st
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi del set passato come
	 * parametro siano effetivamente contenuti nel set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#containsAll(HCollection c)} sul set st e verifica che il valore
	 * booleano
	 * ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene gli elementi da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nel set st di tutti gli elementi del set
	 * passato come parametro
	 * </p>
	 * 
	 * @see myAdapter.HSet#containsAll(HCollection c)
	 */
	@Test
	public void containsAll_c() {
		boolean res = st.containsAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#equals(Object o)}, che verifica
	 * l'uguaglianza
	 * del set st con un'altra fornita come parametro
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi del set siano gli stessi,
	 * in uguale numero
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce il set tmp utilizzato come parametro per
	 * effettuare la chiamata del metodo {@link HSet#equals(Object o)} e verifica
	 * che il valore
	 * booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione st e collezione costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> set st effettivamente uguale al set costrutito tmp
	 * </p>
	 * 
	 * @see myAdapter.HSet#equals(Object o)
	 */
	@Test
	public void equals_o() {
		HSet tmp = new SetAdapter();
		tmp.add("Set Adapter");
		tmp.add("param_1");
		tmp.add("param_2");
		tmp.add("param_3");

		boolean res = st.equals(tmp) && tmp.equals(st);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#hashCode()}, che calcola il
	 * codice hash del
	 * set su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica l'uguaglianza del codice hash di due set
	 * uguali
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce il set tmp e verifica che il codice hash
	 * calcolato sia uguale a quello del set st mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st e set costruito tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> codice hash del set st effettivamente uguale quello
	 * del set costrutito tmp
	 * </p>
	 * 
	 * @see myAdapter.HSet#hashCode()
	 */
	@Test
	public void hash_Code() {
		HSet tmp = new SetAdapter();
		tmp.add("Set Adapter");
		tmp.add("param_1");
		tmp.add("param_2");
		tmp.add("param_3");

		boolean res = st.hashCode() == tmp.hashCode();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#isEmpty()}, che verifica se il
	 * set su cui si
	 * effettua la chiamata e' vuoto
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che il set st, dopo il vuotamento, e' vuoto
	 * </p>
	 * <p>
	 * <b>Test Description:</b> vuoto il set st, chiama il metodo
	 * {@link HSet#isEmpty()} e
	 * verifica che il valore booleano ritoranto sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st vuota e valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> set st e' privo di elementi
	 * </p>
	 * 
	 * @see myAdapter.HSet#isEmpty()
	 */
	@Test
	public void isEmpty() {
		st.clear();

		boolean res = st.isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#iterator()}, per cui si verifica
	 * il corretto
	 * funzionamento dell'iterazione sul set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che il set costruito con l'iteratore e'
	 * uguale al set da cui lo si ha estratto
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottiene un iteratore chiamando il metodo
	 * {@link HSet#iterator()}
	 * sul set st, esegue l'iterazione dell'intero set st costruendo il set tmp e
	 * infine verifica se il set st e' uguale al set tmp
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st uguale al set tmp
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione del set st eseguita correttamente
	 * </p>
	 * 
	 * @see myAdapter.HSet#iterator()
	 */
	@Test
	public void iterator() {
		HSet tmp = new SetAdapter();
		HIterator iter = st.iterator();
		while (iter.hasNext())
			tmp.add(iter.next());

		Assert.assertEquals(st, tmp);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#remove(Object o)}, che rimuove un
	 * elemento dal
	 * set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento o sia stato rimosso dal set
	 * st
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#remove(Object o)} sul
	 * set st per passando come parametro l'elemento o da rimuovere e verifica che
	 * il valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st modificata con la rimozione dell'elemento o
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione dal set st dell'elemento o specificato
	 * come parametro
	 * </p>
	 * 
	 * @see myAdapter.HSet#remove(Object o)
	 */
	@Test
	public void remove_o() {
		Object o = "Set Adapter";

		boolean res = st.remove(o);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#removeAll(HCollection c)}, che
	 * rimuove gli
	 * elementi del set specificata come parametro dal set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi del set param passato come
	 * parametro siano stati rimossi dal set st
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#removeAll(HCollection c)}
	 * sul set st passando come parametro il set param che specifica gli elementi da
	 * rimuovere e verifica che il valore booleano ritornato sia true mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st modificato con la rimozione degli elementi
	 * presenti nel set param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione degli elementi del set param specificato
	 * come parametro dal set st
	 * </p>
	 * 
	 * @see myAdapter.HSet#removeAll(HCollection c)
	 */
	@Test
	public void removeAll_c() {
		boolean res = st.removeAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#retainAll(HCollection c)}, che
	 * mantiene nel
	 * set solamente gli elementi comuni con il set specificato come parametro del
	 * metodo
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che siano stati mantenuti nel set st
	 * solamente gli elementi presenti nel set param passato come parametro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} sul set st passando come parametro il
	 * set param che
	 * specifica gli elementi da mantenere e verifica che il valore booleano
	 * ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set st modificato con la rimozione degli elementi non
	 * presenti nel set param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione dal set st degli elementi non presenti nel
	 * set param specificato come parametro
	 * </p>
	 * 
	 * @see myAdapter.HSet#retainAll(HCollection c)
	 */
	@Test
	public void retainAll_c() {
		boolean res = st.retainAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#size()}, che restituisce il
	 * numero di elementi
	 * presenti nel set su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che il set st abbia size uguale a quattro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HSet#size()}
	 * sul set st e
	 * verifica che il valore intero ritornato sia uguale a quattro mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> size uguale a quattro
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione del numero di elementi del set st,
	 * uguale quattro
	 * </p>
	 * 
	 * @see myAdapter.HSet#size()
	 */
	@Test
	public void size() {
		int size = st.size();
		Assert.assertEquals(4, size);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#toArray()}, che verifica se
	 * l'array di oggetti
	 * contiene effettivamente gli stessi elementi del set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'array di oggetti tmp contiene gli
	 * stessi elementi del set head, composto da un solo elemento
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata al metodo
	 * {@link HSet#toArray()} sul set
	 * head per ottenere l'array di oggetti corrispondente e verifica che l'unico
	 * elemento presente sia uguale all'unico elemento presente nel set head
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set head contiene un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> array di oggetti tmp contiene gli stessi elementi del
	 * set head e i valori booleani size e content sono true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> ottenimento di un array di oggetti contenuti nel set
	 * head
	 * </p>
	 * 
	 * @see myAdapter.HSet#toArray()
	 */
	@Test
	public void toArray() {
		Object[] tmp = head.toArray();
		boolean size = tmp.length == 1;
		boolean content = tmp[0].equals("Set Adapter");
		Assert.assertTrue(size && content);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HSet#toArray(Object[] a)}, che
	 * verifica se l'array
	 * di oggetti passato come parametro viene effettivamente riempito con gli
	 * stessi elementi del set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'array di oggetti a passato come
	 * parametro venga riempito con gli stessi elementi del set head, composto da un
	 * solo elemento
	 * </p>
	 * <p>
	 * <b>Test Description:</b> istanzia un array di dieci oggetti, effettua la
	 * chiamata del metodo {@link HSet#toArray(Object[] a)} sul set head per
	 * ottenere l'array a
	 * riempito con gli elementi del set head e verifica che l'unico elemento
	 * presente sia uguale all'unico elemento presente nel set head mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set head contiene un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> array di oggetti a contiene gli stessi elementi del
	 * set head, dunque ha un solo elemento, e i valori booleani size e content sono
	 * true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> riempimento dell'array di oggetti a con gli elementi
	 * contenuti nel set head
	 * </p>
	 * 
	 * @see myAdapter.HSet#toArray(Object[] a)
	 */
	@Test
	public void toArray_a() {
		Object[] a = new Object[10];
		a = head.toArray(a);
		boolean size = a.length == 1;
		boolean content = a[0].equals("Set Adapter");
		Assert.assertTrue(size && content);
	}

	/**
	 * Seguono ulteriori metodi di test del funzionamento del set
	 */

	/**
	 * <p>
	 * <b>Summary:</b> verifica che il set rifiuti l'aggiunta di elementi duplicati
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo la creazione di un nuovo set tmp avente gli
	 * stessi elementi del set st e l'aggiunta di un elemento duplicato, si
	 * confrontano il numero di elementi di ciascun set mediante asserzione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore, crea un nuovo set tmp
	 * riempito con gli elementi del set st e un elemento duplicato, e verifica che
	 * il numero di elementi del set st sia uguale a quello della collezione tmp,
	 * ossia che non sia stato inserito l'elemento duplicato
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> st.size() uguale a tmp.size()
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rifiuto dei duplicati nel set
	 * </p>
	 */
	@Test
	public void refuseDuplicate() {
		HCollection tmp = new SetAdapter();
		tmp.add("Set Adapter");
		HIterator iter = st.iterator();
		while (iter.hasNext())
			tmp.add(iter.next());

		Assert.assertEquals(st.size(), tmp.size());
	}

	/**
	 * Seguono i metodi di test per la classe SetIterator che implementa
	 * l'interfaccia HIterator
	 */

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#hasNext()} di
	 * {@link HIterator} per un set, che
	 * verifica se effettivamente un iteratore ha un elemento successivo
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> itera tutta il set mediante un iteratore e verifica
	 * che la dimensione calcolata sia uguale alla dimensione del set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sul set ct, effettua
	 * ripetute chiamate del metodo {@link HIterator#next()} e incrementi di size
	 * fintanto che
	 * {@link HIterator#hasNext()} restituisce true, e verifica, mediante
	 * asserzione, che il set st ha
	 * dimensione uguale a size
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> st.size() uguale a size
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione completa del set st senza lanciare
	 * eccezioni
	 * </p>
	 * 
	 * @see myAdapter.HIterator#hasNext()
	 */
	@Test
	public void setIterator_hasNext() {
		int size = 0;
		HIterator iter = st.iterator();
		while (iter.hasNext()) {
			iter.next();
			size++;
		}

		Assert.assertEquals(st.size(), size);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#next()} di {@link HIterator}
	 * per un set, che verifica
	 * se effettivamente l'iteratore esegue avanzamenti sugli elementi del set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> itera tutto il set mediante un iteratore e verifica
	 * che gli elementi iterati siano tutti parte del set
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sul set st, effettua
	 * ripetute chiamate dei metodo {@link HIterator#next()} e
	 * {@link HSet#contains(Object o)} fintanto che
	 * {@link HIterator#hasNext()} restituisce true, e verifica, mediante
	 * asserzione, che l'iterazione
	 * e' avvenuta solo su elementi del set st
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione completa del set st senza lanciare
	 * eccezioni
	 * </p>
	 * 
	 * @see myAdapter.HIterator#next()
	 */
	@Test
	public void setIterator_next() {
		boolean res = true;
		HIterator iter = st.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (!st.contains(obj))
				res = false;
		}

		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#next()} di {@link HIterator}
	 * per un set, per cui si
	 * verifica, utilizzando un iteratore del set, l'effettivo lancio di un
	 * eccezione in caso di avanzamento mediante {@link HIterator#next()} dopo aver
	 * gia' iterato
	 * l'intero set
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che chiamate del metodo
	 * {@link HIterator#next()} successive
	 * all'iterazione dell'intero set lanciano l'eccezione
	 * {@link java.util.NoSuchElementException}
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottiene un iteratore chiamando il metodo
	 * {@link HSet#iterator()}
	 * sul set st, esegue un'iterazione completa del set st mediante ripetute
	 * chiamate del metodo {@link HIterator#next()}, e di cui ne viene eseguita una
	 * ulteriore chiamata
	 * per scaturire il lancio dell'eccezione NoSuchElementException
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> eccezione {@link java.util.NoSuchElementException}
	 * lanciata
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> lancio dell'eccezione
	 * {@link java.util.NoSuchElementException} dopo
	 * l'ulteriore chiamata del metodo {@link HIterator#next()} sull'iteratore
	 * </p>
	 * 
	 * @see myAdapter.HIterator#next()
	 */
	@Test(expected = NoSuchElementException.class)
	public void setIterator_next_NoSuchElementException() {
		HIterator iter = st.iterator();
		while (iter.hasNext())
			iter.next();
		iter.next();
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per un set, che
	 * verifica il funzionamento della rimozione di elementi del set mediante un
	 * iteratore
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> effettua la rimozione di tutti gli elementi del set
	 * mediante un iteratore e verifica che questa sia vuota
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sul set st, rimuove tutti
	 * gli elementi in essa contenuti effettuando ripetute chiamate del metodo
	 * {@link HIterator#remove()} finche' vi sono ancora elementi nel set st, e
	 * verifica, mediante
	 * asserzione, che il set st sia stato vuotato controllando il valore booleano
	 * restituito dalla chiamata del metodo {@link HSet#isEmpty()} sul set st
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> set vuoto
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi del set st mediante
	 * l'uso di un iteratore
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test
	public void setIterator_remove() {
		HIterator iter = st.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		boolean res = st.isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per un set, per cui si
	 * verifica, utilizzando un iteratore del set, l'effettivo lancio di un
	 * eccezione in caso di rimozione mediante {@link HIterator#remove()} senza
	 * prima aver chiamato il
	 * metodo {@link HIterator#next()}
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la seconda chiamata del metodo
	 * {@link HIterator#remove()}
	 * sull'iteratore lancia l'eccezione {@link IllegalStateException}
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottengo un iteratore chiamando il metodo
	 * {@link HSet#iterator()}
	 * sul set st, e su esso si esegue una chiamata del metodo next() e due
	 * consecutive del metodo {@link HIterator#remove()} per scaturire il lancio
	 * dell'eccezione
	 * {@link IllegalStateException}
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> set st contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> eccezione {@link IllegalStateException} lanciata
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> lancio dell'eccezione {@link IllegalStateException}
	 * dopo la
	 * seconda chiamata del metodo {@link HIterator#remove()} sull'iteratore
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test(expected = IllegalStateException.class)
	public void setIterator_remove_IllegalStateException() {
		HIterator iter = st.iterator();
		if (iter.hasNext())
			iter.next();
		iter.remove();
		iter.remove();
	}
}