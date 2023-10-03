package libreria;

public class Libro {

	private static double porcIVA = 10;
	private String autor;
	private String titulo;
	private double precioBase;
	
	
	public Libro(String s1,String s2,double p) {
		autor = s1;
		titulo = s2;
		precioBase = p;
	}


	public String getAutor() {
		return autor;
	}


	public String getTitulo() {
		return titulo;
	}


	public double getPrecioBase() {
		return precioBase;
	}
	
	protected double getBaseImponible() {
		return precioBase;
	}
	
	public double getPrecioFinal() {
		return getBaseImponible() +(getBaseImponible()*porcIVA )/ 100;
	}
	
	public String toString() {
		return "(" + autor + "; " + titulo + "; " + precioBase + "; " +porcIVA + "%;" + getPrecioFinal() +")";
	}
	
	public static double getIVA() {
		return porcIVA;
	}
	public void setIva(double d) {
		porcIVA = d;
	}
}
