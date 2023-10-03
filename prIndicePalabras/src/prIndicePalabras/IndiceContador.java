package prIndicePalabras;

import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


public class IndiceContador extends IndiceAbstracto{
	private SortedMap<String,Integer> indice;
	
	public IndiceContador() {
		indice = new TreeMap<String, Integer>();
	}
	
	@Override
	public void resolver(String delim) {
		for (String f : frases) {
			try (Scanner sc = new Scanner(f)) {
				sc.useDelimiter(delim);
				while(sc.hasNext()) {
					String palabra = sc.next();
					anyadir(palabra);
				}
			}
		}
	}
	private void anyadir(String palabra) {
		palabra = palabra.toLowerCase();
		//1ª opcion
		Integer contador = indice.get(palabra);
		if(contador == null) {
			indice.put(palabra, 1);
		}else {
			indice.put(palabra, contador+1);
		}
		//2ª metodo
		//Integer contador1 = indice.getOrDefault(palabra, 0);
		//indice.put(palabra, contador1 + 1);
	}
	
	@Override
	
	public void presentarIndice(PrintWriter pw) {
		//1ª opcion
		for(Entry<String, Integer> e : indice.entrySet()) {
			pw.println(e.getKey() + " " + e.getValue());
		}
		//2 ocpion
		for(String clave : indice.keySet()) {
			pw.println(clave + " " + indice.get(clave));
		}
	}
}
//getorDefault