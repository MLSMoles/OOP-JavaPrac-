package hospital;

public class MismaPlanta implements Criterio{

	
	private int planta;
	
	public MismaPlanta(int p) {
		planta = p;
	}

	@Override
	public boolean cumpleCondicion(Paciente p, Habitacion h) {
		return h.getPlanata() == planta;
	}
	
	
}
