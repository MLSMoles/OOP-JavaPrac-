package hospital;

public class Habitacion implements Comparable<Habitacion>{
	private int planta, numero;

	public Habitacion(int planta, int numero) {
		this.planta = planta;
		this.numero = numero;
	}

	public int getPlanata() {
		return planta;
	}

	public void setPlanata(int planata) {
		this.planta = planata;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean equals(Object o) {
		boolean ans = o instanceof Habitacion;
		if (ans) {
			Habitacion aux = (Habitacion) o;
			ans =this.planta == aux.planta && this.numero == aux.numero;
		}
		return ans;
	}
	
	public int compareTo(Habitacion h) {
		int ans = Integer.compare(planta, h.planta);
		if(ans==0) ans = Integer.compare(numero, h.numero);
		return ans;
	}
	
	public String toString() {
		return "Habitacion [planta=" +planta + ", numero="+numero+"]";
	}

}
