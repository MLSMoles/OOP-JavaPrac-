package hospital;

public class NacidoAntesDe implements Criterio{
	private int anno;
	
	public NacidoAntesDe(int i) {
		anno = i;
	}

	@Override
	public boolean cumpleCondicion(Paciente p, Habitacion h) {
		return p.getAnoDeNacimiento()<anno;
	}
	
}
