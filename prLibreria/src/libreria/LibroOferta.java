package libreria;

public class LibroOferta extends Libro {
	protected double porcDescuento;
	
	public LibroOferta(String autor,String titulo,double precioBase,double descuento) throws Exception {
		super(autor, titulo, precioBase);
		if(descuento <0) throw new Exception("Descuento menor que 0.");
		porcDescuento = descuento;
	}
	
	public double getDescuento() {
		return porcDescuento;
	}
	
	protected double getBaseImponible() {
		return super.getPrecioBase() - super.getPrecioBase()*porcDescuento/100;
		
	}
	
	public String toString() {
		return "(" + getAutor() + "; " + getTitulo() + "; " + getPrecioBase() + "; " + porcDescuento + "%; " + getBaseImponible() + "; " + getIVA() + "; "  + getPrecioFinal() + ")";	
	}
}
