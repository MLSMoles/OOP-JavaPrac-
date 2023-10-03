package bus;
import java.util.*;

public class DistanciaMinima implements CriterioParadas {
	private double dist;
	
	public DistanciaMinima(double dd) {
		dist = dd;
	}
	
	public ArrayList<Parada> seleccionar(ArrayList<Parada> paradas){
		ArrayList<Parada> ans = new ArrayList<>();
		Parada first = paradas.get(0);
		for(Parada p:paradas) {
			if(first.distancia(p)>dist) ans.add(p);
		}
		return ans;
	}
}
