package prIndicePalabras;

import java.io.PrintWriter;

public interface Indice {
	 public void agregarFrase(String frase);
	 
	 public void resolver(String delimitadores);
	 
	 public void presentarIndiceConsola();
	 
	 public void presentarIndice(PrintWriter pw);
	
}
