package bus;

import java.util.ArrayList;

public class LineaBus {
	private ArrayList<Parada> paradas;
	private String nombre;
	
	public LineaBus(String nombre) {
		this.nombre = nombre;
		paradas = new ArrayList<>();
	}
	
	public void agregar(Parada p) {
		paradas.add(p);
	}
	public void cambiar(String nombreParada, Parada nuevaParada) {
		int i = numeroParada(nombreParada);
		if (i ==-1) throw new RuntimeException();
		paradas.set(i, nuevaParada);
	}
	
	
	public boolean pasaPor(String nombreParada) {
		int i = (numeroParada(nombreParada));
		return   (i!=-1) ? true:false ;
	}
	
//	public int numeroParada(String nombreParada) {
//		int i = 0;
//		boolean ans = false;
//		while(i < paradas.size() && !ans) {
//			ans = paradas.get(i).getNombre().equalsIgnoreCase(nombreParada);
//			i++;
//		}
//		return (ans) ? (i-1):-1;
//	}
	
	public int numeroParada(String nombreParada) {
		int i = 0;
		while(i < paradas.size() && !nombreParada.equalsIgnoreCase(paradas.get(i).getNombre())){
			i++;
		}
		return i < paradas.size() ? i : -1;
	}
	
	public String getNombreLinea() {
		return nombre;
	}
	public Parada inicio() {
		if (paradas.size() == 0) throw new RuntimeException();
		return paradas.get(0);
	}
	public Parada fin() {
		if (paradas.size() == 0) throw new RuntimeException();
		return paradas.get(paradas.size()-1);
	}
	
	public double distanciaLinea() {
		if (paradas.size() == 0) return 0;
		double dist = 0;
		for(int i = 0;i<paradas.size();i++) {
			dist += paradas.get(i).distancia(paradas.get(i++));
		}
		return dist;
	}
	public String toString() {
		String ss = nombre + "[";
		for ( int i = 0;i<paradas.size();i++) {
			ss += (i+1!= paradas.size()) ? paradas.get(i).toString() +", ": paradas.get(i);
		}
		return ss +"]";
	}
}
