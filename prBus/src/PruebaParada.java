import bus.Parada;

public class PruebaParada {
	public static void main(String[] args) {
		Parada p1 = new Parada("Louis Pasteur", 36.71654, -4.47508);
		Parada p2 = new Parada("Paseo del Parque", 36.71884, -4.41910);
		Parada p3 = new Parada("Pedregalejo", 36.72219, -4.37708);
		String s1 = (p1.distancia(p2) > p2.distancia(p3)) ? "mayor":"menor";
		System.out.print("La distancia entre Louis Pasteur y Paseo del Parque es "+ s1 +" que la distancia entre Paseo del Parque y Pedregalejo");
	}
}
