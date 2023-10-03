package hospital;



public class Paciente implements Comparable<Paciente> {
	private String nombre, apellidos, nuss;
	private int anno;

	public Paciente(String nom, String ape, String num, int ano) throws HospitalException {
		if (nom == null || num == null || ape == null) {
			throw new HospitalException("Nombre o numero erroneo.");
		}
		if (nom.length() == 0 || ape.length() == 0) {
			throw new HospitalException("Nombre o apellido vacios.");
		}
		if (num.length() != 10) {
			throw new HospitalException("NUSS de tamaño erroneo.");
		}
		if (!checker(num)) {
			throw new HospitalException("NUSS erroneo.");
		}
		nombre = nom;
		apellidos = ape;
		nuss = num;
		anno = ano;
	}

	private boolean checker(String nus) {
		int aux1 = Integer.parseInt(nus.substring(0, 8));
		int aux2 = Integer.parseInt(nus.substring(8, 10));
		return aux1 % 97 == aux2;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNuss() {
		return nuss;
	}

	public int getAnoDeNacimiento() {
		return anno;
	}

	public boolean equals(Object o) {
		boolean ans = o instanceof Paciente;
		if (ans) {
			Paciente aux = (Paciente) o;
			ans = this.nombre.equalsIgnoreCase(aux.nombre) && this.apellidos.equalsIgnoreCase(aux.apellidos)
					&& this.nuss == aux.nuss && this.anno == aux.anno;
		}
		return ans;
	}

	
	public int compareTo(Paciente o) {
		int ans = Integer.compare(o.anno, this.anno);
		if (ans == 0) {
			ans = this.nuss.compareTo(o.nuss);
			if (ans == 0) {
				ans = this.apellidos.compareToIgnoreCase(o.apellidos);

				if (ans == 0) {
					ans = this.nombre.compareToIgnoreCase(o.nombre);
				}
			}
		}
		return ans;
	}
	
	public int hashCode() {
		return nombre.toLowerCase().hashCode()
			+ apellidos.toLowerCase().hashCode()
			+ nuss.hashCode()
			+ Integer.hashCode(anno);
	}
	
	public String toString() {
		return "Paciente [nombre=" + nombre
			+ ", apellidos=" + apellidos
			+ ", nuss=" + nuss
			+ ", anoDeNacimiento=" + anno + "]";
	}
}
