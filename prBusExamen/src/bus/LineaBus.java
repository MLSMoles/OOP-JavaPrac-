package bus;
import java.util.*;
public class LineaBus {
	private String name;
	private ArrayList<Parada> paradas;
	
	public LineaBus(String nombre) {
		name = nombre;
		paradas = new ArrayList<>();
	}
	
	public void agregar(Parada parada) {
		paradas.add(parada);
	}
	
	public int agregarParadas(String[] datosParadas) {
		int errores=0;
		for(String ss:datosParadas) {
			try(Scanner sc = new Scanner(ss)){
				sc.useDelimiter("[@,]");
				sc.useLocale(Locale.ENGLISH);
				String nombre = sc.next();
				double lt = sc.nextDouble();
				double lg = sc.nextDouble();
				agregar(new Parada(nombre,lt,lg));
			}catch(NoSuchElementException|BusException e) {
				errores++;
			}
		}
		return errores;
	}
	
	public ArrayList<Parada> paradasAlejadas(double dist){
		Parada primera = paradas.get(0);
		ArrayList<Parada> ans = new ArrayList<>();
		for(Parada p:paradas) {
			if(p.distancia(primera)>=dist) {
				ans.add(p);
			}
		}
		return ans;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(name.toUpperCase() +": ");
		StringJoiner  sj = new StringJoiner("->","{","}");
		for(Parada p:paradas) {
			sj.add(p.toString());
		}
		sb.append(sj.toString());
		return sb.toString();
	}
	
	public ArrayList<Parada> filtrarParadas(CriterioParadas filtro){
		return filtro.seleccionar(paradas);
	}

}
