package cuentapalabras;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class ContadorPalabrasSig extends ContadorPalabras{
	private ArrayList<String> list;
	
	public ContadorPalabrasSig() {
		super();
	}
	
	public void leeArrayNoSig(String[] pal) {
		list.clear();
		for(String p :pal) {
			int i = 0;
			while(i<list.size() && !list.get(i).equalsIgnoreCase(p)) i++;
			if(i!= list.size()) list.add(p);
		}
	}
	
	private void leerFicheroNoSig(String filNoSig, String del) throws FileNotFoundException{
		try(Scanner sc=new Scanner(new File(filNoSig))){
			leerPalabrasNoSignificativas(sc,del);
		}
	}
	
	private void leerPalabrasNoSignificativas(Scanner sc, String del) {
		while(sc.hasNextLine()) {
			leerLineasNoSignificativas(sc.nextLine(),del);
		}
	}
	
	private void leerLineasNoSignificativas(String cad, String del) {
		try(Scanner scLinea=new Scanner(cad)){
			scLinea.useDelimiter(del);
			while(scLinea.hasNext()) {
				list.add(scLinea.next().toUpperCase());
			}
		}
	}
	
	private void anyadePalabrasNoSignificativas(String linea, String del) {
		try(Scanner sc = new Scanner(linea)){
			sc.useDelimiter(del);
			int i = 0;
			while(sc.hasNext()) {
				while(i<list.size() && !list.get(i).equalsIgnoreCase(sc.next())) i++;
				if(i!= list.size()) list.add(sc.next());
			}
			
		}
	}
	
	private int estaNoSig(String pal) {
		int i = 0;
		while(i<list.size() && !list.get(i).equalsIgnoreCase(pal)) {
			i++;
		}
		return (i==list.size()) ? -1:i;
	}
}
