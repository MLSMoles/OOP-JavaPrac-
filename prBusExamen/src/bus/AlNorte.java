package bus;

import java.util.ArrayList;

public class AlNorte implements CriterioParadas{

	public ArrayList<Parada> seleccionar(ArrayList<Parada> paradas){
		ArrayList<Parada> ans = new ArrayList<>();
		Parada first = paradas.get(0);
		for(Parada p:paradas) {
			if(first.getLatitud()<p.getLatitud()) ans.add(p);
		}
		return ans;
	}
}
