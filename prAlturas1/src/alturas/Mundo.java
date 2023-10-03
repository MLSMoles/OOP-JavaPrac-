package alturas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Mundo {
	private static List<Pais> list;

	private Mundo(List<Pais> paices) {
		list = new LinkedList<>();
		list.addAll(paices);
	}

	public List<Pais> getPaises() {
		return list;
	}

	public static Mundo createFromFile(String file) throws FileNotFoundException {
		List<Pais> p = new LinkedList<>();
		try (Scanner sc = new Scanner(new File(file))) {
			while (sc.hasNextLine()) {
				try (Scanner sc2 = new Scanner(sc.nextLine())) {
					sc2.useDelimiter("[,]");
					Pais pp = new Pais(sc2.next(), sc2.next(), sc2.nextDouble());
					p.add(pp);
				}
			}
		} catch (FileNotFoundException e) {

		}
		return new Mundo(p);
	}

	public static <K, V> void presentaEnConsola(Map<K, V> map) {
		Iterator<K> it = map.keySet().iterator();
		while (it.hasNext()) {
			K key = (K) it.next();
			System.out.println(key + "\t" + map.get(key));
		}
	}

	public SortedMap<String, Integer> numeroDePaisesPorContinente() {
		SortedMap<String, Integer> ppc = new TreeMap<>();
		Iterator<Pais> it = list.iterator();
		Pais pais;
		Integer valor;
		while (it.hasNext()) {
			pais = it.next();
			valor = ppc.get(pais.getContinente());
			if (valor == null) {
				ppc.put(pais.getContinente(), 1);
			} else {
				valor++;
				ppc.put(pais.getContinente(), valor);
			}
		}
		return ppc;
	}

	/*
	 * / num = Math.round(
	 */
	public SortedMap<Double, List<Pais>> paisesPorAltura() {
		SortedMap<Double, List<Pais>> ppa = new TreeMap<>();
		Iterator<Pais> it = list.iterator();
		Pais pais;
		List<Pais> lp;
		Double altura;
		while (it.hasNext()) {
			pais = it.next();
			altura = Math.floor(pais.getAltura() * 10) / 10.0;
			lp = ppa.get(altura);
			if (lp == null) {
				lp = new LinkedList<Pais>();
				ppa.put(altura, lp);
			}
			lp.add(pais);  
		}
		return ppa;
	}
	
	public SortedMap<String , List<Pais>> paisesPorContinente(){
		SortedMap<String, List<Pais>> ppc = new TreeMap<>();
		Iterator<Pais> it = list.iterator();
		Pais pais;
		List<Pais> lpp;
		String cont;
		while(it.hasNext()) {
			pais = it.next();
			cont = pais.getContinente();
			lpp = ppc.get(cont);
			if(cont == null){
				lpp = new LinkedList<Pais>();
				ppc.put(cont, lpp);
			}
			lpp.add(pais);
		}
		return ppc;
		
	}
	
	public SortedMap<String, List<Pais>> paisesPorInicial(){
		SortedMap<String, List<Pais>> ppi = new TreeMap<>();
		String inic;
		List<Pais> lp;
		Iterator<Pais> it = list.iterator();
		Pais pais;
		while(it.hasNext()) {
			pais = it.next();
			inic = pais.getContinente().substring(0,1);
			lp = ppi.get(inic);
			if(inic == null){
				lp = new LinkedList<Pais>();
			}
			ppi.put(inic, lp);
		}
		return ppi;
		/*/
		 * string.substring(0,1);
		 * charAt( int 0) + "";
		 */
	}
	
	public SortedMap<String, Double> mediaPorContinente(){
		SortedMap<String, Double> mpc = new TreeMap<>();
		SortedMap<String, List<Pais>> ppc = paisesPorContinente();
		Iterator<String> itcont = ppc.keySet().iterator();
		String cont;
		Double media;
		while(itcont.hasNext()) {
		}

		return null;
	}
	
	public List<String> continentesConMasPaises() {
		SortedMap<String,Integer> ppc = numeroDePaisesPorContinente();
		List<String> ccmp = new LinkedList<>();
		Iterator<String> it = ppc.keySet().iterator();
		String cont;
		int numPaises=0;
		while(it.hasNext()) {
			cont = it.next();
			ccmp.add(cont);
		}
		return ccmp;
		}
}
