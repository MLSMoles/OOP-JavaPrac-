package prIndicePalabras;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class IndiceposicionesEnLineas extends IndiceAbstracto {
	private SortedMap<String, SortedMap<Integer, SortedSet<Integer>>> indice;

	public IndiceposicionesEnLineas() {
		indice = new TreeMap<String, SortedMap<Integer, SortedSet<Integer>>>();
	}

	public void resolver(String delim) {
		int numLinea = 0;
		int numApar = 0;
		for (String f : frases) {
			try (Scanner sc = new Scanner(f)) {
				sc.useDelimiter(delim);
				while (sc.hasNext()) {
					if (sc.hasNextLine()) {
						numLinea += 1;
					}
					numApar = numApar + 1;
					String palabra = sc.next();
					anyadir(palabra, numApar, numLinea);
				}
			}
		}
	}

	private void anyadir(String palabra, int numApar, int numLinea) {
		palabra = palabra.toLowerCase();
		SortedMap<Integer, SortedSet<Integer>> s = indice.get(palabra);
		SortedSet<Integer> q = new TreeSet<Integer>();
		if (s == null) {
			s = new TreeMap<Integer, SortedSet<Integer>>();
			indice.put(palabra, s);
		}
		q.add(numLinea);
		s.put(numApar, q);
	}
	
	
	public void presentarIndice(PrintWriter pw) {
		for(String clave : indice.keySet()) {
			pw.println(clave + " " + indice.get(clave));
		}
	}
}
