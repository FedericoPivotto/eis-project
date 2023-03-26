package myTest;

import myAdapter.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * <b>Summary:</b> testa il corretto funzionamento dei metodi della mappa
 * realizzata dalla classe MapAdapter, la quale implementa l'interfaccia
 * {@link HMap}
 * </p>
 * <p>
 * <b>Test Suite Design:</b> testa ciascuno metodo dichiarato nell'interfaccia
 * {@link HMap} per l'implementazione della classe {@link MapAdapter},
 * avvalendosi di mappe
 * ausiliarie costruite appositamente per semplificare la verifica delle
 * asserzioni e che vengono ripristinate prima dell'avvio di ciascun metodo di
 * test
 * </p>
 */
public class TestMap {
	/**
	 * Mappe ausiliarie per l'esecuzione dei test unitari
	 */
	private HMap head, param, mt;
	private HEntry en;

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una mappa ausiliaria per i test avente un
	 * elemento
	 * </p>
	 */
	@Before
	public void setUp_head() {
		head = new MapAdapter();
		head.put("Map Adapter", "Map Adapter");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una mappa ausiliaria per i test avente tre
	 * elementi
	 * </p>
	 */
	@Before
	public void setUp_param() {
		param = new MapAdapter();
		param.put("param_1", "param_1");
		param.put("param_2", "param_2");
		param.put("param_3", "param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> inizializza una mappa ausiliaria per i test avente quattro
	 * elementi
	 * </p>
	 */
	@Before
	public void setUp_mt() {
		mt = new MapAdapter();
		mt.put("Map Adapter", "Map Adapter");
		mt.put("param_1", "param_1");
		mt.put("param_2", "param_2");
		mt.put("param_3", "param_3");
	}

	/**
	 * <p>
	 * <b>Summary:</b> utilizzando reflection, inizializza una entry utilizzata per
	 * realizzare i test sulla classe EntryMapAdapter che implementa l'interfaccia
	 * {@link HEntry}
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 */
	@Before
	public void setUp_en() throws Exception {
		en = instanceEntryMapAdapter("Key", "Value");
	}

	/**
	 * <p>
	 * <b>Summary:</b> metodo ausiliario che utilizza reflection per inizializzare
	 * una entry della classe EntryMapAdapter che implementa l'interfaccia
	 * {@link HEntry}
	 * </p>
	 * 
	 * @param key   chiave della entry
	 * @param value valore della entry
	 * @return entry chiave-valore
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 */
	private HEntry instanceEntryMapAdapter(Object key, Object value) throws Exception {
		// Inizializzo a null l'oggetto entryClass della classe Class.
		Class entryClass = null;

		// Invoco il metodo getDeclaredClasses() sull'oggetto MapAdapter.class della
		// classe Class per ottenere un array di oggetti della classe Class
		// corrispondenti alle classi interne della classe MapAdapter.
		Class[] classes = MapAdapter.class.getDeclaredClasses();

		// Mediante un ciclo for-each, itero l'array classes di oggetti Class e assegno
		// all'oggetto entryClass il riferimento dell'oggetto Class associato alla
		// classe protected EntryMapAdapter, selezionata verificando quale oggetto di
		// questo array implementa l'interfaccia HEntry.
		for (Class cl : classes) { // Ad ogni iterazione, salva nell'oggetto cl della classe Class il riferimento
									// dell'oggetto scansionato dell'array classes.
			if (HEntry.class.isAssignableFrom(cl)) { // Condizione in linguaggio naturale: la classe associata
														// all'oggetto cl implementa l'interfaccia HEntry?
				entryClass = cl; // In caso affermativo, assegna il riferimento dell'oggetto cl all'oggetto
									// entryClass.
			}
		}

		// Inizializzo un array di oggetti della classe Class composto da tre elementi:
		// il primo parametro MapAdapter.class rappresenta l'oggetto della classe Class
		// associato alla classe MapAdapter che contiene la classe interna
		// EntryMapAdapter da cui si vuole estrarre il costruttore, mentre il secondo e
		// terzo parametro sono entrambi oggetti Object.class della classe Class
		// associati alla classe Object.
		// Il primo parametro si rivela necessario per poter selezionare il costruttore
		// da una classe non statica, che nel nostro caso coincide con EntryMapAdapter,
		// mentre il secondo e terzo parametro rappresentano gli effettivi parametri
		// dell'unico costruttore presente nella classe interna EntryMapAdapter e di cui
		// abbiamo bisogno.
		Class[] paramArray = { MapAdapter.class, Object.class, Object.class };

		// A questo punto, invocando il metodo getDeclaredConstructor(paramArray)
		// sull'oggetto entryClass della classe Class associato alla classe
		// EntryMapAdapter, si estrae il costruttore della classe EntryMapAdapter avente
		// come parametri quelli specificati nell'array paramArray appena definito.
		Constructor constr = entryClass.getDeclaredConstructor(paramArray);

		// Ora inizializzo un'istanza map di MapAdapter: risulta necessaria per poter
		// istanziare entry chiave-valore della classe EntryMapAdapter essendo
		// quest'ultima non statica, ossia richiede l'esistenza di un'istanza della
		// classe MapAdapter che la contiene per poter a sua volta istanziare oggetti.
		MapAdapter map = new MapAdapter();

		// Il valore di ritorno di questo metodo ausiliario che utilizza reflection per
		// istanziare entry chiave-valore della classe EntryMapAdapter, coincide con il
		// valore ritornato dal metodo newInstance(map, key, value) chiamato
		// sull'oggetto constr, definito prima, della classe Constructor: siccome la
		// classe dichiarante del costruttore, ossia EntryMapAdapter, è una classe
		// interna in un contesto non statico, il metodo newInstance(Object... initargs)
		// richiede come primo parametro un'istanza della classe MapAdapter, che nel
		// nostro caso e' map, a cui poi seguono i parametri effettivi che si vogliono
		// passare all'unico costruttore della classe EntryMapAdapter, ossia i parametri
		// chiave key e valore value ricevuti dal metodo corrente e per cui si vuole
		// istanziare una entry chiave-valore. Siccome il valore ritornato dal metodo
		// newInstance(map, key, value) e' un oggetto della classe Object, e' necessario
		// eseguire un cast esplicito all'interfaccia pubblica HEntry, che costituisce
		// l'unico modo per restituire all'esterno una entry chiave-valore: HEntry
		// nasconde all'esterno i dettagli realizzativi della entry chiave-valore della
		// classe EntryMapAdapter, istanziata tramite reflection.
		return (HEntry) constr.newInstance(map, key, value);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#clear()}, che rimuove tutti gli
	 * elementi della
	 * mappa mt
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della mappa siano stati
	 * tutti rimossi
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HMap#clear()}
	 * sulla mappa
	 * mt e verifica che il valore booleano ritornato dalla chiamata del metodo
	 * {@link HMap#isEmpty()} sulla mappa mt sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa mt vuota
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi dalla mappa mt
	 * </p>
	 * 
	 * @see myAdapter.HMap#clear()
	 */
	@Test
	public void clear() {
		mt.clear();
		Assert.assertTrue(mt.isEmpty());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#containsKey(Object key)}, che
	 * verifica la
	 * presenza della chiave key nella mappa mt
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la chiave passata come parametro sia
	 * effetivamente contenuta nella mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#containsKey(Object key)} sulla mappa mt e verifica che il valore
	 * booleano ritornato sia true
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene la chiave da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nella mappa mt della chiave passata come
	 * parametro
	 * </p>
	 * 
	 * @see myAdapter.HMap#containsKey(Object key)
	 */
	@Test
	public void containsKey_key() {
		boolean res = mt.containsKey("Map Adapter");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#containsValue(Object value)}, che
	 * verifica la
	 * presenza del valore value nella mappa mt
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che il valore passato come parametro sia
	 * effetivamente contenuto nella mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#containsValue(Object value)} sulla mappa mt e verifica che il
	 * valore booleano ritornato sia true
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene il valore da verificare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> presenza nella mappa mt del valore passato come
	 * parametro
	 * </p>
	 * 
	 * @see myAdapter.HMap#containsValue(Object value)
	 */
	@Test
	public void containsValue_value() {
		boolean res = mt.containsValue("Map Adapter");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#entrySet()}, che verifica se la
	 * mappa ha lo
	 * stesso numero di elementi del set di entry della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la mappa mt ha lo stesso numero di
	 * elementi del set di entry della mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata al metodo
	 * {@link HMap#entrySet()} sulla
	 * mappa mt per ottenere il set es delle entry e verifica se la mappa mt ha lo
	 * stesso numero di elementi del set es mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> es.size() uguale a mt.size()
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza fra il numero di elementi della mappa mt
	 * e del set es di entry
	 * </p>
	 * 
	 * @see myAdapter.HMap#entrySet()
	 */
	@Test
	public void entrySet() {
		HSet es = mt.entrySet();
		Assert.assertEquals(es.size(), mt.size());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#equals(Object o)}, che verifica
	 * l'uguaglianza
	 * della mappa mt con un'altra fornita come parametro
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della mappa siano gli
	 * stessi, in uguale numero
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la mappa tmp utilizzata come parametro
	 * per effettuare la chiamata del metodo {@link HMap#equals(Object o)} e
	 * verifica che il
	 * valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt e mappa costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> mappa mt effettivamente uguale alla mappa costrutita
	 * tmp
	 * </p>
	 * 
	 * @see myAdapter.HMap#equals(Object o)
	 */
	@Test
	public void equals_o() {
		HMap tmp = new MapAdapter();
		tmp.put("Map Adapter", "Map Adapter");
		tmp.put("param_1", "param_1");
		tmp.put("param_2", "param_2");
		tmp.put("param_3", "param_3");

		boolean res = mt.equals(tmp) && tmp.equals(mt);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#get(Object key)}, che verifica la
	 * corretta
	 * restituzione del valore associato alla chiave nella mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che l'oggetto resituito non e' null
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#get(Object key)}
	 * sulla mappa mt passando come parametro la chiave di cui si vuole il valore
	 * associato e verifica che l'oggetto ritornato non sia null mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene la chiave da ricercare
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> oggetto obj uguale a null
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione dalla mappa mt del valore obj associato
	 * alla chiave passata come parametro
	 * </p>
	 * 
	 * @see myAdapter.HMap#get(Object key)
	 */
	@Test
	public void get_key() {
		Object obj = mt.get("Map Adapter");
		Assert.assertNotNull(obj);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#hashCode()}, che calcola il
	 * codice hash della
	 * mappa su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica l'uguaglianza del codice hash di due mappe
	 * uguali
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la mappa tmp e verifica che il codice
	 * hash calcolato sia uguale a quello della mappa mt mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt e mappa costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> codice hash della mappa mt effettivamente uguale a
	 * quello della mappa costrutita tmp
	 * </p>
	 * 
	 * @see myAdapter.HMap#hashCode()
	 */
	@Test
	public void hash_Code() {
		HMap tmp = new MapAdapter();
		tmp.put("Map Adapter", "Map Adapter");
		tmp.put("param_1", "param_1");
		tmp.put("param_2", "param_2");
		tmp.put("param_3", "param_3");

		boolean res = mt.hashCode() == tmp.hashCode();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#isEmpty()}, che verifica se la
	 * mappa su cui si
	 * effettua la chiamata e' vuota
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la mappa mt, dopo lo svuotamento, e'
	 * vuota
	 * </p>
	 * <p>
	 * <b>Test Description:</b> svuota la mappa mt, chiama il metodo
	 * {@link HMap#isEmpty()} e
	 * verifica che il valore booleano ritoranto sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa mt vuota e valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> mappa mt e' priva di elementi
	 * </p>
	 * 
	 * @see myAdapter.HMap#isEmpty()
	 */
	@Test
	public void isEmpty() {
		mt.clear();

		boolean res = mt.isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#keySet()}, che verifica se la
	 * mappa ha lo
	 * stesso numero di elementi del set di chiavi della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la mappa mt ha lo stesso numero di
	 * elementi del set di chiavi della mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata al metodo {@link HMap#keySet()}
	 * sulla mappa
	 * mt per ottenere il set ks delle chiavi e verifica se la mappa mt ha lo stesso
	 * numero di elementi del set ks mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> ks.size() uguale a mt.size()
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza fra il numero di elementi della mappa mt
	 * e del set ks di chiavi
	 * </p>
	 * 
	 * @see myAdapter.HMap#keySet()
	 */
	@Test
	public void keySet() {
		HSet ks = mt.keySet();
		Assert.assertEquals(ks.size(), mt.size());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#put(Object key, Object value)},
	 * che aggiunge
	 * una entry key-value alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la entry sia stata aggiunto alla mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#put(Object key, Object value)} sulla mappa mt per aggiungere la
	 * entry key-value definita dai
	 * parametri e verifica che l'elemento ritornato sia null mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale della mappa mt
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa mt modificata con l'aggiunta della entry
	 * key-value
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta della entry key-value definita dai
	 * parametri alla mappa mt
	 * </p>
	 * 
	 * @see myAdapter.HMap#put(Object key, Object value)
	 */
	@Test
	public void put_key_value() {
		Object obj = mt.put("put(Object key, Object value)", "put(Object key, Object value)");
		Assert.assertNull(obj);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#put(Object key, Object value)},
	 * che verifica
	 * la corretta modifica del value associato a una chiave gia presente nella
	 * mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la entry aggiunta abbia solamente
	 * modificato il value associato alla chiave gia presente nella mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#put(Object key,
	 * Object value)} sulla mappa mt per aggiungere la entry key-value definita dai
	 * parametri e verifica che l'elemento ritornato non sia null e che la chiave
	 * "Map Adapter" sia associata al nuovo value "Value" invece che al vecchio
	 * value "Map Adapter", mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale della mappa mt
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa mt modificata con l'aggiornamento del value, da
	 * "Map Adapter" a "Value", associato alla chiave "Map Adapter"
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> modifica del value associato a una chiave gia
	 * presente nella mappa mt
	 * </p>
	 * 
	 * @see myAdapter.HMap#put(Object key, Object value)
	 */
	@Test
	public void put_key_value_valueSubstitution() {
		Object obj = mt.put("Map Adapter", "Value");

		boolean res = obj != null && mt.get("Map Adapter").equals("Value");
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#putAll(HMap t)}, che aggiunge gli
	 * elementi
	 * della mappa specificata come parametro alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che gli elementi della mappa passata come
	 * parametro siano stati aggiunti alla mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#putAll(HMap t)} sulla
	 * mappa head per aggiungere gli elementi della mappa param passata come
	 * parametro e verifica la mappa head ha lo stesso numero di elementi della
	 * mappa mt mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> stato attuale della mappa mt
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa head modificata con l'aggiunta degli elementi
	 * della mappa param
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> aggiunta degli elementi della mappa param
	 * specificata come parametro alla mappa head
	 * </p>
	 * 
	 * @see myAdapter.HMap#putAll(HMap t)
	 */
	@Test
	public void putAll_t() {
		head.putAll(param);
		Assert.assertEquals(head.size(), mt.size());
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#remove(Object key)}, che rimuove
	 * dalla mappa
	 * la entry avente chiave key
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la entry associata alla chiave key sia
	 * stata rimossa dalla mappa mt
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#remove(Object key)}
	 * sulla mappa mt passando come parametro la chiave key della entry da rimuovere
	 * e verifica che l'oggetto ritornato non sia null mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa mt modificata con la rimozione della entry
	 * associata alla chiave key
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione dalla mappa mt della entry associata alla
	 * chiave key specificata come parametro
	 * </p>
	 * 
	 * @see myAdapter.HMap#remove(Object key)
	 */
	@Test
	public void remove_key() {
		Object obj = mt.remove("Map Adapter");
		Assert.assertNotNull(obj);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#size()}, che restituisce il
	 * numero di elementi
	 * presenti nella mappa su cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la mappa mt abbia size uguale a quattro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HMap#size()}
	 * sulla mappa
	 * mt e verifica che il valore intero ritornato sia uguale a quattro mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> size uguale a quattro
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione del numero di elementi della mappa mt,
	 * uguale quattro
	 * </p>
	 * 
	 * @see myAdapter.HMap#size()
	 */
	@Test
	public void size() {
		int size = mt.size();
		Assert.assertEquals(size, 4);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HMap#values()}, che verifica se la
	 * mappa ha lo
	 * stesso numero di elementi della collezione di value della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la mappa mt ha lo stesso numero di
	 * elementi della collezione di value della mappa
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata al metodo {@link HMap#values()}
	 * sulla mappa
	 * mt per ottenere la collezione vc dei value e verifica se la mappa mt ha lo
	 * stesso numero di elementi della collezione vc mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> vc.size() uguale a mt.size()
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza fra il numero di elementi della mappa mt
	 * e della collezione vc di value
	 * </p>
	 * 
	 * @see myAdapter.HMap#values()
	 */
	@Test
	public void values() {
		HCollection vc = mt.values();
		Assert.assertEquals(vc.size(), mt.size());
	}

	/**
	 * Seguono ulteriori metodi di test del funzionamento della mappa, in
	 * particolare delle operazioni di backing tra mappa e strutture ausiliarie, e
	 * viceversa
	 */

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HMap#clear()} della classe
	 * {@link MapAdapter}, che
	 * verifica se la mappa propaga l'operazione di {@link HMap#clear()} alle sue
	 * strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo {@link HMap#clear()}
	 * sulla mappa mt,
	 * verifica che la mappa mt abbia lo stesso numero di elementi delle strutture
	 * dati ausiliarie mt.entrySet(), mt.keySet() e mt.values() della mappa mt, e
	 * che essa sia pari a 0
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HMap#clear()}
	 * sulla mappa
	 * mt e del metodo size() sulla mappa mt e sulle strutture dati ausiliarie
	 * mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste dimensioni e
	 * quella della mappa mt siano uguali a 0 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 0
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 0 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HMap#clear()
	 */
	@Test
	public void clear_backing() {
		mt.clear();

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 0 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#clear()} della classe
	 * BackedSet per le
	 * entry key-value, che verifica se mt.entrySet() della mappa propaga
	 * l'operazione di {@link HSet#clear()} alle strutture dati ausiliarie
	 * mt.keySet(),
	 * mt.values() e dunque alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo {@link HSet#clear()} su
	 * mt.entrySet(), verifica che la mappa mt abbia lo stesso numero di elementi
	 * delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * della mappa mt, e che essa sia pari a 0
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HSet#clear()}
	 * su
	 * mt.entrySet() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 0 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 0
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 0 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#clear()
	 */
	@Test
	public void entrySet_clear_backing() {
		mt.entrySet().clear();

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 0 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#clear()} della classe
	 * BackedSet per le
	 * chiavi, che verifica se mt.keySet() della mappa propaga l'operazione di
	 * {@link HSet#clear()} alle strutture dati ausiliarie mt.entrySet(),
	 * mt.values() e dunque
	 * alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo {@link HSet#clear()} su
	 * mt.keySet(),
	 * verifica che la mappa mt abbia lo stesso numero di elementi delle strutture
	 * dati ausiliarie mt.entrySet(), mt.keySet() e mt.values() della mappa mt, e
	 * che essa sia pari a 0
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo {@link HSet#clear()}
	 * su
	 * mt.keySet() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 0 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 0
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 0 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#clear()
	 */
	@Test
	public void keySet_clear_backing() {
		mt.keySet().clear();

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 0 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HCollection#clear()} della
	 * classe BackedCollection
	 * per i value, che verifica se mt.values() della mappa propaga l'operazione di
	 * {@link HCollection#clear()} alle strutture dati ausiliarie mt.entrySet(),
	 * mt.keySet() e dunque
	 * alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HCollection#clear()} su mt.values(),
	 * verifica che la mappa mt abbia lo stesso numero di elementi delle strutture
	 * dati ausiliarie mt.entrySet(), mt.keySet() e mt.values() della mappa mt, e
	 * che essa sia pari a 0
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#clear()} su
	 * mt.values() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 0 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt con zero o piu' elementi
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 0
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 0 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HCollection#clear()
	 */
	@Test
	public void values_clear_backing() {
		mt.values().clear();

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 0 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HMap#remove(Object key)} della
	 * classe
	 * {@link MapAdapter}, che verifica se la mappa propaga l'operazione di
	 * {@link HMap#remove(Object
	 * key)} alle sue strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values()
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HMap#remove(Object key)}
	 * sulla mappa mt, verifica che la mappa mt abbia lo stesso numero di elementi
	 * delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * della mappa mt, e che essa sia pari a 3
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HMap#remove(Object key)}
	 * sulla mappa mt passando come parametro la chiave "Map Adapter", in essa gia
	 * presente, e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 3 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 3
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 3 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HMap#remove(Object key)
	 */
	@Test
	public void remove_o_backing() {
		mt.remove("Map Adapter");

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 3 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#remove(Object o)} della
	 * classe
	 * BackedSet, che verifica se mt.entrySet() propaga l'operazione di
	 * {@link HSet#remove(Object o)} alle sue strutture dati ausiliarie mt.keySet(),
	 * mt.values() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#remove(Object o)} su
	 * mt.entrySet(), verifica che la mappa mt abbia lo stesso numero di elementi
	 * delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * della mappa mt, e che essa sia pari a 3
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#remove(Object o)}
	 * su mt.entrySet() passando come parametro la entry avente come chiave e valore
	 * "Map Adapter", la quale e' gia presente nella mappa mt, e del metodo size()
	 * sulla mappa mt e sulle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values(), e verifica se queste dimensioni e quella della mappa mt siano
	 * uguali a 3 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 3
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 3 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 * @see myAdapter.HSet#remove(Object o)
	 */
	@Test
	public void entrySet_remove_o_backing() throws Exception {
		HEntry en = instanceEntryMapAdapter("Map Adapter", "Map Adapter");
		mt.entrySet().remove(en);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 3 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#remove(Object o)} della
	 * classe
	 * BackedSet, che verifica se mt.keySet() propaga l'operazione di
	 * {@link HSet#remove(Object o)} alle sue strutture dati ausiliarie
	 * mt.entrySet(), mt.values() e alla
	 * mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#remove(Object o)} su
	 * mt.keySet(), verifica che la mappa mt abbia lo stesso numero di elementi
	 * delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * della mappa mt, e che essa sia pari a 3
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#remove(Object o)}
	 * su mt.keySet() passando come parametro la chiave "Map Adapter", in essa gia
	 * presente, e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 3 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 3
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 3 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#remove(Object o)
	 */
	@Test
	public void keySet_remove_o_backing() {
		mt.keySet().remove("Map Adapter");

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 3 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HCollection#remove(Object o)}
	 * della classe
	 * BackedCollection, che verifica se mt.values() propaga l'operazione di
	 * {@link HCollection#remove(Object o)} alle sue strutture dati ausiliarie
	 * mt.entrySet(),
	 * mt.keySet() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HCollection#remove(Object o)} su
	 * mt.values(), verifica che la mappa mt abbia lo stesso numero di elementi
	 * delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e mt.values()
	 * della mappa mt, e che essa sia pari a 3
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#remove(Object o)}
	 * sulla mappa mt passando come parametro il value "Map Adapter", in essa gia
	 * presente, e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 3 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 3
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 3 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HCollection#remove(Object o)
	 */
	@Test
	public void values_remove_o_backing() {
		mt.values().remove("Map Adapter");

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 3 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di HIterator per
	 * il set
	 * mt.entrySet(), che verifica il funzionamento della rimozione di elementi dal
	 * set mediante un iteratore e verifica la corretta propagazione delle modifiche
	 * alla mappa e alle sue strutture dati ausiliarie, ossia verifica il backing
	 * della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> effettua la rimozione di tutti gli elementi del set
	 * mt.entrySet() mediante un iteratore e verifica che la mappa e le sue
	 * strutture dati ausiliarie siano, di conseguenza, vuote
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sul set mt.entrySet(),
	 * rimuove tutti gli elementi in esso contenuti effettuando ripetute chiamate
	 * del metodo {@link HIterator#remove()} finche' vi sono ancora elementi nel set
	 * mt.entrySet(), e
	 * verifica, mediante asserzione, che il set mt.entrySet(), la mappa e le sue
	 * rimanenti strutture dati ausiliarie siano state vuotate controllando il
	 * valore booleano restituito dalle chiamate del metodo isEmpty() su di esse
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa e strutture dati ausiliarie vuote
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi del set
	 * mt.entrySet() mediante l'uso di un iteratore, e propagazione delle modifiche
	 * alla mappa e alle sue strutture dati ausiliarie mt.keySet() e mt.values()
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test
	public void backedSetIterator_entrySet_remove_backing() {
		HIterator iter = mt.entrySet().iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		boolean res = mt.isEmpty() && mt.entrySet().isEmpty() && mt.keySet().isEmpty() && mt.values().isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per il set mt.keySet(),
	 * che verifica il funzionamento della rimozione di elementi dal set mediante un
	 * iteratore e verifica la corretta propagazione delle modifiche alla mappa e
	 * alle sue strutture dati ausiliarie, ossia verifica il backing della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> effettua la rimozione di tutti gli elementi del set
	 * mt.keySet() mediante un iteratore e verifica che la mappa e le sue strutture
	 * dati ausiliarie siano, di conseguenza, vuote
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sul set mt.keySet(),
	 * rimuove tutti gli elementi in esso contenuti effettuando ripetute chiamate
	 * del metodo {@link HIterator#remove()} finche' vi sono ancora elementi nel set
	 * mt.keySet(), e
	 * verifica, mediante asserzione, che il set mt.keySet(), la mappa e le sue
	 * rimanenti strutture dati ausiliarie siano state vuotate controllando il
	 * valore booleano restituito dalle chiamate del metodo isEmpty() su di esse
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa e strutture dati ausiliarie vuote
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi del set mt.keySet()
	 * mediante l'uso di un iteratore, e propagazione delle modifiche alla mappa e
	 * alle sue strutture dati ausiliarie mt.entrySet() e mt.values()
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test
	public void backedSetIterator_keySet_remove_backing() {
		HIterator iter = mt.keySet().iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		boolean res = mt.isEmpty() && mt.entrySet().isEmpty() && mt.keySet().isEmpty() && mt.values().isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HIterator#remove()} di
	 * {@link HIterator} per la collezione
	 * mt.values(), che verifica il funzionamento della rimozione di elementi dalla
	 * collezione mediante un iteratore e verifica la corretta propagazione delle
	 * modifiche alla mappa e alle sue strutture dati ausiliarie, ossia verifica il
	 * backing della mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> effettua la rimozione di tutti gli elementi della
	 * collezione mt.values() mediante un iteratore e verifica che la mappa e le sue
	 * strutture dati ausiliarie siano, di conseguenza, vuote
	 * </p>
	 * <p>
	 * <b>Test Description:</b> utilizzando un iteratore sulla mappa mt.values(),
	 * rimuove tutti gli elementi in esso contenuti effettuando ripetute chiamate
	 * del metodo {@link HIterator#remove()} finche' vi sono ancora elementi nella
	 * collezione
	 * mt.values(), e verifica, mediante asserzione, che la collezione mt.values(),
	 * la mappa e le sue rimanenti strutture dati ausiliarie siano state vuotate
	 * controllando il valore booleano restituito dalle chiamate del metodo
	 * isEmpty() su di esse
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> mappa mt contiene almeno un elemento
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> mappa e strutture dati ausiliarie vuote
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> rimozione di tutti gli elementi della collezione
	 * mt.values() mediante l'uso di un iteratore, e propagazione delle modifiche
	 * alla mappa e alle sue strutture dati ausiliarie mt.entrySet() e mt.keySet()
	 * </p>
	 * 
	 * @see myAdapter.HIterator#remove()
	 */
	@Test
	public void backedCollectionIterator_values_remove_backing() {
		HIterator iter = mt.values().iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		boolean res = mt.isEmpty() && mt.entrySet().isEmpty() && mt.keySet().isEmpty() && mt.values().isEmpty();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#removeAll(HCollection c)}
	 * della classe
	 * BackedSet, che verifica se mt.entrySet() propaga l'operazione di
	 * {@link HSet#removeAll(HCollection c)} alle sue strutture dati ausiliarie
	 * mt.keySet(),
	 * mt.values() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#removeAll(HCollection c)} su mt.entrySet(), verifica che la mappa
	 * mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#removeAll(HCollection c)} su mt.entrySet() passando come
	 * parametro
	 * param.entrySet() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 * @see myAdapter.HSet#removeAll(HCollection c)
	 */
	@Test
	public void entrySet_removeAll_c_backing() throws Exception {
		HSet es_param = param.entrySet();
		mt.entrySet().removeAll(es_param);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#removeAll(HCollection c)}
	 * della classe
	 * BackedSet, che verifica se mt.keySet() propaga l'operazione di
	 * {@link HSet#removeAll(HCollection c)} alle sue strutture dati ausiliarie
	 * mt.entrySet(),
	 * mt.values() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#removeAll(HCollection c)} su mt.keySet(), verifica che la mappa
	 * mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#removeAll(HCollection c)} su mt.keySet() passando come parametro
	 * param.keySet() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#removeAll(HCollection c)
	 */
	@Test
	public void keySet_removeAll_c_backing() {
		HSet ks_param = param.keySet();
		mt.keySet().removeAll(ks_param);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo
	 * {@link HCollection#removeAll(HCollection c)} della classe
	 * BackedCollection, che verifica se mt.values() propaga l'operazione di
	 * {@link HCollection#removeAll(HCollection c)} alle sue strutture dati
	 * ausiliarie mt.entrySet(),
	 * mt.keyValue() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HCollection#removeAll(HCollection c)} su mt.values(), verifica che la
	 * mappa mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HCollection#removeAll(HCollection c)} su mt.values() passando come
	 * parametro
	 * param.values() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HCollection#removeAll(HCollection c)
	 */
	@Test
	public void values_removeAll_c_backing() {
		HCollection vc_param = param.values();
		mt.values().removeAll(vc_param);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#retainAll(HCollection c)}
	 * della classe
	 * BackedSet, che verifica se mt.entrySet() propaga l'operazione di
	 * {@link HSet#retainAll(HCollection c)} alle sue strutture dati ausiliarie
	 * mt.keySet(),
	 * mt.values() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.entrySet(), verifica che la mappa
	 * mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.entrySet() passando come
	 * parametro
	 * head.entrySet() e del metodo size() sulla mappa mt e sulle strutture dati
	 * ausiliarie mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste
	 * dimensioni e quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 * @see myAdapter.HSet#retainAll(HCollection c)
	 */
	@Test
	public void entrySet_retainAll_c_backing() throws Exception {
		HSet es_head = head.entrySet();
		mt.entrySet().retainAll(es_head);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#retainAll(HCollection c)}
	 * della classe
	 * BackedSet, che verifica se mt.keySet() propaga l'operazione di
	 * {@link HSet#retainAll(HCollection c)} alle sue strutture dati ausiliarie
	 * mt.entrySet(),
	 * mt.values() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.keySet(), verifica che la mappa
	 * mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.keySet() passando come parametro
	 * head.keySet()
	 * e del metodo size() sulla mappa mt e sulle strutture dati ausiliarie
	 * mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste dimensioni e
	 * quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#retainAll(HCollection c)
	 */
	@Test
	public void keySet_retainAll_c_backing() {
		HSet ks_head = head.keySet();
		mt.keySet().retainAll(ks_head);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test backing del metodo {@link HSet#retainAll(HCollection c)}
	 * della classe
	 * BackedCollection, che verifica se mt.values() propaga l'operazione di
	 * {@link HSet#retainAll(HCollection c)} alle sue strutture dati ausiliarie
	 * mt.entrySet(),
	 * mt.keyValue() e alla mappa
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> dopo una chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.values(), verifica che la mappa
	 * mt abbia lo stesso numero di
	 * elementi delle strutture dati ausiliarie mt.entrySet(), mt.keySet() e
	 * mt.values() della mappa mt, e che essa sia pari a 1
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HSet#retainAll(HCollection c)} su mt.values() passando come parametro
	 * head.values()
	 * e del metodo size() sulla mappa mt e sulle strutture dati ausiliarie
	 * mt.entrySet(), mt.keySet() e mt.values(), e verifica se queste dimensioni e
	 * quella della mappa mt siano uguali a 1 mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 4
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> dimensione della mappa mt e delle sue strutture dati
	 * ausiliarie uguali a 1
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> uguaglianza a 1 della dimensione della mappa mt e
	 * delle sue strutture dati ausiliarie
	 * </p>
	 * 
	 * @see myAdapter.HSet#retainAll(HCollection c)
	 */
	@Test
	public void values_retainAll_c_backing() {
		HCollection vc_head = head.values();
		mt.values().retainAll(vc_head);

		int size = mt.size();
		int es_size = mt.entrySet().size();
		int ks_size = mt.keySet().size();
		int vc_size = mt.values().size();

		boolean res = size == 1 && size == es_size && es_size == ks_size && ks_size == vc_size;
		Assert.assertTrue(res);
	}

	/**
	 * Seguono i metodi di test per la classe EntryMapAdapter che implementa
	 * l'interfaccia HEntry
	 */

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HEntry#equals(Object o)} della classe
	 * EntryMapAdapter
	 * che implementa l'interfaccia {@link HEntry}, che verifica l'uguaglianza della
	 * entry
	 * con un'altra fornita come parametro
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica se la entry en e' uguale alla entry tmp
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la entry tmp utilizzata come parametro
	 * per effettuare la chiamata del metodo {@link HEntry#equals(Object o)} e
	 * verifica che il
	 * valore booleano ritornato sia true mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> entry en e entry costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> entry en effettivamente uguale alla entry costrutita
	 * tmp
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 * @see myAdapter.HEntry#equals(Object o)
	 */
	@Test
	public void entryMapAdapter_equals_o() throws Exception {
		HEntry tmp = instanceEntryMapAdapter("Key", "Value");

		boolean res = en.equals(tmp) && tmp.equals(en);
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HEntry#getKey()} della classe
	 * EntryMapAdapter che
	 * implementa l'interfaccia {@link HEntry}, che verifica la corretta
	 * restituzione della
	 * chiave associata alla entry
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la chiave resituita e' effettivamente
	 * quella che ci si aspettava
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HEntry#getKey()} sulla entry
	 * en e verifica che l'oggetto ritornato sia la chiave "Key" che ci si aspettava
	 * mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> entry en ha chiave "Key"
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> oggetto key uguale a "Key"
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione della chiave della entry en
	 * </p>
	 * 
	 * @see myAdapter.HEntry#getKey()
	 */
	@Test
	public void entryMapAdapter_getKey() {
		Object key = en.getKey();
		Assert.assertEquals(key, "Key");
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HEntry#getValue()} della classe
	 * EntryMapAdapter che
	 * implementa l'interfaccia {@link HEntry}, che verifica la corretta
	 * restituzione del
	 * value associato alla entry
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che il value resituito e' effettivamente
	 * quello che ci si aspettava
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HEntry#getValue()} sulla
	 * entry en e verifica che l'oggetto ritornato sia il value "Value" che ci si
	 * aspettava mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> entry en ha value "Value"
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> oggetto value uguale a "Value"
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> restituzione del value della entry en
	 * </p>
	 * 
	 * @see myAdapter.HEntry#getValue()
	 */
	@Test
	public void entryMapAdapter_getValue() {
		Object value = en.getValue();
		Assert.assertEquals(value, "Value");
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HEntry#hashCode()} della classe
	 * EntryMapAdapter che
	 * implementa l'interfaccia {@link HEntry}, che calcola il codice hash della
	 * entry su
	 * cui si effettua la chiamata
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica l'uguaglianza del codice hash di due entry
	 * uguali
	 * </p>
	 * <p>
	 * <b>Test Description:</b> costruisce la entry tmp e verifica che il codice
	 * hash calcolato sia uguale a quello della entry en mediante asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> entry en e entry costruita tmp uguali
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> valore booleano res uguale a true
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> codice hash della entry en effettivamente uguale a
	 * quello della entry costrutita tmp
	 * </p>
	 * 
	 * @throws Exception lanciata dai metodi del package java.lang.reflect
	 * @see myAdapter.HEntry#hashCode()
	 */
	@Test
	public void entryMapAdapter_hash_Code() throws Exception {
		HEntry tmp = instanceEntryMapAdapter("Key", "Value");

		boolean res = en.hashCode() == tmp.hashCode();
		Assert.assertTrue(res);
	}

	/**
	 * <p>
	 * <b>Summary:</b> test del metodo {@link HEntry#setValue(Object value)} della
	 * classe
	 * EntryMapAdapter che implementa l'interfaccia {@link HEntry}, che verifica la
	 * corretta
	 * modifica della chiave associata alla entry
	 * </p>
	 * <p>
	 * <b>Test Case Design:</b> verifica che la chiave modificata e' effettivamente
	 * uguale a quella passata come parametro
	 * </p>
	 * <p>
	 * <b>Test Description:</b> effettua la chiamata del metodo
	 * {@link HEntry#setValue(Object value)} sulla entry en e verifica che l'oggetto
	 * ritornato dal metodo
	 * {@link HEntry#getValue()} sia uguale alla nuova chiave "New value" mediante
	 * asserzione
	 * </p>
	 * <p>
	 * <b>Pre-Condition:</b> entry en ha value "Value"
	 * </p>
	 * <p>
	 * <b>Post-Condition:</b> entry en ha value uguale a "New value"
	 * </p>
	 * <p>
	 * <b>Expected Results:</b> modifica del value della entry en con quello passato
	 * come parametro
	 * </p>
	 * 
	 * @see myAdapter.HEntry#setValue(Object value)
	 */
	@Test
	public void entryMapAdapter_setValue_value() {
		en.setValue("New value");
		Assert.assertEquals(en.getValue(), "New value");
	}
}