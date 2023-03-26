package myTest;

import myAdapter.*;

import java.util.NoSuchElementException;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * <p>
 * <b>Summary:</b> testa il corretto funzionamento dei metodi della collezione
 * realizzata dalla classe {@link CollectionAdapter}, la quale implementa
 * l'interfaccia
 * {@link HCollection}
 * </p>
 * <p>
 * <b>Test Suite Design:</b> testa ciascuno metodo dichiarato nell'interfaccia
 * {@link HCollection} per l'implementazione della classe
 * {@link CollectionAdapter}, avvalendosi
 * di collezioni ausiliarie costruite appositamente per semplificare la verifica
 * delle asserzioni e che vengono ripristinate prima dell'avvio di ciascun
 * metodo di test
 * </p>
 */
public class TestCollection {
	/**
	 * Collezioni ausiliarie per l'esecuzione dei test unitari
	 */
	private HCollection head, param, ct;

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una collezione ausiliaria per i test avente un
	 * elemento
	 * </p>
	 */
	@Before
	public void setUp_head() {
		head = new CollectionAdapter();
		head.add("Collection Adapter");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una collezione ausiliaria per i test avente tre
	 * elementi
	 * </p>
	 */
	@Before
	public void setUp_param() {
		param = new CollectionAdapter();
		param.add("param_1");
		param.add("param_2");
		param.add("param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una collezione ausiliaria per i test avente
	 * quattro elementi
	 * </p>
	 */
	@Before
	public void setUp_ct() {
		ct = new CollectionAdapter();
		ct.add("Collection Adapter");
		ct.add("param_1");
		ct.add("param_2");
		ct.add("param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#add(Object o)}, che
	 * aggiunge un elemento alla
	 * collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento sia stato aggiunto alla
	 * collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#add(Object o)} sulla
	 * collezione ct per aggiungere l'elemento passato come parametro e verifica che
	 * il valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale della collezione ct
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct modificata con l'aggiunta di un elemento
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta dell'elemento specificato come parametro
	 * alla collezione ct
	 * </p>
	 * 
	 * @see myAdapter.HCollection#add(Object o)
	 */
	@Test
	public void add_o() {
		boolean res = ct.add("add(Object o)");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#addAll(HCollection c)},
	 * che aggiunge gli
	 * elementi della collezione specificata come parametro alla collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della collezione passata
	 * come parametro siano stati aggiunti alla collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#addAll(HCollection
	 * c)} sulla collezione ct per aggiungere gli elementi della collezione param
	 * passata come parametro e verifica che il valore booleano ritornato sia true
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale della collezione ct
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct modificata con l'aggiunta degli elementi
	 * della collezione param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta degli elementi della collezione param
	 * specificata come parametro alla collezione ct
	 * </p>
	 * 
	 * @see myAdapter.HCollection#addAll(HCollection c)
	 */
	@Test
	public void addAll_c() {
		boolean res = ct.addAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#clear()}, che rimuove
	 * tutti gli elementi della
	 * collezione ct
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della collezione siano
	 * stati tutti rimossi
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#clear()} sulla
	 * collezione ct e verifica che il valore booleano ritornato dalla chiamata del
	 * metodo {@link HCollection#isEmpty()} sulla collezione ct sia true mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct con almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct vuota
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi dalla collezione ct
	 * </p>
	 * 
	 * @see myAdapter.HCollection#clear()
	 */
	@Test
	public void clear() {
		ct.clear();
		Assert.assertTrue(ct.isEmpty());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#contains(Object o)}, che
	 * verifica la presenza
	 * dell'elemento o nella collezione ct
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento passato come parametro sia
	 * effetivamente contenuto nella collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#contains(Object o)}
	 * sulla collezione ct e verifica che il valore booleano ritornato sia true
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene l'elemento da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nella collezione ct dell'elemento passato
	 * come parametro
	 * </p>
	 * 
	 * @see myAdapter.HCollection#contains(Object o)
	 */
	@Test
	public void contains_o() {
		boolean res = ct.contains("Collection Adapter");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo
	 * {@link HCollection#containsAll(HCollection c)}, che verifica la
	 * presenza degli elementi della collezione c nella collezione ct
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della collezione passata
	 * come parametro siano effetivamente contenuti nella collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#containsAll(HCollection c)} sulla collezione ct e verifica
	 * che il valore
	 * booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene gli elementi da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nella collezione ct di tutti gli elementi
	 * della collezione passata come parametro
	 * </p>
	 * 
	 * @see myAdapter.HCollection#containsAll(HCollection c)
	 */
	@Test
	public void containsAll_c() {
		boolean res = ct.containsAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#equals(Object o)}, che
	 * verifica l'uguaglianza
	 * della collezione ct con un'altra fornita come parametro
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della collezione siano gli
	 * stessi, in uguale numero e nello stesso ordine
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la collezione tmp utilizzata come
	 * parametro per effettuare la chiamata del metodo
	 * {@link HCollection#equals(Object o)} e verifica
	 * che il valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct e collezione costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> collezione ct effettivamente uguale alla collezione
	 * costrutita tmp
	 * </p>
	 * 
	 * @see myAdapter.HCollection#equals(Object o)
	 */
	@Test
	public void equals_o() {
		HCollection tmp = new CollectionAdapter();
		tmp.add("Collection Adapter");
		tmp.add("param_1");
		tmp.add("param_2");
		tmp.add("param_3");

		boolean res = ct.equals(tmp) && tmp.equals(ct);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#hashCode()}, che calcola
	 * il codice hash della
	 * collezione su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica l'uguaglianza del codice hash di due
	 * collezioni uguali
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la collezione tmp e verifica che il
	 * codice hash calcolato sia uguale a quello della collezione ct mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct e collezione costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> codice hash della collezione ct effettivamente
	 * uguale quello della collezione costrutita tmp
	 * </p>
	 * 
	 * @see myAdapter.HCollection#hashCode()
	 */
	@Test
	public void hash_Code() {
		HCollection tmp = new CollectionAdapter();
		tmp.add("Collection Adapter");
		tmp.add("param_1");
		tmp.add("param_2");
		tmp.add("param_3");

		boolean res = ct.hashCode() == tmp.hashCode();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#isEmpty()}, che verifica
	 * se la collezione su
	 * cui si effettua la chiamata e' vuota
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la collezione ct, dopo lo svuotamento,
	 * e' vuota
	 * </p>
	 * <p>
	 * <b>Test Description:</b> svuota la collezione ct, chiama il metodo
	 * {@link HCollection#isEmpty()}
	 * e verifica che il valore booleano ritoranto sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct vuota e valore booleano res uguale a
	 * true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> collezione ct e' priva di elementi
	 * </p>
	 * 
	 * @see myAdapter.HCollection#isEmpty()
	 */
	@Test
	public void isEmpty() {
		ct.clear();

		boolean res = ct.isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#iterator()}, per cui si
	 * verifica il corretto
	 * funzionamento dell'iterazione sulla collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la collezione costruita con l'iteratore
	 * e' uguale alla collezione da cui lo si ha estratto
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottiene un iteratore chiamando il metodo
	 * {@link HCollection#iterator()}
	 * sulla collezione ct, esegue l'iterazione dell'intera collezione ct costruendo
	 * la collezione tmp e infine verifica se la collezione ct e' uguale alla
	 * collezione tmp
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct uguale alla collezione tmp
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione della collezione ct eseguita
	 * correttamente
	 * </p>
	 * 
	 * @see myAdapter.HCollection#iterator()
	 */
	@Test
	public void iterator() {
		HCollection tmp = new CollectionAdapter();
		HIterator iter = ct.iterator();
		while (iter.hasNext())
			tmp.add(iter.next());

		Assert.assertEquals(ct, tmp);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#remove(Object o)}, che
	 * rimuove un elemento
	 * dalla collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'elemento o sia stato rimosso dalla
	 * collezione ct
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#remove(Object o)}
	 * sulla collezione ct per passando come parametro l'elemento o da rimuovere e
	 * verifica che il valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct modificata con la rimozione
	 * dell'elemento o
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione dalla collezione ct dell'elemento o
	 * specificato come parametro
	 * </p>
	 * 
	 * @see myAdapter.HCollection#remove(Object o)
	 */
	@Test
	public void remove_o() {
		Object o = "Collection Adapter";

		boolean res = ct.remove(o);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#removeAll(HCollection c)},
	 * che rimuove gli
	 * elementi della collezione specificata come parametro dalla collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della collezione param
	 * passata come parametro siano stati rimossi dalla collezione ct
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#removeAll(HCollection c)}
	 * sulla collezione ct passando come parametro la collezione param che specifica
	 * gli elementi da rimuovere e verifica che il valore booleano ritornato sia
	 * true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct modificata con la rimozione degli
	 * elementi presenti nella collezione param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione degli elementi della collezione param
	 * specificata come parametro dalla collezione ct
	 * </p>
	 * 
	 * @see myAdapter.HCollection#removeAll(HCollection c)
	 */
	@Test
	public void removeAll_c() {
		boolean res = ct.removeAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#retainAll(HCollection c)},
	 * che mantiene nella
	 * collezione solamente gli elementi comuni con la collezione specificata come
	 * parametro del metodo
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che siano stati mantenuti nella collezione
	 * ct solamente gli elementi presenti nella collezione param passata come
	 * parametro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#retainAll(HCollection c)} sulla collezione ct passando
	 * come parametro la
	 * collezione param che specifica gli elementi da mantenere e verifica che il
	 * valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione ct modificata con la rimozione degli
	 * elementi non presenti nella collezione param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione dalla collezione ct degli elementi non
	 * presenti nella collezione param specificata come parametro
	 * </p>
	 * 
	 * @see myAdapter.HCollection#retainAll(HCollection c)
	 */
	@Test
	public void retainAll_c() {
		boolean res = ct.retainAll(param);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#size()}, che restituisce
	 * il numero di elementi
	 * presenti nella collezione su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la collezione ct abbia size uguale a
	 * quattro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#size()} sulla
	 * collezione ct e verifica che il valore intero ritornato sia uguale a quattro
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> size uguale a quattro
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione del numero di elementi della collezione
	 * ct, uguale quattro
	 * </p>
	 * 
	 * @see myAdapter.HCollection#size()
	 */
	@Test
	public void size() {
		int size = ct.size();
		Assert.assertEquals(size, 4);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#toArray()}, che verifica
	 * se l'array di oggetti
	 * contiene effettivamente gli stessi elementi della collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'array di oggetti tmp contiene gli
	 * stessi elementi della collezione head, composta da un solo elemento
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata al metodo
	 * {@link HCollection#toArray()} sulla
	 * collezione head per ottenere l'array di oggetti corrispondente e verifica che
	 * l'unico elemento presente sia uguale all'unico elemento presente nella
	 * collezione head mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione head contiene un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> array di oggetti tmp contiene gli stessi elementi
	 * della collezione head e i valori booleani size e content sono true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> ottenimento di un array di oggetti contenuti nella
	 * collezione head
	 * </p>
	 * 
	 * @see myAdapter.HCollection#toArray()
	 */
	@Test
	public void toArray() {
		Object[] tmp = head.toArray();
		boolean size = tmp.length == 1;
		boolean content = tmp[0].equals("Collection Adapter");
		Assert.assertTrue(size && content);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HCollection#toArray(Object[] a)}, che
	 * verifica se l'array
	 * di oggetti passato come parametro viene effettivamente riempito con gli
	 * stessi elementi della collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'array di oggetti a passato come
	 * parametro venga riempito con gli stessi elementi della collezione head,
	 * composta da un solo elemento
	 * </p>
	 * <p>
	 * <b>Test Description:</b> istanzia un array di dieci oggetti, effettua la
	 * chiamata del metodo {@link HCollection#toArray(Object[] a)} sulla collezione
	 * head per ottenere
	 * l'array a riempito con gli elementi della collezione head e verifica che
	 * l'unico elemento presente sia uguale all'unico elemento presente nella
	 * collezione head mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione head contiene un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> array di oggetti a contiene gli stessi elementi della
	 * collezione head, dunque ha un solo elemento, e i valori booleani size e
	 * content sono true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> riempimento dell'array di oggetti a con gli elementi
	 * contenuti nella collezione head
	 * </p>
	 * 
	 * @see myAdapter.HCollection#toArray(Object[] a)
	 */
	@Test
	public void toArray_a() {
		Object[] a = new Object[10];
		a = head.toArray(a);
		boolean size = a.length == 1;
		boolean content = a[0].equals("Collection Adapter");
		Assert.assertTrue(size && content);
	}

	/**
	 * Seguono ulteriori metodi di test del funzionamento della collezione
	 */

	/**
	 * <p>
	 * <b>Summary:</b> verifica che la collezione accetti l'aggiunta di elementi
	 * duplicati
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo la creazione di una nuova collezione tmp avente
	 * gli stessi elementi della collezione ct e l'aggiunta di un elemento
	 * duplicato, si confrontano il numero di elementi di ciascuna collezione
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore, crea una nuova collezione
	 * tmp riempita con gli elementi della collezione ct e un elemento duplicato, e
	 * verifica che il numero di elementi della collezione ct sia uguale a quello
	 * della collezione tmp, meno uno
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> ct.size() uguale a tmp.size()-1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> accettazione dei duplicati nella collezione
	 * </p>
	 */
	@Test
	public void acceptDuplicate() {
		HCollection tmp = new CollectionAdapter();
		tmp.add("Collection Adapter");
		HIterator iter = ct.iterator();
		while (iter.hasNext())
			tmp.add(iter.next());

		Assert.assertEquals(ct.size(), tmp.size() - 1);
	}

	/**
	 * Seguono i metodi di test per la classe CollectionIterator che implementa
	 * l'interfaccia HIterator
	 */

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#hasNext()} di
	 * {@link HIterator} per una collezione,
	 * che verifica se effettivamente un iteratore ha un elemento successivo
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> itera tutta la collezione mediante un iteratore e
	 * verifica che la dimensione calcolata sia uguale alla dimensione della
	 * collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sulla collezione ct,
	 * effettua ripetute chiamate del metodo {@link HIterator#next()} e incrementi
	 * di size fintanto
	 * che {@link HIterator#hasNext()} restituisce true, e verifica, mediante
	 * asserzione, che la
	 * collezione ct ha dimensione uguale a size
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> ct.size() uguale a size
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione completa della collezione ct senza
	 * lanciare eccezioni
	 * </p>
	 * 
	 * @see myAdapter.HIterator#hasNext()
	 */
	@Test
	public void collectionIterator_hasNext() {
		int size = 0;
		HIterator iter = ct.iterator();
		while (iter.hasNext()) {
			iter.next();
			size++;
		}

		Assert.assertEquals(ct.size(), size);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#next()} di {@link HIterator}
	 * per una collezione, che
	 * verifica se effettivamente l'iteratore esegue avanzamenti sugli elementi
	 * della collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> itera tutta la collezione mediante un iteratore e
	 * verifica che gli elementi iterati siano tutti parte della collezione
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sulla collezione ct,
	 * effettua ripetute chiamate dei metodo {@link HIterator#next()} e
	 * {@link HCollection#contains(Object o)} fintanto
	 * che {@link HIterator#hasNext()} restituisce true, e verifica, mediante
	 * asserzione, che
	 * l'iterazione e' avvenuta solo su elementi della collezione ct
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> iterazione completa della collezione ct senza
	 * lanciare eccezioni
	 * </p>
	 * 
	 * @see myAdapter.HIterator#next()
	 */
	@Test
	public void collectionIterator_next() {
		boolean res = true;
		HIterator iter = ct.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (!ct.contains(obj))
				res = false;
		}

		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#next()} di {@link HIterator}
	 * per una collezione, per
	 * cui si verifica, utilizzando un iteratore della collezione, l'effettivo
	 * lancio di un eccezione in caso di avanzamento mediante
	 * {@link HIterator#next()} dopo aver gia'
	 * iterato l'intera collezione
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che chiamate del metodo
	 * {@link HIterator#next()} successive
	 * all'iterazione dell'intera collezione lanciano l'eccezione
	 * {@link java.util.NoSuchElementException}
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottiene un iteratore chiamando il metodo
	 * {@link HCollection#iterator()}
	 * sulla collezione ct, esegue un'iterazione completa della collezione ct
	 * mediante ripetute chiamate del metodo {@link HIterator#next()}, e di cui ne
	 * viene eseguita una
	 * ulteriore chiamata per scaturire il lancio dell'eccezione
	 * {@link java.util.NoSuchElementException}
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
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
	public void collectionIterator_next_NoSuchElementException() {
		HIterator iter = ct.iterator();
		while (iter.hasNext())
			iter.next();
		iter.next();
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per una collezione, che
	 * verifica il funzionamento della rimozione di elementi della collezione
	 * mediante un iteratore
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> effettua la rimozione di tutti gli elementi della
	 * collezione mediante un iteratore e verifica che sia vuota
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sulla collezione ct,
	 * rimuove tutti gli elementi in essa contenuti effettuando ripetute chiamate
	 * del metodo {@link HIterator#remove()} finche' vi sono ancora elementi nella
	 * collezione ct, e
	 * verifica, mediante asserzione, che la collezione ct sia stata vuotata
	 * controllando il valore booleano restituito dalla chiamata del metodo
	 * {@link HCollection#isEmpty()} sulla collezione ct
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> collezione vuota
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi della collezione ct
	 * mediante l'uso di un iteratore
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test
	public void collectionIterator_remove() {
		HIterator iter = ct.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		boolean res = ct.isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per una collezione, per
	 * cui si verifica, utilizzando un iteratore della collezione, l'effettivo
	 * lancio di un eccezione in caso di rimozione mediante
	 * {@link HIterator#remove()} senza prima
	 * aver chiamato il metodo {@link HIterator#next()}
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la seconda chiamata del metodo
	 * {@link HIterator#remove()}
	 * sull'iteratore lancia l'eccezione {@link IllegalStateException}
	 * </p>
	 * <p>
	 * <b>Test Description:</b> ottengo un iteratore chiamando il metodo
	 * {@link HCollection#iterator()}
	 * sulla collezione ct, e su esso si esegue una chiamata del metodo
	 * {@link HIterator#next()} e due
	 * consecutive del metodo {@link HIterator#remove()} per scaturire il lancio
	 * dell'eccezione
	 * {@link IllegalStateException}
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> collezione ct contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> eccezione IllegalStateException lanciata
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
	public void collectionIterator_remove_IllegalStateException() {
		HIterator iter = ct.iterator();
		if (iter.hasNext())
			iter.next();
		iter.remove();
		iter.remove();
	}
}