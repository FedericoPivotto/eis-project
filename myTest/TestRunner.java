package myTest;

import java.util.List;
import java.util.ListIterator;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 * <p>
 * <b>Summary:</b> avvia l'esecuzione di tutti i test case definiti nelle test
 * suite {@link TestCollection}, {@link TestSet} e {@link TestMap}
 * </p>
 * <p>
 * <b>Test Suite Design:</b> per ciascuna test suite fornisce in output il
 * numero di test unitari eseguiti e il tempo impiegato, se sono stati eseguiti
 * tutti correttamente, il numero di test falliti, e in caso elenca quali
 * </p>
 */
public class TestRunner {
	/**
	 * <p>
	 * <b>Summary:</b> avvia l'esecuzione della test suite cl passata come parametro
	 * 
	 * @param cl test suite da avviare
	 */
	public static void runTest(Class cl) {
		System.out.println("_____________ " + cl.getName() + " IN ESECUZIONE... _____________");
		Result res = JUnitCore.runClasses(cl);
		// res raccoglie i risultati dell'esecuzione delle unita' di test della classe
		// di test cl

		System.out.println("Ho eseguito " + res.getRunCount() + " tests in " + res.getRunTime() + " millisecondi.");
		System.out.println("Tutti i test sono stati eseguiti correttamente? " + res.wasSuccessful());
		System.out.println("Sono falliti " + res.getFailureCount() + " tests.");

		List<Failure> failures = res.getFailures();
		ListIterator<Failure> fi = failures.listIterator();
		while (fi.hasNext())
			System.out.println(fi.next().toString());
		System.out.println("\n");
	}

	/**
	 * <p>
	 * <b>Summary:</b> avvia l'esecuzione delle test suite {@link TestCollection},
	 * {@link TestSet} e {@link TestMap}
	 * 
	 * @param args array di stringhe reperite da linea di comando
	 */
	public static void main(String[] args) {
		runTest(myTest.TestCollection.class);
		runTest(myTest.TestSet.class);
		runTest(myTest.TestMap.class);
	}
}