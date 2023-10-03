package prjarras;

public class Jarra {
	private int contenido;
	private final int capacidad;

	public Jarra(int capacidad) {
		if (capacidad < 0)
			throw new RuntimeException();
		this.capacidad = capacidad;
		contenido = 0;

	}

	public int capacidad() {
		return capacidad;
	}

	public int contenido() {
		return contenido;
	}

	public void llena() {
		contenido = capacidad;
	}

	public void vacia() {
		contenido = 0;
	}

	public void llenaDesde(Jarra J) {
		if (this == J)
			throw new RuntimeException();

		if (J.contenido < this.capacidad - this.contenido) {
			this.contenido += J.contenido;
			J.vacia();
		} else {
			J.contenido -= (this.capacidad - this.contenido);
			this.llena();
		}

	}

	public String toString() {
		return "J(" + capacidad + ", " + contenido + ")";
	}
}
