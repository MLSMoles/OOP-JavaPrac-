package prIndicePalabras;

import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.TreeSet;


public class IndiceLineas extends IndiceAbstracto{
	private SortedMap<String, SortedSet<Integer>> indice;
	
	public IndiceLineas() {
		indice = new TreeMap<String, SortedSet<Integer>>();
	}
	
	@Override
	public void resolver(String delim) {
		int numLineas=0;
		for (String f : frases) {
			try (Scanner sc = new Scanner(f)) {
				sc.useDelimiter(delim);
				while(sc.hasNext()) {
					numLineas = numLineas+1;
					String palabra = sc.next();
					anyadir(palabra, numLineas);
				}
			}
		}
	}
	private void anyadir(String palabra, int numLinea) {
		palabra = palabra.toLowerCase();
		//1ª opcion
		SortedSet<Integer> s = indice.get(palabra);
		if(s == null) {
			s = new TreeSet<Integer>();
			indice.put(palabra, s);
		}
		s.add(numLinea);
		}
		//2ª metodo
		//Integer contador1 = indice.getOrDefault(palabra, 0);
		//indice.put(palabra, contador1 + 1);
	
	
	@Override
	
	public void presentarIndice(PrintWriter pw) {
		//1ª opcion
		for(Entry<String, SortedSet<Integer>> e : indice.entrySet()) {
			pw.println(e.getKey() + " " + MostrarConjunto(e.getValue()));
		}
		//2 ocpion
		for(String clave : indice.keySet()) {
			pw.println(clave + " " + indice.get(clave));
		}
	}
	
	public String MostrarConjunto(SortedSet<Integer> conjunto) {
		StringJoiner sj = new StringJoiner(",", "<", ">");
		for(Integer i:conjunto){
			sj.add(i.toString());
		}
		return sj.toString();
	}
}