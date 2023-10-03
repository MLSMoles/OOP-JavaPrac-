package alturas;

public class Pais implements Comparable<Pais>{
	private String nombre,continente;
	private double altura;
	
	public Pais(String nombre, String cont, double alt) {
		this.nombre = nombre;
		continente = cont;
		altura = alt;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContinente() {
		return continente;
	}

	public double getAltura() {
		return altura;
	}
	
	public boolean equals(Object o) {
		boolean ok = false;
		if(o instanceof Pais) {
			Pais p = (Pais) o;
			ok = nombre.equals(p.getNombre());
		}
		return ok;
	}
	
	public int hashcode() {
		return nombre.hashCode();
	}
	
	
	public int compareTo(Pais o) {
		int num = nombre.compareTo(o.getNombre());
		return num;
	}
	
	public String toString() {
		return nombre + " , " + continente +" , " + altura;
	}
}
