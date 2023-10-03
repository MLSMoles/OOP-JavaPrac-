package sociedad;

import java.util.Set;

public class SociedadLimitada extends Sociedad {

	private int positionsLimit;
	
	public SociedadLimitada(int pl) {
		super();
		if (pl <= 0) {
			throw new SociedadException("No se permite un número de plazas 0 o negativo para las actividades: " + pl);
		}
		positionsLimit = pl;
	}
	
	@Override
	public void inscribir (String name, int id, String activity) {
	    Set<Socio> membersInActivity = this.inscritos(activity);
	    if ((membersInActivity != null)
	            &&(membersInActivity.size() >= positionsLimit)) {
	          throw new SociedadException("No hay plazas disponibles para la actividad " + activity);
	        }
	        super.inscribir(name, id, activity);
	    }

}
