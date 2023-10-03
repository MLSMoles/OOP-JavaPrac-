import cuentapalabras.ContadorPalabras;

public class PruebaContadorPalabras {

	
	public static void main(String[] args) {
		
		ContadorPalabras cp = new ContadorPalabras();
		 String [] datos = {
		          "Esta es la primera frase del ejemplo",
		          "y esta es la segunda frase"
		};
		 cp.incluyeTodas(datos, "[ ]");
		 
		 System.out.println(cp.toString());
	}
}
