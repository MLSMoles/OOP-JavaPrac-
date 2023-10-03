package libreria;

public class LibreriaOfertaFlex extends Libreria{
	private OfertaFlex flex;	
	
	
	public LibreriaOfertaFlex(OfertaFlex o) {
		flex = o;
	}
	
	public void setOferta(OfertaFlex o) {
		flex =o;
	}
	
	public OfertaFlex getOferta() {
		return flex;
	}
	
	public void addLibro(String s1,String s2,double d) {
		Libro l1 = new Libro(s1, s2, d);
		Libro l2;
		try {
			l2 = (flex.getDescuento(l1) ==0) ? l1 : new LibroOferta(s1, s2, d, flex.getDescuento(l1));
			anyadirLibro(l2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return flex.toString() + super.toString();
	}
	
}
