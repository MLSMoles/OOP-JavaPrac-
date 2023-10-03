import cuentapalabras.PalabraEnTexto;

public class PruebaPalabraEnTexto {

	
	public static void main(String[] args) {
		PalabraEnTexto p1 = new PalabraEnTexto("gorra");
		PalabraEnTexto p2 = new PalabraEnTexto("Gorra");
		p1.incrementa();
		System.out.println("Palabra 1: " +p1 +"\n" +"Palabra 2: "+ p2+"\n" +(p1.equals(p2) ? "Palabras son iguales.":"PAlabras son diferentes."));
	}
}
