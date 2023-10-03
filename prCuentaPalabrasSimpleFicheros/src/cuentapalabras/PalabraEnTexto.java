package cuentapalabras;

public class PalabraEnTexto {
	private String palabra;
	private int cont;
	
	public PalabraEnTexto(String pal) {
		palabra = pal.toUpperCase();
		cont =1;
	}

	public boolean equals(Object o) {
		boolean ans = o instanceof PalabraEnTexto;
		if(ans) {
			PalabraEnTexto p = (PalabraEnTexto) o;
			 ans = this.palabra.equalsIgnoreCase(p.palabra);
		}
		return ans; 
	}
	
	public int hashCode() {
		return palabra.hashCode() + cont;
	}
	
	public String toString() {
		return palabra +": " +cont;
	}
	
	public void incrementa() {
		cont++;
	}
}
