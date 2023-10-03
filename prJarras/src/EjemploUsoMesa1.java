import prjarras.Mesa;

public class EjemploUsoMesa1 {
	
	public static void main(String[] args) {
		Mesa m = new Mesa(7,5);
		m.llena(2);
		System.out.println(m.toString());
		m.llenaDesde(1);
		System.out.println(m.toString());
		m.llena(2);
		System.out.println(m.toString());
		m.llenaDesde(1);
		System.out.println(m.toString());
		m.vacia(1);
		System.out.println(m.toString());
		m.llenaDesde(1);
		System.out.println(m.toString());
		m.llena(2);
		System.out.println(m.toString());
		m.llenaDesde(1);
		System.out.println(m.toString());
	}
	
}

